/*
 The MIT License (MIT
 Copyright (c) 2015 Los Andes University
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
(function (ng) {
    var mod = ng.module('shoppingCartModule');

    mod.controller('shoppingCartCtrl', ['$scope', 'client', 'payments', 'shopping', 'Restangular',
        function ($scope, client, payments, shopping, Restangular) {
            $scope.subtotal = function () {
                var total = 0;
                angular.forEach(shopping, function (item) {
                    total += item.qty * item.artwork.price;
                });
                return total;
            };
            $scope.purchase = {};
            $scope.purchase["items"] = shopping;
            //Alertas
            $scope.alerts = [];

            $scope.makePurchase = function () {
                payments.post(angular.toJson($scope.purchase), {clientsId: client[0].id}).then(function () {
                    $scope.showSuccess("Compra realizada");
                    $scope.purchase["items"] = [];
                });
            };
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            /* Función showMessage: Recibe el mensaje en String y
             * su tipo con el fin de almacenarlo en el array $scope.alerts.
             */
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            $scope.showError = function (msg) {
                showMessage(msg, "danger");
            };

            $scope.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            $scope.deleteFromCart = function (item) {
                client.customDELETE("wishList/"+ item.id).then(function () {
                    var index = $scope.purchase["items"].indexOf(item);
                    $scope.purchase["items"].splice(index, 1);
                });
            };
        }]);

})(window.angular);
