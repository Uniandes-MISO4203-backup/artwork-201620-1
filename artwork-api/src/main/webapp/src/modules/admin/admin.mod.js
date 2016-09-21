/*
The MIT License (MIT)

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
    var mod = ng.module('adminModule', ['ngCrud', 'ui.router']);

    mod.constant('adminModel', {
        name: 'admin',
        displayName: 'Admin',
		url: 'admins',
        fields: {            userName: {
                displayName: 'UserName',
                type: 'String',
                required: true
            } 
            ,  email: {
                displayName: 'Email',
                type: 'String',
                required: true
            }
            ,  givenName: {
                displayName: 'Name',
                type: 'String',
                required: true
            }
            ,  middleName: {
                displayName: 'MiddleName',
                type: 'String',
                required: true
            }
            ,  surName: {
                displayName: 'Roles',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/admin/';
//            var baseInstancePath = basePath + 'instance/';

            $sp.state('admin', {
                url: '/admins?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'admin.tpl.html',
                        controller: 'adminCtrl'
                    }
                },
                resolve: {
                    model: 'adminModel',
                    admins: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('adminList', {
                url: '/list',
                parent: 'admin',
                views: {
                    adminView: {
                        templateUrl: basePath + 'list/admin.list.tpl.html',
                        controller: 'adminListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
           
//            $sp.state('artistNew', {
//                url: '/new',
//                parent: 'artist',
//                views: {
//                    artistView: {
//                        templateUrl: basePath + 'new/artist.new.tpl.html',
//                        controller: 'artistNewCtrl',
//                        controllerAs: 'ctrl'
//                    }
//                }
//            });
//            $sp.state('artistInstance', {
//                url: '/{artistId:int}',
//                abstract: true,
//                parent: 'artist',
//                views: {
//                    artistView: {
//                        template: '<div ui-view="artistInstanceView"></div>'
//                    }
//                },
//                resolve: {
//                    artist: ['artists', '$stateParams', function (artists, $params) {
//                            return artists.get($params.artistId);
//                        }]
//                }
//            });
//            $sp.state('artistDetail', {
//                url: '/details',
//                parent: 'artistInstance',
//                views: {
//                    artistInstanceView: {
//                        templateUrl: baseInstancePath + 'detail/artist.detail.tpl.html',
//                        controller: 'artistDetailCtrl'
//                    }
//                }
//            });
//            $sp.state('artistEdit', {
//                url: '/edit',
//                sticky: true,
//                parent: 'artistInstance',
//                views: {
//                    artistInstanceView: {
//                        templateUrl: baseInstancePath + 'edit/artist.edit.tpl.html',
//                        controller: 'artistEditCtrl',
//                        controllerAs: 'ctrl'
//                    }
//                }
//            });
//            $sp.state('artistDelete', {
//                url: '/delete',
//                parent: 'artistInstance',
//                views: {
//                    artistInstanceView: {
//                        templateUrl: baseInstancePath + 'delete/artist.delete.tpl.html',
//                        controller: 'artistDeleteCtrl',
//                        controllerAs: 'ctrl'
//                    }
//                }
//            });
	}]);
})(window.angular);
