/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('userModule');

    mod.controller('userEditCtrl', ['$scope', '$http', '$rootScope', '$state',
        function ($scope, $http, $rootScope, $state) {
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };
            $scope.changePassword = function (password) {
                var req = {
                    method: 'POST',
                    url: 'api/change',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    params: {newpass: password.new}
                };
                var ar = password.new.split('');
                $scope.upperCaseError = true;
                $scope.numError = true;
                for (var st in ar) {
                    if (ar[st] === ar[st].toUpperCase() && isNaN(ar[st])) {

                        $scope.upperCaseError = false;
                    }
                    if (!isNaN(ar[st])) {

                        $scope.numError = false;
                    }

                }
                if (!$scope.upperCaseError && !$scope.numError) {
                    $http(req).then(function () {

                        $http.get('api/users/logout').then(function () {
                            $rootScope.$broadcast('logged-out');
                            $state.go('login');
                        });
                    });
                }
            };
        }
    ]);
})(window.angular);





