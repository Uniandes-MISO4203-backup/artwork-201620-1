/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {  
    var mod = ng.module("visitorModule");
    mod.controller("visitorGalViewCtrl", ["$scope",'artworks',
        function ($scope,artworks) {
          $scope.records=artworks;
          
    }]);
})(window.angular);
