var app = angular.module('Messages', [ 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination','ngTagsInput','ngSanitize', 'ui.select', 'mailService' ])

app.filter('propsFilter', function() {
	  return function(items, props) {
	    var out = [];

	    if (angular.isArray(items)) {
	      items.forEach(function(item) {
	        var itemMatches = false;

	        var keys = Object.keys(props);
	        for (var i = 0; i < keys.length; i++) {
	          var prop = keys[i];
	          var text = props[prop].toLowerCase();
	          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
	            itemMatches = true;
	            break;
	          }
	        }

	        if (itemMatches) {
	          out.push(item);
	        }
	      });
	    } else {
	      // Let the output be the input untouched
	      out = items;
	    }

	    return out;
	  };
});

// Venu
angular.module('mailService',[]).factory('mailFactory',function($http){
	return{
		sendMail: function(mailObj){
			$http.post('/resource/mail/send', mailObj)
			.then(function(data){
				console.log("mail sent successfully");
			})
			.catch(function(data){
				console.log("mail not sent successfully");
			});
		},
	}
});
app.controller('getMessagesCtrl', getMessagesCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

// venu
app.controller('myCtrl', function($scope, $http, $timeout, mailFactory) {
    $scope.tags = [
// { text: 'vmeesala@nisum.com' },
// { text: 'skaranam@nisum.com' },
// { text: 'javeed@nisum.com' }
    ];
    $scope.loadTags = function(query) {
         return $http.get('/tags?query=' + query);
    };
    
    $scope.multipleDemo = {};
    
    
    $scope.sendMail = function(mailObj){
    	mailObj.toAddress = $scope.multipleDemo.selectedPeople;
    	mailFactory.sendMail(mailObj);
	}
    
    $scope.tagTransform = function (newTag) {
        var item = {
          firstName: newTag,
          emailId: newTag
        };
        return item;
      };


      $scope.counter = 0;
      $scope.someFunction = function (item, model){
        $scope.counter++;
        $scope.eventResult = {item: item, model: model};
      };

      $scope.person = {};

      $http.get('/resource/allContacts').success(function(data) {
    	  $scope.people = data;
    	});
      
     
    
});

getMessagesCtrl.$inject = [ '$scope', '$http', '$modal', 'RowEditor', 'uiGridConstants' ];
function getMessagesCtrl($scope, $http, $modal, RowEditor, uiGridConstants) {
	var vm = this;

	vm.editRow = RowEditor.editRow;

	vm.serviceGrid = {
		paginationPageSizes: [5, 10, 15],
		paginationPageSize: 5,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		enableGridMenu : true,
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

	$http.get('/resource/getMessages').success(function(response) {
		vm.serviceGrid.data = response;
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
