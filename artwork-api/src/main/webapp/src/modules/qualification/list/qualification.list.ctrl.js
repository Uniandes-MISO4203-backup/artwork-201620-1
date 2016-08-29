/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
   var mod = ng.module('qualificationModule');
   
   mod.controller('qualificationListCtrl',["$scope","$state",'$rootScope',
       function($scope, $state, $rootScope){
           alert($rootScope.arts);
           
       }
   ]);
   
})(window.angular);
