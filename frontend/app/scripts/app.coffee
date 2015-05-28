'use strict'

###*
 # @ngdoc overview
 # @name frontendApp
 # @description
 # # frontendApp
 #
 # Main module of the application.
###
angular
  .module 'frontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
    'restangular'
  ]
  .config ($routeProvider) ->
    $routeProvider
      .when '/',
        templateUrl: 'views/carts.html'
        controller: 'MainCtrl'
      .when '/report',
        templateUrl: 'views/report.html'
        controller: 'ReportCtrl'
      .otherwise
        redirectTo: '/'

