/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
   var mod = ng.module('commentModule');
   
   mod.controller('commentListCtrl',["$scope","$state",'$rootScope',
       function($scope, $state, $rootScope){
           alert($rootScope.arts);
           
       }
   ]);
   
})(window.angular);
