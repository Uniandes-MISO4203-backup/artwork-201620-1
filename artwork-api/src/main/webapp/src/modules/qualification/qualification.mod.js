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
    var mod = ng.module('qualificationModule', ['ngCrud', 'ui.router']);

    mod.constant('qualificationModel', {
        name: 'qualification',
        displayName: 'Qualification',
        url: 'qualifications',
        fields: {
            qualification: {
                displayName: 'Qualification',
                type: 'Integer',
                required: true
            },
            userClient: {
                displayName: 'User',
                type: 'String',
                required: true
            },
            artwork: {
                name: 'artwork',
                displayName: 'Artwork',
                type: 'Reference',
                url: 'artworks',
                options: [],
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/qualification/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('qualification', {
                url: '/qualifications?page&limit',
                abstract: true,
                views: {
                    mainView: {
                        templateUrl: basePath + 'qualification.tpl.html',
                        controller: 'qualificationCtrl'
                    }
                },
                resolve: {
                    model: 'qualificationModel',
                    qualifications: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]/*,
                    modelArtwork: 'artworkModel',
                    artworks: ['Restangular', 'modelArtwork', '$stateParams', function (r, modelArtwork, $params) {
                            return r.all(modelArtwork.url).getList();
                        }]*/
                }
            });
            $sp.state('qualificationList', {
                url: '/list',
                parent: 'qualification',
                views: {
                    qualificationView: {
                        templateUrl: basePath + 'list/qualification.list.tpl.html',
                        controller: 'qualificationListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('qualificationNew', {
                url: '/new/:artworkId',
                parent: 'qualification',
                views: {
                    qualificationView: {
                        templateUrl: basePath + 'new/qualification.new.tpl.html',
                        controller: 'qualificationNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('qualificationInstance', {
                url: '/{qualificationId:int}',
                abstract: true,
                parent: 'qualification',
                views: {
                    qualificationView: {
                        template: '<div ui-view="qualificationInstanceView"></div>'
                    }
                },
                resolve: {
                    qualification: ['qualifications', '$stateParams', function (qualifications, $params) {
                            return qualifications.get($params.qualificationId);
                        }]
                }
            });
            $sp.state('qualificationDetail', {
                url: '/details',
                parent: 'qualificationInstance',
                views: {
                    qualificationInstanceView: {
                        templateUrl: baseInstancePath + 'detail/qualification.detail.tpl.html',
                        controller: 'qualificationDetailCtrl'
                    }
                }
            });
            $sp.state('qualificationEdit', {
                url: '/edit',
                sticky: true,
                parent: 'qualificationInstance',
                views: {
                    qualificationInstanceView: {
                        templateUrl: baseInstancePath + 'edit/qualification.edit.tpl.html',
                        controller: 'qualificationEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('qualificationDelete', {
                url: '/delete',
                parent: 'qualificationInstance',
                views: {
                    qualificationInstanceView: {
                        templateUrl: baseInstancePath + 'delete/qualification.delete.tpl.html',
                        controller: 'qualificationDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
