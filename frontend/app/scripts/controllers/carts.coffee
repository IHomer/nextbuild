'use strict'

###*
 # @ngdoc function
 # @name frontendApp.controller:MainCtrl
 # @description
 # # MainCtrl
 # Controller of the frontendApp
###
angular.module 'frontendApp'
  .controller 'MainCtrl', ($scope, ShoppingCartsResource) ->
    fetch = (id) ->
      if id
        ShoppingCartsResource.getShoppingCart(id).then (cart) ->
          $scope.selectedCart = cart
      else
        ShoppingCartsResource.getShoppingCarts().then (carts) ->
          $scope.carts = carts
    fetch()

    $scope.fetch = ->
      fetch()

    $scope.registerShoppingCart = ->
      if $scope.name
        ShoppingCartsResource.registerShoppingCart($scope.name).then ->
          $scope.name = undefined
          fetch()

    $scope.addItem = (item) ->
      if $scope.selectedCart and item
        ShoppingCartsResource.addItem($scope.selectedCart.id, item.name).then ->
          fetch($scope.selectedCart.id)

    $scope.removeItem = (item) ->
      if $scope.selectedCart and item
        ShoppingCartsResource.removeItem($scope.selectedCart.id, item.name).then ->
          fetch($scope.selectedCart.id)

    $scope.checkout = ->
      if $scope.selectedCart
        ShoppingCartsResource.checkoutShoppingCart($scope.selectedCart.id).then ->
          fetch($scope.selectedCart.id)

    $scope.cancel = ->
      if $scope.selectedCart
        ShoppingCartsResource.cancelShoppingCart($scope.selectedCart.id).then ->
          fetch($scope.selectedCart.id)

    $scope.accept = ->
      if $scope.selectedCart
        ShoppingCartsResource.acceptShoppingCart($scope.selectedCart.id).then ->
          fetch($scope.selectedCart.id)

    $scope.reject = ->
      if $scope.selectedCart
        ShoppingCartsResource.rejectShoppingCart($scope.selectedCart.id).then ->
          fetch($scope.selectedCart.id)

    $scope.selectShoppingCart = (cart) ->
      fetch(cart.id)

    $scope.items = [
      id: 1
      name: "Item 1"
    ,
      id: 2
      name: "Item 2"
    ,
      id: 3
      name: "Item 3"
    ,
      id: 4
      name: "Item 4"
    ,
      id: 5
      name: "Item 5"
    ]

