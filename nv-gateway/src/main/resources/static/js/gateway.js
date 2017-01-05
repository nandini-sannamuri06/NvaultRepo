angular.module('gateway', ['ui.bootstrap']).config(function($httpProvider) {

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

		function($http,$timeout,$uibModal) {
	var self = this;

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":"
							+ credentials.password)
		} : {};

		self.user = ''
		$http.get('user', {
			headers : headers
		}).then(function(response) {
			var data = response.data;
			if (data.name) {
				self.authenticated = true;
				self.user = data.name
				self.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;
			} else {
				self.authenticated = false;
				self.admin = false;
			}
			callback && callback(true);
		}, function(response) {
			self.loginError = true;
			if (response.status === 0) {
				self.error = 'No connection. Verify application is running.';
			} else if (response.status == 401) {
				self.error = 'Access is denied due to invalid credentials';
			} else if (response.status == 403) {
				self.error = 'Dont have Permision to NVault Application';
			}
			 $timeout(function () { self.loginError = false; }, 5000);   
			callback && callback(false);
		});

	}
	self.credentials = {};
	self.loginError = false;
	self.login = function() {
		authenticate(self.credentials, function(authenticated) {
			self.authenticated = authenticated;
		})
	};

	self.reset = function() {
		console.log("reseting");
		self.loginError = false;
		self.authenticated = false;
		self.admin = false;
		self.credentials ={};
		self.signup ={};
	}

	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			self.authenticated = false;
			self.admin = false;
		});
	}
	
	self.register = function() {	
				   $http.post('/register', {
			             username: self.signup.username,
			             password:self.signup.password,
			             mail: self.signup.email,
			         }).
			         success(function(data, status, headers) {

			    		 console.log("createBucket"+data.bucketName);
			    		 $http.post('/resource/createBucket',{
			    			 'bucketName':data.bucketName,
			    			 'userName': self.signup.username
			    		 }).then(function(resp){
			    			 self.SignupSuccess = true;
				             self.regSuccess = "User has been added successfully  ";
				             self.toLogin = "   here to login"; 
				        	   self.SignupSuccess = true;
				        	   self.reset();
				        	   $timeout(function () { self.SignupSuccess = false; }, 6000);
			    		 },function(resp){
			    			 $scope.SignupSuccess=false
					 			if (response.status === 0) {
					 				$scope.error = 'No connection. Verify application is running.';
					 			} else if (response.status == 400) {
					 				$scope.error = 'Bucket has not been created';
					 			} else if (response.status == 403) {
					 				$scope.error = 'Dont have Permision to NVault Application';
					 			} 
					 			 $timeout(function () { $scope.forgotError = false; }, 5000);
			    		 });
		
	});
	}
	self.forgot = function()
	{
		console.log('opening pop up');
		var uibModalInstance = $uibModal.open({
		templateUrl: 'forgotPassword.html',
		controller:function($uibModalInstance ,$scope,$http,$timeout){
			     $scope.close = function () {
			            $uibModalInstance.dismiss('cancel');
			         };
			     $scope.reset = function(email) {
			    	 $scope.submitted = true;
			    	 $http.get('checkEmail',{params: {'email': email}}).
			    	 then(function(response) {
			    		 console.log("sendMail");
			    		 $http.post('/resource/sendMail',{
			    			 'recipient':email,
// Id should be generated and appended and appended to the URL.Once the user is
// successfully reset the password then need to insert into the DB which is like
// already reseted.If the user again tries to update need to thrown an Exception
// like link is already used.
			    			 'body':'http://localhost:9006/resetPassword.html?id='+Math.random()*9,
			    			 'subject':'Reset Password'
			    		 }).then(function(resp){
			    			 console.log("sendMail")
			    			 $scope.success=true;
			    		 },function(resp){
			    			 $scope.forgotError = true;
			    			 $scope.success=false
					 			if (response.status === 0) {
					 				$scope.error = 'No connection. Verify application is running.';
					 			} else if (response.status == 400) {
					 				$scope.error = 'Email has Not Been Sent Successfully';
					 			} else if (response.status == 403) {
					 				$scope.error = 'Dont have Permision to NVault Application';
					 			} 
					 			 $timeout(function () { $scope.forgotError = false; }, 5000);
			    		 });
			 		}, function(response) {
			 			$scope.forgotError = true;
			 			if (response.status === 0) {
			 				$scope.error = 'No connection. Verify application is running.';
			 			} else if (response.status == 400) {
			 				$scope.error = 'This Email is not Registered with us.Please provide the valid email Id';
			 			} else if (response.status == 403) {
			 				$scope.error = 'Dont have Permision to NVault Application';
			 			}
			 			 $timeout(function () { $scope.forgotError = false; }, 5000);   
			 		});

			     }
		}
		});
	}
});