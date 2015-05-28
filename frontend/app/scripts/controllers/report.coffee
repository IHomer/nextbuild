'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the frontendApp
###
angular.module 'frontendApp'
  .controller 'ReportCtrl', ($scope, ReportResource) ->
    $scope.generateReport = ->
      ReportResource.generateReport().then ->
        console.log 'Generate Report'

    $scope.getReports = ->
      ReportResource.getReports().then (items) ->
        $scope.items = items

    $scope.getReports()
