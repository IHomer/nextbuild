'use strict'

###*
 # @ngdoc function
 # @name zepCustomerPortal.controller:AboutCtrl
 # @description
 # # AboutCtrl
 # Controller of the zepCustomerPortal
###
angular.module('frontendApp')
  .service 'ShoppingCartsResource', (Restangular) ->
    Restangular.setBaseUrl('http://localhost\:8400');

    @registerShoppingCart = (name) ->
      Restangular.one('cart').customPOST
        name: name

    @addItem = (id, item) ->
      Restangular.one('cart', id).one('add').customPUT
        name: item

    @removeItem = (id, item) ->
      Restangular.one('cart', id).one('remove').customPUT
        name: item

    @checkoutShoppingCart = (id) ->
      Restangular.one('cart', id).one('checkout').customPUT()

    @cancelShoppingCart = (id) ->
      Restangular.one('cart', id).one('cancel').customPUT()

    @acceptShoppingCart = (id) ->
      Restangular.one('cart', id).one('accept').customPUT()

    @rejectShoppingCart = (id) ->
      Restangular.one('cart', id).one('reject').customPUT()

    @getShoppingCarts = ->
      Restangular.all('cart').getList()

    @getShoppingCart = (id) ->
      Restangular.one('cart', id).customGET()


    @

