
var app = angular.module('Docs', ['ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination' ])

app.controller('DocsCtrl', DocsCtrl);

DocsCtrl.$inject = [ '$scope', '$http', '$modal', 'uiGridConstants' ];
function DocsCtrl($scope, $http, $modal, uiGridConstants) {
	
//	 $scope.someProp = 'abc',
//	 $scope.showbuttons = true;
//	 $scope.showMe = function(){
//	                   alert($scope.someProp);
//	                   $scope.showbuttons = false;
//	                };	
	var vm = this;
	$scope.showdata =true;
	vm.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		paginationPageSizes: [25, 50, 75, 100],
		paginationPageSize: 25,
		//rowTemplate: '<div  ng-click="foo()" ng-bind="row.getProperty(id)"></div>'

		//rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
	};
	vm.serviceGrid.columnDefs = [ {
		field : 'id',
		displayName : 'Id',
		enableSorting : true,
		type : 'number',
		enableCellEdit : false,
		width : 60,
		sort : {
			direction : uiGridConstants.ASC,
			priority : 1,
		},
	}, {
		field : 'fileName',
		displayName : 'File Name',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'path',
		displayName : 'Location',
		enableSorting : true,
		enableCellEdit : false,
//		cellTemplate:'<button class="btn primary" ng-click="grid.appScope.showMe()" >Click Me</button>'
	}
	];


	$http.get('/resource/getAllDocs').success(function(response) {
		vm.serviceGrid.data = response;
		$scope.docs = response;
	}).error(function(response) {
		$log.error(response);
	})
	
	$scope.foo = function() {

    alert('');
  };

	
	$scope.createFolder = function() {
		
		
	}
}

