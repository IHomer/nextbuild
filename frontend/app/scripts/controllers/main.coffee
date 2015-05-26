'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the frontendApp
###
angular.module 'frontendApp'
  .controller 'MainCtrl', ($scope, ShoppingCardResource) ->
    $scope.items = [
      id: 1
      name: "Item1"
    ,
      id: 2
      name: "Item2"
    ,
      id: 3
      name: "Item3"

      id: 4
      name: "Item4"
    ,
      id: 5
      name: "Item5"
    ,
      id: 6
      name: "Item6"
    ]
    $scope.cards = [
      name: "Test1"
      items: [
        id: 7
        name: "foo"
      ,
        id: 8
        name: "bar"
      ]
    ,
      name: "Test2"
      items:[
        id: 9
        name: "foo1"
      ,
        id: 10
        name: "bar1"
      ]
    ]


    $scope.setSelectedCard = (card) ->
      $scope.selectedCard = card

    $scope.addItem = (item) ->
      ShoppingCardResource.addItem($scope.selectedCard.id, item.id)
      $scope.selectedCard.items.push(item)
      ## DO SOMETHING WITH SERVICE TO BACKEND!
    $scope.removeItem = (item) ->
      ShoppingCardResource.removeItem($scope.selectedCard.id, item.id)
      _.remove $scope.selectedCard.items, (it) ->
        it.id == item.id

    $scope.checkout = ->
      ## DO SOMETHING WIT SERVICE TO BACKEND
      ShoppingCardResource.checkout($scope.selectedCard.id)
