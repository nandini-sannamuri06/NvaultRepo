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

                $scope.upload = function(files) {

                    if (files) {
                        var Size = 0;
                        var fileSize = 0;
                        $scope.ShowFilesProgress = false;
                        for (var i = 0; i < files.length; i++) {

                            var file = files[i];

                            Size = (files[i].size) / 1048576;
                            fileSize = fileSize + Size;

                        }
                        if (files.length <= 5) {

                            if (fileSize < 10) {

                                for (var i = 0; i < files.length; i++) {
                                    var file = files[i];

                                    if (file.type == "application/x-msdownload" ||
                                        file.type == "" ||
                                        file.type == "text/html" ||
                                        file.type == "text/css" ||
                                        file.type == "text/javascript" ||
                                        file.type == "text/php" ||
                                        file.type == "application/xml" ||
                                        file.type == "text/x-python-script") {


                                        $scope.fileFormat = true;
                                        $scope.fileType = true;
                                        $scope.File = "File format not supported";
                                        $scope.Type = "File types with .exe,.py,.php,cfc,.xml,.js,.css,.html are not allowed";
                                        $timeout(
                                            function() {
                                                $scope.fileFormat = false;
                                                $scope.fileType = false;
                                            }, 5000);
                                        $scope.ShowFilesProgress = false;
                                       files=[];
                                    } else {
                                        $scope.ShowFilesProgress = true;
                                        Upload
                                            .upload({
                                                url: 'https://angular-file-upload-cors-srv.appspot.com/upload',

                                                file: file
                                            })
                                            .progress(
                                                function(
                                                    evt) {
                                                    for (var i = 0; i < files.length; i++) {

                                                        var file = files[i];
                                                        file.progress = Math
                                                            .min(
                                                                100,
                                                                parseInt(100.0 *
                                                                    evt.loaded /
                                                                    evt.total));

                                                    }

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

                                }
                            } else {

                                $scope.SizeIncreased = true;
                                $scope.Size = "File Upload Size limit should not exceed 10MB";
                                $timeout(function() {
                                    $scope.SizeIncreased = false;
                                }, 5000);
                            }
                        } else {

                            $scope.legthIncreased = true;

                            $scope.length = "Should not upload more than 5 files at a time";
                            $timeout(function() {
                                $scope.legthIncreased = false;
                            }, 5000);
                        }
                    }

                };

            }

        ]);