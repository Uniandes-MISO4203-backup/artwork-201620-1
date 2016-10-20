/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('userModule');

    mod.controller('userPurchasesCtrl', ['$scope',
        function ($scope) {
            //Asignar resultado de compras de servicio
            $scope.purchases=[{items:[{a:1},{a:2},{a:3},{a:4}]},{items:[{a:5},{a:6},{a:7},{a:8}]},{items:[{},{},{},{}]},{items:[{},{},{},{}]}, {items:[{},{},{},{}]}]; 
            $scope.states = new Array($scope.purchases.length).fill(false);;
            $scope.changeState = function(index){
                $scope.states[index] = !$scope.states[index];
            };
        }
    ]);
})(window.angular);





