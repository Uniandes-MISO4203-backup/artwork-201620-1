/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    
    var mod = ng.module("artworkModule");
    mod.controller("artworkListCtrl", ["$rootScope","$scope", '$state', 'artworks', '$stateParams','Restangular','itemModel','client','latest',
        function ($rootScope, $scope, $state, artworks, $params,Restangular, itemModel, client, latest, item) {
            $scope.records = artworks;
            for(var i in $scope.records){
                if($scope.records[i]){
                    $scope.records[i].isInWishlist = false;
                    for(var j in $scope.records[i].items){
                        if($scope.records[i].items[j]){
                            if($scope.records[i].items[j].userName === $rootScope.usuario.$$state.value.userName){
                                $scope.records[i].isInWishlist = true;
                                $scope.records[i].idItem = $scope.records[i].items[j].id;
                                break;
                            }
                        }
                   
                    }
                }
            
            }
            $scope.currentPage = 0;
            $scope.pageSize = 9;
            $scope.numberOfPages=function(){
                return Math.ceil($scope.records.length/$scope.pageSize);                
            };
            
            
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
            
            $scope.addToWishlist = function (artwork) {
                itemModel['name'] = artwork.name;
                itemModel['qty'] = 1;
                itemModel['artwork'] = artwork;
                itemModel['shoppingCart'] = false;
                client.post("shopping/wishlist", JSON.stringify(itemModel));
                artwork.isInWishlist = true;
                alert("Obra agregada a la wishlist");
            };
            
            $scope.removeWishlist = function (artwork) {
                itemModel['id'] = artwork.idItem;
                client.customDELETE("wishList/"+ artwork.idItem).then(function (rc) {
                    artwork.isInWishlist = false;
                    alert("Obra removida del wishlist");
                });
                /*
                client.delete("shopping/?itemId="+artwork.idItem,{}).then(function(rc){
                     if(rc){
                        artwork.isInWishlist = false;
                        alert("Obra removida del wishlist");
                    } else {
                        alert("Obra no removida del wishlist");
                    }
                });
                
                client[0].customPUT({},"wishList/"+ artwork.idItem, JSON.stringify(item),{}).then(function (rc) {
                   
                });
                */
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
    
    //We already have a limitTo filter built-in to angular,
    //let's make a startFrom filter
    mod.filter('startFrom', function() {
        return function(input, start) {
            start = +start; //parse to int
            return input.slice(start);
        };
    });
})(window.angular);
