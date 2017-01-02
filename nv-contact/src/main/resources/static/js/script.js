var app = angular.module('influx', ['ngMessages', 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination' ])

app.controller('MainCtrl', MainCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

MainCtrl.$inject = [ '$scope', '$http', '$modal', 'RowEditor', 'uiGridConstants' ];
function MainCtrl($scope, $http, $modal, RowEditor, uiGridConstants) {
	var vm = this;

	vm.editRow = RowEditor.editRow;

	vm.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		enableGridMenu : true,
		paginationPageSizes: [25, 50, 75, 100],
		paginationPageSize: 25,
		rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
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
		field : 'firstName',
		displayName : 'First Name',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'lastName',
		displayName : 'Last Name',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'emailId',
		displayName : 'Email Id',
		enableSorting : true,
		enableCellEdit : false
	} ];

//	$http.get('data.json').success(function(response) {
//		vm.serviceGrid.data = response;
//	});

	$http.get('/resource/allContacts').success(function(response) {
		vm.serviceGrid.data = response;
	}).error(function(response) {
		$log.error(response);
	})
	
	$scope.addRow = function() {
		var newService = {
			"id" : "0",
			"firstName" : "fname",
			"lastName" : "lname",
			"emailId" : "-"
		};
		var rowTmp = {};
		rowTmp.entity = newService;
		vm.editRow($scope.vm.serviceGrid, rowTmp);
	};

}

RowEditor.$inject = [ '$http', '$rootScope', '$modal' ];
function RowEditor($http, $rootScope, $modal) {
	var service = {};
	service.editRow = editRow;

	function editRow(grid, row) {
		$modal.open({
			templateUrl : 'service-edit.html',
			controller : [ '$http', '$modalInstance', 'grid', 'row', RowEditCtrl ],
			controllerAs : 'vm',
			resolve : {
				grid : function() {
					return grid;
				},
				row : function() {
					return row;
				}
			}
		});
	}

	return service;
}

function RowEditCtrl($http, $modalInstance, grid, row) {
	var vm = this;
	vm.entity = angular.copy(row.entity);
	vm.save = save;
	function save(isvalid) {
		if(isvalid){
			if (row.entity.id == '0') {
				
				$http.post('/resource/create', vm.entity).success(function(response) {
					
					row.entity = angular.extend(row.entity, vm.entity);
					//real ID come back from response after the save in DB
					row.entity.id = response.id;
					grid.data.push(row.entity);
					
					$modalInstance.close(row.entity); 
					alert('Contact Added Successfully!!!'); 
					}).error(function(response) {
						alert('Cannot Add row (error in console)'); 
						console.dir(response);
					});
				 
	
			} else {
				row.entity = angular.extend(row.entity, vm.entity);
				
				$http.post('/resource/update', vm.entity).success(function(response) {
					$modalInstance.close(row.entity); 
					alert('Contact Updated Successfully!!!'); 
					}).error(function(response) { 
						alert('Cannot edit row (error in console)');
						console.dir(response);
						});
				 
			}
		}else{
			alert('Sorry, Unable to Save due to InValid Details'); 
		}
		$modalInstance.close(row.entity);
	}

	vm.remove = remove;
	function remove() {
		console.dir(row)
		if (row.entity.id != '0') {
			 $http.delete('/resource/delete', {params: {'id': row.entity.id}}).success(function(response) {
				 row.entity = angular.extend(row.entity, vm.entity);
				 var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
				 grid.appScope.vm.serviceGrid.data.splice(index, 1);
				 $modalInstance.close(row.entity); 
				 alert('Contact Deleted Successfully!!!'); 
				 }).error(function(response) { 
					 alert('Cannot delete row (error in console)'); 
					 console.dir(response); 
				});
			 
		}
		$modalInstance.close(row.entity);
	}

}
