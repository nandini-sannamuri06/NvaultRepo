var app = angular.module('Messages', [ 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit','ui.grid.pagination','ngTagsInput','ngSanitize', 'ui.select','mailService' ])
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
	     
	      out = items;
	    }

	    return out;
	  };
});

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



app.controller('myCtrl', function($scope, $http, mailFactory) {
    $scope.tags = [

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

getTrMessagesCtrl.$inject = [ '$scope', '$http', '$modal', 'uiGridConstants' ];
function getTrMessagesCtrl($scope, $http, $modal, uiGridConstants) {
	var vtm = this;

	

	vtm.serviceGridtrash = {
		paginationPageSizes: [5, 10, 15],
		paginationPageSize: 5,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		enableGridMenu : true,

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

	

}


