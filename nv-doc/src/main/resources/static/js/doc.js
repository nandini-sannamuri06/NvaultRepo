angular.module('hello', ['ngFileUpload'])
.controller('home',['$scope','Upload','$timeout','$http',

function($scope,Upload,$timeout,$http) {
	var self = this;
	$http.get('user').then(function(response) {
		var data = response.data;
		if (data.name) {
			self.authenticated = true;
			self.user = data.name
			/*$http.get(url='/resource/getFiles',data={userId:self.user}).then(function(response) {
				//self.greeting = response.data;
				console.log(response.data);
			})*/
		} else {
			self.authenticated = false;
		}
	}, function() {
		self.authenticated = false;
	});
	
	$scope.uploadFiles = function(file) {
        $scope.f = file;
        if (file) {
            file.upload = Upload.upload({
                url: '/resource/upload/',
                data: {uploadedFile: file,userId:self.user}
                
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                file.progress = Math.min(100, parseInt(100.0 * 
                                         evt.loaded / evt.total));
            });
        }   
    }
	
	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			self.authenticated = false;
			self.admin = false;
		});
	}

}]);
