var app = angular.module('resetPassword',[]).controller('resetController',function($scope,$http,$location){
	console.log("hi");
	$scope.reset = function(email,password,userName){
		console.log(email);
		console.log(password);
		var id = $location.absUrl().split('=')[1];
		$http.post('resetPwd',{'mail':email,'password':password,'userName':userName,'id':id
		 }).then(function(resp){
			 console.log("resetPassword")
			 $scope.success=true;
			 console.log(resp.data.success);
			 $scope.successMessage="password Updated Successfully";
			 $scope.email="";
			 $scope.password="";
			 $scope.repassword="";
			 $scope.userName="";
		 },function(resp){
			 $scope.error = true;
			 $scope.success=false
	 			if (resp.status === 0) {
	 				$scope.errorMessage = 'No connection. Verify application is running.';
	 			} else if (resp.status == 400) {
	 				$scope.errorMessage = 'Password is not Updated Successfully';
	 			} else if (resp.status == 403) {
	 				$scope.errorMessage = 'Dont have Permision to NVault Application';
	 			} else if (resp.status == 409) {
	 				$scope.errorMessage = 'Password Reset Link has been expired';
	 			} 
			 $scope.email="";
			 $scope.password="";
			 $scope.repassword="";
			 $scope.userName="";
		 });
	}
});