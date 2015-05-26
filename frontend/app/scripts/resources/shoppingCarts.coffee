'use strict'

###*
 # @ngdoc function
 # @name zepCustomerPortal.controller:AboutCtrl
 # @description
 # # AboutCtrl
 # Controller of the zepCustomerPortal
###
angular.module('frontendApp')
  .service 'ShoppingCardResource', (Restangular) ->
    @getShoppingCards = ->
      Restangular.all('shoppingCard').getList()

    @getShoppingCard = (id) ->
      Restangular.one('shoppingCard', id).customGET()


    @checkoutShoppingCard = (id) ->
      Restangular.one('shoppingCard', id).one('checkout').customGET()


    @addItem = (id, itemId) ->
      Restangular.one('shoppingCard', id).one('addItem').customPOST
        itemId: itemId

    @removeItem = (id, itemId) ->
      Restangular.one('shoppingCard', id).one('removeItem').customPOST
        itemId: itemId

    @

