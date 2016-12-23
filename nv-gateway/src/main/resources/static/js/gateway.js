angular.module('gateway', []).config(function($httpProvider) {

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

		function($http,$timeout) {
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
			        	 self.SignupSuccess = true;
			             self.regSuccess = "User has been added successfully  ";
			             self.toLogin = "   here to login"; 
			        	   self.SignupSuccess = true;
			        	   self.reset();
			        	   $timeout(function () { self.SignupSuccess = false; }, 2000); 
			        	      });
		
	}
	


	
});