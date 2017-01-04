//inject angular file upload directives and services.
var app = angular.module('fileUpload', ['ngFileUpload']);

app
    .controller(
        'MyCtrl', [
            '$scope',
            'Upload',
            '$timeout',
            function($scope, Upload, $timeout) {
                $scope.$watch('files', function() {
                    $scope.upload($scope.files);
                });
                $scope.$watch('file', function() {
                    if ($scope.file != null) {
                        $scope.upload([$scope.file]);
                    }
                });
                $scope.log = '';

                $scope.upload = function(files) {

                    if (files) {
                        var Size = 0;
                        var fileSize = 0;

                        for (var i = 0; i < files.length; i++) {

                            var file = files[i];
                            Size = (files[i].size) / 1048576;
                            fileSize = fileSize + Size;

                        }
                        if (fileSize < 10) {

                            if (files.length < 5) {
                                for (var i = 0; i < files.length; i++) {
                                    var file = files[i];
                                    alert(file.name);

                                    Upload
                                        .upload({
                                            url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                                            fields: {
                                                'username': $scope.username
                                            },
                                            file: file
                                        })
                                        .progress(
                                            function(evt) {
                                                var progressPercentage = parseInt(100.0 *
                                                    evt.loaded /
                                                    evt.total);
                                                $scope.log = 'progress: ' +
                                                    progressPercentage +
                                                    '% ' +
                                                    evt.config.file.name +
                                                    '\n' +
                                                    $scope.log;
                                            })
                                        .success(
                                            function(
                                                data,
                                                status,
                                                headers,
                                                config) {
                                                $timeout(function() {
                                                    $scope.log = 'file: ' +
                                                        config.file.name +
                                                        ', Response: ' +
                                                        JSON
                                                        .stringify(data) +
                                                        '\n' +
                                                        $scope.log;
                                                });
                                            });
                                }
                            } else {
                                alert("");
                            }
                        } else {
                            alert("Size limit Exceeded");

                        }
                    }


                };
            }
        ]);