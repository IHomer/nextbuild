'use strict'

###*
 # @ngdoc function
 # @name zepCustomerPortal.controller:AboutCtrl
 # @description
 # # AboutCtrl
 # Controller of the zepCustomerPortal
###
angular.module('frontendApp')
  .service 'ReportResource', (Restangular) ->
    Restangular.setBaseUrl('http://localhost\:8400');

    @generateReport = ->
      Restangular.one('report').customPOST()

    @getReports =  ->
      Restangular.all('report').getList()

    @

