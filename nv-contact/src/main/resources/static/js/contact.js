var nvault = angular.module('nvault',['ngRoute','ngGrid'])
.config(
		   function($routeProvider, $httpProvider, $locationProvider) {
			$locationProvider.html5Mode(true);
			$routeProvider
				.when('/register', {
					templateUrl : 'js/register/register.html',
					controller : 'RegisterController',
					controllerAs : 'vm'
				})
				.when('/EditContact', {
					templateUrl: 'views/EditContact.html',
					controller: 'editContactCtrl'
				})
				.otherwise({
					redirectTo: '/index.html'
				});
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
})
.controller('searchcontactCtrl',['$scope','$timeout','$http','$q',
function($scope,$timeout,$http,$q) {
//	 $scope.gridOptions = {
//		        excludeProperties: '__metadata',
//		    };
	$scope.numRows = 10;
	$scope.myData = {};
	$scope.loadContacts = function() {
		$http.get('/resource/allContacts').success(function(data, status, headers, config) {
			
			$scope.myData = angular.fromJson(data);
			$scope.gridOptions = data;
         $scope.gridOptions.totalItems = data.length;
		$scope.gridOptions.paginationPageSize = $scope.numRows;
	$scope.gridOptions.minRowsToShow = data.length < $scope.numRows ? data.length : $scope.numRows;
		}).error(function(data, status, headers, config) {
			$log.error(data);
		})
	}
	
	$scope.loadContacts();

	$scope.EditContact = function(data) {
		$scope.userToEdit = data;
		location.href="EditContact.html";
        alert('update completed!');
   }
	$scope.CreateContact = function(data) {
		$scope.userToEdit = data;
		location.href="js/register/register.html";
   }
   $scope.DeleteContact = function(data) {
	   $http.delete('/resource/delete', data).then(contactdeleteSuccessMsg)
       .catch(contactdeleteError);
        alert('Delete completed!');
    }
   function contactdeleteSuccessMsg(response){
		return response.data.message;
	}
   function contactdeleteError(response) {
	     return $q.reject('Error deleting contact: ' + response.status );
	}
	
	$scope.gridOptions = {
			data: 'myData',
			enableSorting: true,
		    enableColumnMenus: false,
			enablePaginationControls: false,
			enableHorizontalScrollbar : 0,
	        enableVerticalScrollbar   : 0,
			selectionRowHeaderWidth: 35,
			paginationCurrentPage: 1,
		    columnDefs: [
		      { field: 'firstName', displayName:'Name', cellClass: 'ui-grid-align'},
		      { field: 'emailId', displayName:"Email Id",cellClass: 'ui-grid-align' },
		      { field: 'edit', enableSorting: false, cellTemplate: '<button id="editBtn" type="button"  ng-click="EditContact(row.entity)" >Edit</button>'},
		      { field: 'delete', enableSorting: false, cellTemplate: '<button id="deleteBtn" type="button"  ng-click="DeleteContact(row.entity)" >Delete</button>' }

		      ],
		    onRegisterApi: function( gridApi ) {
		      $scope.gridApi = gridApi;
		      $scope.gridApi.grid.registerRowsProcessor($scope.singleFilter, 200);
		    }
		  };
	
	$scope.singleFilter = function(renderableRows) {
		var searchValue = "";
		if($scope.filterValue){
			searchValue = $scope.filterValue.toUpperCase()
		}
	    var matcher = new RegExp(searchValue);
	    renderableRows.forEach(function(row) {
	        var match = false;
	        ['firstName'].forEach(function(field) {
	            if (row.entity[field] && row.entity[field].toUpperCase().match(matcher)) {
	                match = true;
	            }
	        });
	        if (!match) {
	            row.visible = false;
	        }
	    });
	    return renderableRows;
	};
	
	$scope.searchFilter = function() {
		$scope.gridApi.grid.refresh();
	};
	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			self.authenticated = false;
			self.admin = false;
		});
	}

}]).controller('RegisterController', RegisterController);

RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
function RegisterController(UserService, $location, $rootScope, FlashService) {
    var vm = this;

    vm.register = register;

    function register() {
        vm.dataLoading = true;
        UserService.Create(vm.user)
            .then(function (response) {
                if (response.success) {
                    FlashService.Success('Registration successful', true);
                    $location.path('/login');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
    }
};
