/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    
    var mod = ng.module("artworkModule");
    mod.controller("artworkListCtrl", ["$rootScope","$scope", '$state', 'artworks', '$stateParams','Restangular','itemModel','client','latest',
        function ($rootScope, $scope, $state, artworks, $params,Restangular, itemModel, client, latest) {
            $scope.records = artworks;
            $scope.galView = 'latest';
            $scope.latest = latest;

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = artworks.totalRecords;
            $scope.categorys = [];
            $scope.qualifications = [];
            $scope.isArtworkNotQualificated = function(artworkId){
                var userClient = $rootScope.usuario.$object.email;
                for(var i = 0; i < $scope.qualifications.length; i++ ){
                    var calificacion = $scope.qualifications[i];
                    if(calificacion.artwork.id===artworkId && calificacion.userClient===userClient){
                        return false;
                    }
                }
                return true;
            };
            $scope.getArtworkQualifications = function(artworkId){
                var qual = [];
                for(var i = 0; i < $scope.qualifications.length; i++ ){
                    var calificacion = $scope.qualifications[i];
                    if(calificacion.artwork.id===artworkId){
                        qual.push(calificacion);
                    }
                }
                return qual;
            };
            $scope.getQualifications = function () {
                Restangular.all("qualifications").customGET('').then(function (response) {
                    if (response.length>0) {
                        $scope.qualifications = response;
                    }
                });
            };
            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/'+parentCategory).then(function (response) {
                    if (response.length>0) {
                        $scope.categorys=response;
                    }
                });
            };
            $scope.filtrar = function (parentCategory) {
                $scope.getCategorys(parentCategory);
                Restangular.all("artworks").customGET(parentCategory).then(function (response) {
                    $scope.records=response;
                });
            };
            
            $scope.filtrarPorArtista = function(artist){
                Restangular.all("artworks").customGET("",{artist:artist}).then(function (response) {          
                    $scope.records=response;
                }); 
            };
            
            $scope.getCategorys("");
            $scope.getQualifications();

            this.pageChanged = function () {
                $state.go('artworkList', {page: this.currentPage});
            };
            
            $scope.addToCart = function (artwork) {
                itemModel['name'] = artwork.name;
                itemModel['qty'] = 1;
                itemModel['artwork'] = artwork;
                client.post("shopping/cart", JSON.stringify(itemModel));
                alert("Obra agregada a carrito");
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
