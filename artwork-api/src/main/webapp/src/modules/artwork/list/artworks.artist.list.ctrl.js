/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    
    var mod = ng.module("artworkModule");
    mod.controller("artistGalleryListCtrl", ["$rootScope","$scope", '$state','$stateParams','Restangular',
        function ($rootScope, $scope, $state, $params,Restangular) {
            $scope.records = [];
            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            $scope.categorys = [];
            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/'+parentCategory).then(function (response) {
                    if (response.length>0) {
                        $scope.categorys=response;
                    }
                });
            };
            
            $scope.getArtworks = function(){
                var artistUserName = $rootScope.usuario.$object.userName;	
                Restangular.all("artists").customGET("artworks",{userName:artistUserName}).then(function (response) {          
                                console.log("Se obtuvieron las obras del artista");
                                $scope.records = response;
                            }); 
            
            };                                     
                            
            /*
            $scope.filtrar = function (parentCategory) {
                $scope.getCategorys(parentCategory);
                Restangular.all("artworks").customGET(parentCategory).then(function (response) {
                    $scope.records=response;
                });
            };
            */
           
           $scope.redireccionarParaAñadir = function(){
               //TO-DO
               console.log("Redireccionando para añadir obra");
           };
           
           $scope.eliminarObra = function(artworkId){
               //TO-DO
               console.log("Redireccionando para editar obra");
           };
            
            $scope.getCategorys("");
            $scope.getArtworks();

            this.pageChanged = function () {
                $state.go('artworkList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('artworkNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                cancel: {
                    displayName: 'Go back',
                    icon: 'arrow-left',
                    fn: function () {
                        $state.go('artistDetail');
                    }
                }

            };
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('artworkDetail', {artworkId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('artworkEdit', {artworkId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('artworkDelete', {artworkId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);
