/* 
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module('commentModule');
    
    mod.controller('commentCtrl', ['$scope', '$state', 'artworks', '$stateParams', 'Restangular',
        function ($scope, $state, artworks, $params, Restangular) {
            $scope.records = artworks;
            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = artworks.totalRecords;
            $scope.categorys = [];
            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/' + parentCategory).then(function (response) {
                    if (response.length > 0) {
                        $scope.categorys = response;
                    }
                });
            };
            $scope.filtrar = function (parentCategory) {
                $scope.getCategorys(parentCategory);
                Restangular.all("artworks").customGET(parentCategory).then(function (response) {
                    $scope.records = response;
                });
            };
            $scope.getCategorys("");
            $state.go('commentList');
        }]);
})(window.angular);
