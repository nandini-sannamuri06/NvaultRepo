
angular.module('mailService',[]).factory('mailFactory',function($http){
	return{
		sendMail: function(mailObj){
			$http.post('/messages/mail/send', mailObj)
			.then(mailSendSuccess)
			.catch(mailSenfFailMsg);
		},
	}
});


angular.module('myApp', ['ngTagsInput','mailService'])
.controller('myCtrl', function($scope, $http, mailFactory) {
    $scope.tags = [
//        { text: 'vmeesala@nisum.com' },
//        { text: 'skaranam@nisum.com' },
//        { text: 'javeed@nisum.com' }
    ];
    $scope.loadTags = function(query) {
         return $http.get('/tags?query=' + query);
    };
    
    $scope.sendMail = function(mailObj){
    	mailObj.toAddress = $scope.tags;
    	mailFactory.sendMail(mailObj);
	}
});
