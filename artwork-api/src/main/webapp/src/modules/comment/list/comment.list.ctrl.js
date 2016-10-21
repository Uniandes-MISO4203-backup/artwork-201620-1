/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('commentModule');
    mod.controller('commentListCtrl', ["$scope", 'comments', 'artwork', 'client', 'itemModel',
        function ($scope, comments, artwork, client, itemModel) {
            $scope.artwork = artwork;
            console.log(artwork);
            var getAllComments = function (id) {
                comments.customGET("", {artworkId: id}).then(function (response) {
                    $scope.comments = response;
                });
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
        }
    ]);
})(window.angular);


