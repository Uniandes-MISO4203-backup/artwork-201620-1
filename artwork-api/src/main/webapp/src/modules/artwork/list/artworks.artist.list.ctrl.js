/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    
    var mod = ng.module("artworkModule");
    mod.controller("artistGalleryListCtrl", ["$scope", '$state','$stateParams',"artist","artworks", "artistArtworks","categories",
        function ($scope, $state, $params, artist, artworks, artistArtworks, categories) {
            $scope.records = artistArtworks;
            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            $scope.categorys = categories;
            $scope.artwork = {};
            $scope.artwork.category = [];
            $scope.artwork.images = []; 
            $scope.artwork["artist"] = artist;
           
            $scope.createArtwork = function(){
                artworks.post($scope.artwork).then(function () {
                    $scope.records = artworks.get();
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
            }
            
            $scope.addImage = function(image){
                var index = $scope.artwork.images.indexOf(image);
                if (index < 0) {
                    $scope.artwork.images.push(image);
                }
                $scope.image = "";
            }
            
            $scope.removeImage = function(image){
                var index = $scope.artwork.images.indexOf(image);
                if (index > -1) {
                    $scope.artwork.images.splice(index, 1);
                }
            }
           
            $scope.eliminarObra = function(artworkId){
                //TO-DO
                console.log("Redireccionando para editar obra");
            };

            this.pageChanged = function () {
                $state.go('artworkList', {page: this.currentPage});
            };
        }]);
    })
(window.angular);
