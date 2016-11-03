/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
 var mod = ng.module('userModule');

 mod.controller('userPurchasesCtrl', ['$scope', 'Restangular', '$stateParams', 'clients', 'items',
  function ($scope, Restangular, $stateParams, clients, items) {
   $scope.purchases = items;
   $scope.getTotal = function (purchase) {
    var total = 0;
    for (var i = 0; i < purchase.items.length; i++) {
     total += purchase.items[i].artwork.price;
    }
    return total;
   };
   $scope.states = new Array($scope.purchases.length).fill(false);
   $scope.changeState = function (index) {
    $scope.states[index] = !$scope.states[index];
   };
  }
 ]);
})(window.angular);





