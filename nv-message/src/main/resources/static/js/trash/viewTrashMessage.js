var app = angular.module('Messages', [ 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination','ngTagsInput','mailService' ])

// Venu
angular.module('mailService',[]).factory('mailFactory',function($http){
	return{
		sendMail: function(mailObj){
			$http.post('/messages/mail/send', mailObj)
			.then(function(data){
				console.log("mail send successfully");
			})
			.catch(mailSenfFailMsg);
		},
	}
});
app.controller('getTrMessagesCtrl', getTrMessagesCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

// venu
app.controller('myCtrl', function($scope, $http, mailFactory) {
    $scope.tags = [
// { text: 'vmeesala@nisum.com' },
// { text: 'skaranam@nisum.com' },
// { text: 'javeed@nisum.com' }
    ];
    $scope.loadTags = function(query) {
         return $http.get('/tags?query=' + query);
    };
    
    $scope.sendMail = function(mailObj){
    	mailObj.toAddress = $scope.tags;
    	mailFactory.sendMail(mailObj);
	}
});

getTrMessagesCtrl.$inject = [ '$scope', '$http', '$modal', 'RowEditor', 'uiGridConstants' ];
function getTrMessagesCtrl($scope, $http, $modal, RowEditor, uiGridConstants) {
	var vtm = this;

	vtm.editRow = RowEditor.editRow;

	vtm.serviceGridtrash = {
		paginationPageSizes: [5, 10, 15],
		paginationPageSize: 5,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		enableGridMenu : true,
// rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\"
// ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by
// col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{
// 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
	};

	vtm.serviceGridtrash.columnDefs = [ {
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
		field : 'sender',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'subject',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'body',
		width : 120,
		enableSorting : true,
		enableCellEdit : false
	},{
		field : 'date',
		width : 120,
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'recipient',
		enableSorting : true,
		enableCellEdit : false
	}
	];

	$http.get('/resource/getTrashMessages').success(function(response) {
		vtm.serviceGridtrash.data = response;
	});

	$scope.addRow = function() {
	var newService = {
		"id" : "0",
		"category" : "public",
		"exposednamespace" : "http://bced.wallonie.be/services/",
		"exposedoperation" : "-",
		"exposedws" : "-",
		"path" : "//*[local-name()='-']/text()",
		"provider" : "BCED",
		"version" : "1.0"
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
			templateUrl : 'manageMessages.html',
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
	function save() {
		if (row.entity.id == '0') {
			/*
			 * $http.post('http://localhost:8080/service/save',
			 * row.entity).success(function(response) {
			 * $modalInstance.close(row.entity); }).error(function(response) {
			 * alert('Cannot edit row (error in console)');
			 * console.dir(response); });
			 */
			row.entity = angular.extend(row.entity, vm.entity);
			// real ID come back from response after the save in DB
			row.entity.id = Math.floor(100 + Math.random() * 1000);
			
			grid.data.push(row.entity);

		} else {
			row.entity = angular.extend(row.entity, vm.entity);
			/*
			 * $http.post('http://localhost:8080/service/save',
			 * row.entity).success(function(response) {
			 * $modalInstance.close(row.entity); }).error(function(response) {
			 * alert('Cannot edit row (error in console)');
			 * console.dir(response); });
			 */
		}
		$modalInstance.close(row.entity);
	}

	vm.trash = trash;
	function trash() {
		console.dir(row)
		if (row.entity.id != '0') {
			row.entity = angular.extend(row.entity, vm.entity);
			var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
			grid.appScope.vm.serviceGrid.data.splice(index, 1);
			
	  $http.get('/resource/updateMessage/'+row.entity.id).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot delete row (error in console)'); console.dir(response); });
			 
		}
		$modalInstance.close(row.entity);
	}
	
	vm.archieve = archieve;
	function archieve() {
		console.dir(row)
		if (row.entity.id != '0') {
			row.entity = angular.extend(row.entity, vm.entity);
			var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
			grid.appScope.vm.serviceGrid.data.splice(index, 1);
			
	  $http.get('/resource/updateMessageArchieve/'+row.entity.id).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot archieve row (error in console)'); console.dir(response); });
			 
		}
		$modalInstance.close(row.entity);
	}

}
