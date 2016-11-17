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
 copies or substantial portions of the Software
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
        fields: {userName: {
                displayName: 'UserName',
                type: 'String',
                required: true
            }
            , email: {
                displayName: 'Email',
                type: 'String',
                required: true
            }
            , givenName: {
                displayName: 'Name',
                type: 'String',
                required: true
            }
            , middleName: {
                displayName: 'MiddleName',
                type: 'String',
                required: true
            }
            , surName: {
                displayName: 'Roles',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/admin/';
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
        }]);
})(window.angular);
