/*
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
 */
(function (ng) {
 var mod = ng.module('userModule');

 mod.controller('userPurchasesCtrl', ['$scope', 'Restangular', '$stateParams', 'clients', 'items',
  function ($scope, Restangular, $stateParams, clients, items) {
   $scope.purchases = items;
   
   $scope.currentPage = 0;
   $scope.pageSize = 10;
   $scope.numberOfPages=function(){
       return Math.ceil($scope.purchases.length/$scope.pageSize);                
   };
   
   
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
    
    //We already have a limitTo filter built-in to angular,
    //let's make a startFrom filter
    mod.filter('startFrom', function() {
        return function(input, start) {
            start = +start; //parse to int
            if(input){
                return input.slice(start);
            }
        };
    });
})(window.angular);





