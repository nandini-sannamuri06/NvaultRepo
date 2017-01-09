
var app = angular.module('Docs', ['ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination' ])

app.controller('DocsTrashCtrl', DocsTrashCtrl);

DocsTrashCtrl.$inject = [ '$scope', '$http','$filter', '$modal', 'uiGridConstants' ];
function DocsTrashCtrl($scope, $http, $filter, $modal, uiGridConstants) {
	var vmt = this;
	$scope.showdata =true;
	vmt.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		paginationPageSizes: [25, 50, 75, 100],
		paginationPageSize: 25,
		//rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
	};
	vmt.serviceGrid.columnDefs = [ {
		field : 'fileName',
		displayName : 'File Name',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'size',
		displayName : 'Size (KB)',
		enableSorting : true,
		enableCellEdit : false,
//		cellTemplate:'<button class="btn primary" ng-click="grid.appScope.showMe()" >Click Me</button>'
	},{
		field : 'modifiedDate',
		displayName : 'ModifiedDate',
		enableSorting : true,
		enableCellEdit : false,
	},{
		name : 'Download',
		cellTemplate : '<button type="button" class="glyphicon glyphicon-floppy-save" ng-click="grid.appScope.d(row.entity)"></button> '
	}
	];


	$http.get('/resource/fetchDocs',{params:{folderName:'trash'}}).success(function(response) {
		vmt.serviceGrid.data = response;
		$scope.docs = response;
	}).error(function(response) {
		$log.error(response);
	})
	
	$scope.refreshData = function() {
		vmt.serviceGrid.data = $filter('filter')($scope.docs, $scope.searchTrashDocs, undefined);
	}

	
	$scope.createFolder = function() {
		
		
	}
}

