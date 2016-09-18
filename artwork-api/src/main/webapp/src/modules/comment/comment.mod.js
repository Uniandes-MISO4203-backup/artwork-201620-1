/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng){
    
    var mod = ng.module('commentModule', ['ngCrud', 'ui.router','artworkModule','itemModule' ]);
    
    mod.constant('commentModel', {
        name: 'comment',
        displayName: 'Comment',
		url: 'comments',
        fields: {            
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            comment: {
                displayName: 'Comment',
                type: 'String',
                required: true
            },
            artwork: {
                displayName: 'Artwork',
                type: 'Reference',
                model: 'artworkModel',
                options: [],
                required: true
            }}
    });
    mod.config(['$stateProvider',
        function ($sp){
            var basePath = 'src/modules/comment/';
          
            
            $sp.state('comment',{
               url:'/comment/:artworkId',
              
               
               views:{ mainView: {
                       templateUrl: basePath + 'comment.tpl.html',
                       controller: 'commentCtrl'
                       
                   }},
                resolve: {
                    model: 'artworkModel',
                    artworks: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }],               }
          
            
               
               
            });
           $sp.state('commentList',{
               url: '/list',
               parent: 'comment',
               views: {
                   commentView: {
                       templateUrl: basePath + 'list/comment.list.tpl.html',
                       controller : 'commentListCtrl'
                   }
                   
               },
                resolve: {
                    model: 'commentModel',
                    comments: ['Restangular', '$stateParams', function (r, $params) {
                            return r.all('/comments').getList($params);
                    }],
                    artwork:['Restangular', '$stateParams', function (r, $params) {
                        return r.one('/artworks', $params.artworkId).get();
                    }],
                    client:['Restangular', '$stateParams', function (r) {
                            return r.all("clients").getList();
                    }],
                    itemModel:'itemModel'
                }
                
           }); 
           
        }
    ]);
})(window.angular);
  


