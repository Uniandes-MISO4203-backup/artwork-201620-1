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
    var mod = ng.module('wishlistModule', ['ngCrud','ui.router','itemModule']);
    mod.constant('wishlistModel', {
        name: 'wishlist',
        displayName: 'Wishlist',
        url: 'wishList',
        fields: {
            artwork: {
                displayName: 'Artwork',
                type: 'Reference',
                model: 'artworkModel',
                options: [],
                required: true
            },
            artist: {
                displayName: 'Artist',
                type: 'Reference',
                model: 'artistModel',
                options: [],
                required: true
            }
        }
    });
    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/wishlist/';

            $sp.state('wishlist', {
                url: '/wishlist',
                abstract: false,
                views: {
                     mainView: {
                        templateUrl: basePath + 'wishlist.tpl.html',
                        controller: 'wishlistCtrl'
                    }
                },
                resolve: {
                    model: 'wishlistModel'
                }
            });
            $sp.state('wishlistList', {
                url: '/list',
                parent: 'wishlist',
                model: 'wishlistModel',
                views: {
                    wishlistView: {
                        templateUrl: basePath + 'list/wishlist.list.tpl.html',
                        controller: 'wishlistListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    client:['Restangular', '$stateParams', function (r) {
                        return r.all("clients").getList().then(function(list){
                            return list[0];
                        });
                    }],
                    wishlist:['client','model', function(client, model){
                        return client.getList(model.url);
                    }],
                    itemModel: 'itemModel'
                }
            });
	}]);
})(window.angular);
