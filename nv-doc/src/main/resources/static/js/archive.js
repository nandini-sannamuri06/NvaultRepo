
var app = angular.module('Docs', ['ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination' ])

app.controller('DocsArchiveCtrl', DocsArchiveCtrl);

DocsArchiveCtrl.$inject = [ '$scope', '$http', '$modal', 'uiGridConstants' ];
function DocsArchiveCtrl($scope, $http, $modal, uiGridConstants) {
	var vma = this;
	$scope.showdata =true;
	vma.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		paginationPageSizes: [25, 50, 75, 100],
		paginationPageSize: 25,
		//rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
	};
	vma.serviceGrid.columnDefs = [ {
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

	$http.get('/resource/fetchDocs',{params:{folderName:'archive'}}).success(function(response) {
		vma.serviceGrid.data = response;
		$scope.docs = response;
	}).error(function(response) {
		$log.error(response);
	})
	
	$scope.refreshData = function() {
		vma.serviceGrid.data = $filter('filter')($scope.docs, $scope.searchArchiveDocs, undefined);
	}

	
	$scope.createFolder = function() {
		
		
	}
}

