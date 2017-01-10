'use strict';

/**
 * @ngdoc function
 * @name contextMenuApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the contextMenuApp
 */
var app = angular.module('contextMenuApp');
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

app.service('shareService', function() {
	  var shareService =this;
	  
	  shareService.getSelect="";

	});
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
    app.controller('shareController', function ($scope,ModalService,shareService) {

        //temp for table/preview
        $scope.items = [];
        for (var i = 1; i <= 3; i++) {
		            $scope.items.push({
			name : 'Name ', address: 'Address '})
        }
        //Menu Items Array
        $scope.menus = [
            {label: 'View', action: 'callView', active: true},
            {label: 'Delete', action: 'deleteItem', active: true},
            {label: 'Send', action: 'sendItem', active: false},
            {label: 'Share', action: 'shareItem', active: true,value:'1'},
            {label: 'Active', action: 'deactivate', active: false}
        ];

        //Sample Button Dropdown
        $scope.buttonMenu = [
            {label: 'View Large', action: 'callView', active: true},
            {label: 'Delete this item', action: 'deleteItem', active: true}
        ];

        $scope.deleteItem = function (arg) {
            console.warn('deleted ...')
        };

        $scope.callView = function (arg) {
            console.info('View Call, another method')
        };
        $scope.shareItem = function (arg) {
        	shareService.getSelect = arg.id;

        	 $('.custom-context-menu').remove();
        	 ModalService.showModal({
            templateUrl: "fileShare.html",
            controller: "fileShare"
          }).then(function(modal) {
            modal.close.then(function(result) {
              $scope.customResult = "All good!";
            });
          });
            
        };

    });
    
  app.controller('fileShare', ['$scope','shareService','$http',  'close', function($scope, shareService,$http,close) {
	  $scope.showSelected=true;
	  $scope.close = close;
	  	$scope.idShow = shareService.getSelect;
	  	
	  
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
      
	  	    $scope.share = function() {	
	  	    	
	  	    	var toMail=[];
	  	    	var i=0;
	  	    	var fileName = "SampleDOCFile_100kb.doc";
	  	    	var url="";
	  	    	$http.get('/resource/shareDownload/',{params: {'filename': fileName}}).success(function(response) {
	  	    		
	  	    		url = response; 
	  	    		
	  	    		
	  	    		for(var i = 0; i <= $scope.multipleDemo.selectedPeople.length; i++){
		  	    		alert(":::"+url);
		  	    		 $http.post('/resource/shareMail',{
			    			 'url':url,
			    			 'note': $scope.note,
			    			 'mailId': $scope.multipleDemo.selectedPeople[i].emailId
			    		 }).then(function(resp){
			    		
			    		 });
		  	    		i++;
		  	    	}
	  	    		
	  	    	}).error(function(response) { 
	  	    		
	  	    		alert('Cannot archieve row (error in console)'); console.dir(response);
	  	    		
	  	    	});
	  	    
	  	    	close();
	
	}
      
	  
    	 

    	}]);

