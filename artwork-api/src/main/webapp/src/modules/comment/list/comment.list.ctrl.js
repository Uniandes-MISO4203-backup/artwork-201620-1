/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('commentModule');
    mod.controller('commentListCtrl', ["$scope", 'comments', 'artwork', 'client', 'itemModel', 'Restangular', "$rootScope",
        function ($scope, comments, artwork, client, itemModel, Restangular, $rootScope) {
            
            
            
            $scope.artwork = artwork;
            $scope.mean = 0;
            $scope.qualifications = [];            
            console.log(artwork);
            var getAllComments = function (id) {
                comments.customGET("", {artworkId: id}).then(function (response) {
                    $scope.comments = response;
                });
            };
            
            $scope.currentPageC = 0;
            $scope.pageSizeC = 6;
            $scope.numberOfPages=function(){
                return Math.ceil($scope.comments.length/$scope.pageSizeC);                
            };
            
            $scope.increasePage = function(){
                $scope.currentPageC = $scope.currentPageC+1;
            };
            
            $scope.decreasePage = function(){
                $scope.currentPageC = $scope.currentPageC-1;
            };
            
            getAllComments($scope.artwork.id);
            $scope.comment = {};
            $scope.commentSent = {};
            $scope.submitComment = function (comment) {
                $scope.commentSent.name = comment.email;
                $scope.commentSent.comment = comment.description;
                $scope.commentSent.artwork = $scope.artwork.id;
                comments.post(angular.toJson($scope.commentSent), {artworkId: $scope.artwork.id}).then(function () {
                    getAllComments($scope.artwork.id);
                    $scope.comment.email = "";
                    $scope.comment.description = "";
                });
            };
            $scope.addToCart = function () {
                itemModel['name'] = artwork.name;
                itemModel['qty'] = $scope.quantity;
                itemModel['artwork'] = artwork;
                client[0].post("shopping/cart", JSON.stringify(itemModel));
                client[0].getList("shopping/cart").then(function () {
                    $scope.cartAdded = true;
                });
            };
            
            $scope.getArtworkQualificationsMean = function(){
                var qual = [];
                var mean = 0.0;
                if($scope.qualifications.length>0){
                    for(var i = 0; i < $scope.qualifications.length; i++ ){
                        var calificacion = $scope.qualifications[i];
                        if(calificacion.artwork.id===artwork.id){
                            qual.push(calificacion);
                            mean = mean + calificacion.qualification;
                        }
                    }
                    mean = mean/$scope.qualifications.length;
                }
                //return qual;
                return mean.toFixed(2);
            };
            
            $scope.isArtworkNotQualificated = function(){
                var userClient = $rootScope.usuario.$object.email;
                for(var i = 0; i < $scope.qualifications.length; i++ ){
                    var calificacion = $scope.qualifications[i];
                    if(calificacion.artwork.id===$scope.artwork.id && calificacion.userClient===userClient){
                        return false;
                    }
                }
                return true;
            };
            
            $scope.calificar = function() {
                calificacionUsuario = {};
                calificacionUsuario['qualification'] = $scope.ratingValue;
                calificacionUsuario['userClient'] = $rootScope.usuario.$object.email;
                calificacionUsuario['artworkId'] = artwork.id;               
                Restangular.all("qualifications").customPOST({}, 'crear', calificacionUsuario, {}).then(function (rc) {
                     console.log("Se añadio calificacion");
                     $scope.getQualifications();
                });
            };
            
            $scope.getQualifications = function () {
                Restangular.all("qualifications").customGET('').then(function (response) {
                    if (response.length>0) {
                        $scope.qualifications = response;
                    }
                });
            };
            
            $scope.getQualifications();

            $scope.maxValue = 5; // default test value
        }
    ]);
    
    //We already have a limitTo filter built-in to angular,
    //let's make a startFrom filter
    mod.filter('startFromC', function() {
        return function(input, start) {
            start = +start; //parse to int
            if(input){
                return input.slice(start);
            }
        };
    });
})(window.angular);


