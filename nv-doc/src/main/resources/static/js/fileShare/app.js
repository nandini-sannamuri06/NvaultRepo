'use strict';

/**
 * @ngdoc overview
 * @name contextMenuApp
 * @description
 * # contextMenuApp
 *
 * Main module of the application.
 */
angular
  .module('contextMenuApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch','angularModalService','ui.select','mailService'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'contextMenu.html',
        controller: 'shareController'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
