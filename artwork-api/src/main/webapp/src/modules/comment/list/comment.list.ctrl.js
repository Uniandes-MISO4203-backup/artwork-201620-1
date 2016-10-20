/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('commentModule');
    mod.controller('commentListCtrl', ["$scope", "$stateParams", 'comments', 'artwork', 'client', 'itemModel',
        function ($scope, $stateParams, comments, artwork, client, itemModel) {
            var getAllComments = function (id) {
                comments.customGET("", {artworkId: id}).then(function (response) {
                    $scope.comments = response;
                });
            };
            var artSelection = $scope.records.filter(function (art) {
                return art.id+"" === $stateParams.artworkId;
            });
            console.log("El arte: "+JSON.stringify(artSelection[0]));
            $scope.artId = artSelection[0].id;
            $scope.artName = artSelection[0].name;
            $scope.artImage = artSelection[0].image;
            $scope.artPrice = artSelection[0].price;
            $scope.artWidth = artSelection[0].width;
            $scope.artHeight = artSelection[0].height;
            $scope.nameArtist = artSelection[0].artist.name;
            getAllComments($scope.artId);
            $scope.comment = {};
            $scope.commentSent = {};
            $scope.submitComment = function (comment) {
                $scope.commentSent.name = comment.email;
                $scope.commentSent.comment = comment.description;
                $scope.commentSent.artwork = $scope.artId;
                comments.post(angular.toJson($scope.commentSent), {artworkId: $scope.artId}).then(function () {
                    getAllComments($scope.artId);
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
        }
    ]);
})(window.angular);


