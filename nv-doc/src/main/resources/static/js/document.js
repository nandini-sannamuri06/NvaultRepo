
var app = angular.module('Docs', ['ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination','ngFileUpload' ])

app.controller('DocsCtrl', DocsCtrl);
//Upload UI

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
                                            url: '/resource/uploadDocs',

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
                                              $scope.successFlag=true;
                                              $scope.successMessage = data;
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
var seleIndex ='';
DocsCtrl.$inject = [ '$scope', '$http', '$modal', 'uiGridConstants' ];
function DocsCtrl($scope, $http, $modal, uiGridConstants) {
	
	var vm = this;
	$scope.showdata =true;
	vm.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		paginationPageSizes: [25, 50, 75, 100],
		paginationPageSize: 25,
		onRegisterApi: function(gridApi) {
		    $scope.gridApi = gridApi;
		    $scope.mySelectedRows = $scope.gridApi.selection.getSelectedRows();
		    gridApi.selection.on.rowSelectionChanged($scope, function(row) {
		        var msg = row.entity.fileName;
		        seleIndex = msg;
		        $('div.grdOptionBtns').css('display','block');
		    });
//		    rowTemplate: '<div ng-click="myvalue = !myvalue" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" class="ui-grid-cell" ng-class="col.colIndex()" ui-grid-cell></div>',
//		    rowTemplate: '<div ng-click="myvalue = !myvalue"></div>',
//		    if($scope.mySelectedRows.length>0){
//		    	$scope.myvalue = true; 
//		    }
		}

		//rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
	};
	vm.serviceGrid.columnDefs = [ {
		field : 'fileName',
		displayName : 'File Name',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'size',
		displayName : 'Size',
		enableSorting : true,
		enableCellEdit : false,
//		cellTemplate:'<button class="btn primary" ng-click="grid.appScope.showMe()" >Click Me</button>'
	},{
		field : 'modifiedDate',
		displayName : 'ModifiedDate',
		enableSorting : true,
		enableCellEdit : false,
	}
	];


	$http.get('/resource/fetchDocs',{params:{folderName:'home'}}).success(function(response) {
		vm.serviceGrid.data = response;
		$scope.docs = response;
	}).error(function(response) {
		$log.error(response);
	})
	
	
	$scope.createFolder = function() {
		alert(seleIndex);
		
	},
	$scope.archive = function() {
		var fileNameValue = seleIndex;
		if(fileNameValue==''){
			alert('Please select a file for moving to Archive');
		}else{
			$http.get('/resource/updateDocs',{params:{folderName:'archive',fileName:fileNameValue}}).success(function(response) {
				alert(response)
			}).error(function(response) {
				$log.error(response);
			})
			
		}
	},
	$scope.trash = function() {
		var fileNameValue = seleIndex;
		if(fileNameValue==''){
			alert('Please select a file for moving to Trash');
		}else{
		$http.get('/resource/updateDocs',{params:{folderName:'trash',fileName:fileNameValue}}).success(function(response) {
			alert(response)
		}).error(function(response) {
			$log.error(response);
		})
		}
		
	}
	$scope.Download = function() {
		var fileNameValue = seleIndex;
		if(fileNameValue==''){
			alert('Please select a file for downloading');
		}else{
			window.location.assign('/resource/downloadDoc?fileName='+fileNameValue+'&folderName=home');
		}
		
	}
}

