/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
    var mod = ng.module('commentModule');
    
    mod.controller('commentCtrl',['$scope','$state','artworkModel','$stateParams','$rootScope',
       function ($scope,$state,artworkModel,$stateParams,$rootScope) {
           alert($rootScope.arts);
   
    
  $state.go('commentList');
    
    
}]);
    
    })(window.angular);
