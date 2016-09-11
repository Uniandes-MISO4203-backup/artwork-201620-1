(function (ng) {
    var mod = ng.module('roleModule', ['ngCrud']);
    mod.controller('roleCtrl', ['$rootScope', 'Restangular','$state','$scope', function ($rootScope, Restangular,$state,$scope) {

        $rootScope.auth = function () {
                var user = Restangular.all("users").customGET('me');
                
                $rootScope.usuario = user;
                
                console.log("USUARIO: "+user);
                user.then(function (response) {
                    if (response == null) {
                        $rootScope.category = false;
                        $rootScope.artist = false;
                        $rootScope.client = false;
                        $rootScope.product = false;
                        
                    } else {
                        
                        var roles = $rootScope.roles = response.roles;
                        if (roles.indexOf("client") !== -1) {
                            $rootScope.category = false;
                            $rootScope.artist = false;
                            $rootScope.client = true;
                            $rootScope.product = false;
                            
                        }
                        if (roles.indexOf("artist") !== -1) {
                            $rootScope.category = false;
                            $rootScope.artist = true;
                            $rootScope.client = false;
                            $rootScope.product = false;
                            
                        }
                        if (roles.indexOf("admin") !== -1) {
                            $rootScope.category = true;
                            $rootScope.artist = true;
                            $rootScope.client = true;
                            $rootScope.product = true;
                           
                        }
                    }
                });
            };
        $rootScope.auth();
        $rootScope.$on('logged-in', function () {
            $rootScope.auth();
        });
         
        $rootScope.$on('logged-out', function () {
            $rootScope.auth();
        });
                        

    }]);
})(window.angular);




