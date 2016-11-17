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
    var mod = ng.module('prizeModule', ['ngCrud', 'ui.router']);
    
    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/prize/';
            
           $sp.state('prize',{
            url: '/prizes&page&limit',
            
            views: {
             mainView:{
             templateUrl: basePath + "prize.tpl.html"
             }
            
           },
            resolve :{
             prizes :['Restangular','$stateParams', function(r,$params){
               return r.all('prizes').getList($params);
             }],
            artworks:['Restangular', function(r){
              return r.all('artworks').getList();
            }]
            }
           });
       $sp.state('prizeList',{
        url: '/list',
        parent: 'prize',
        views: {
         prizeView:{
         templateUrl: basePath + "list/prize.list.tpl.html",
             controller: 'prizeListCtrl' 
         }
        }
       });
       $sp.state('prizeNew',{
         url: '/new',
         parent: 'prize',
         views: {
          prizeView: {
           templateUrl: basePath + "new/prize.new.tpl.html",
           controller: "prizeNewCtrl"
           
          }
         }
       });
       $sp.state('prizeReward',{
        url: '/reward',
        parent: 'prize',
        views:{
         prizeView:{
         templateUrl: basePath + "reward/prize.reward.tpl.html",
         controller: 'prizeRewardCtrl'
        }
        }
       });
        }]);
})(window.angular);

