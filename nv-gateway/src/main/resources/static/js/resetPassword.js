var app = angular.module('resetPassword',[]).controller('resetController',function($scope,$http,$location){
	console.log("hi");
	$scope.reset = function(password){
		var id = $location.absUrl().split('=')[1];
		$http.post('resetPwd',{'password':password,'id':id
		 }).then(function(resp){
			 console.log("resetPassword")
			 $scope.success=true;
			 console.log(resp.data.success);
			 $scope.successMessage="password Updated Successfully";
			 $scope.password="";
			 $scope.repassword="";
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
			
			 $scope.password="";
			 $scope.repassword="";
			 
		 });
	}
});