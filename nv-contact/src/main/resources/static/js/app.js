angular.module("nvault", ['ngRoute'])
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
			});

//angular
//		.module('nvault', [ 'ngRoute', 'register' ])
//		.config(
//
//				function($routeProvider, $httpProvider, $locationProvider) {
//
//					$locationProvider.html5Mode(true);
//
//					$routeProvider.when('/register', {
//						templateUrl : 'js/register/register.html',
//						controller : 'RegisterController',
//						controllerAs : 'vm'
//					}).otherwise('/');
//
//					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
//
//				}).run(function(auth) {
//
//			// Initialize auth module with the home page and login/logout path
//			// respectively
//			auth.init('/', '/login', '/logout');
//
//		});
