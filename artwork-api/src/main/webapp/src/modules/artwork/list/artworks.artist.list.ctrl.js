/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) { 
    var mod = ng.module("artworkModule");
     
    mod.controller("artistGalleryListCtrl", ["$rootScope","$scope", '$state','$stateParams',"artist","artworks", "artistArtworks","categories","Restangular",
        function ($rootScope, $scope, $state, $params, artist, artworks, artistArtworks, categories, Restangular) {
            $scope.records = artistArtworks;
            
            $scope.obraAEliminar = [];
            //Paginación
            $scope.currentPage = 0;
            $scope.pageSize = 6;
            $scope.alerts = []            
            $scope.numberOfPages=function(){            
             return Math.ceil($scope.records.length/$scope.pageSize);                
            };
            $scope.galView = "latest";            
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = artistArtworks.totalRecords;
            
            $scope.categorys = categories;
            $scope.artwork = {};
            $scope.artwork.category = [];
            $scope.artwork.images = []; 
            $scope.artwork["artist"] = artist;
            $scope.createArtwork = function(){
                artworks.post($scope.artwork).then(function () {
                    artist.getList("artworks", {userName: $rootScope.usuario.$object.userName}).then(function(response){
                        $scope.records = response;
                    });
                });
            };
            $scope.addCategory = function(category){
               var index = $scope.categorys.indexOf(category);
                if (index > -1) {
                    $scope.categorys.splice(index, 1);
                    $scope.artwork.category.push(category);
                }
            };
            $scope.removeCategory = function(category){
                var index = $scope.artwork.category.indexOf(category);
                if (index > -1) {
                    $scope.artwork.category.splice(index, 1);
                    $scope.categorys.push(category);
                }
            };
            $scope.addImage = function(image){
                var index = $scope.artwork.images.indexOf(image);
                if (index < 0) {
                    $scope.artwork.images.push(image);
                }
                $scope.image = "";
            };
            $scope.removeImage = function(image){
                var index = $scope.artwork.images.indexOf(image);
                if (index > -1) {
                    $scope.artwork.images.splice(index, 1);
                }
            };
            $scope.eliminarObra = function(){
                console.log("Redireccionando para borrar obra: "+$scope.obraAEliminar);
                Restangular.all("artworks").customDELETE($scope.obraAEliminar).then(function (rc) {
                    $state.go('artistGallery', null, {reload: true});                    
                    $scope.showSuccess("Obra removida");
                    $scope.obraAEliminar = [];
                });
            };
            this.pageChanged = function () {
                $state.go('artistGallery', {page: this.currentPage});
            };
            
            $scope.setearObraAEliminar = function(artworkId){
               $scope.obraAEliminar = artworkId;
            };
            
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            $scope.showSuccess = function (msg) {
                showMessage(msg, "success");
            };
          
        }]);
})(window.angular);
