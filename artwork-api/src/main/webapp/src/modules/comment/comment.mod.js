/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng){
    
    var mod = ng.module('commentModule', ['ngCrud', 'ui.router']);
    
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
            },
            client: {
                displayName: 'Client',
                type: 'Reference',
                model: 'clientModel',
                options: [],
                required: true
            }        }
    });
    mod.config(['$stateProvider',
        function ($sp){
            var basePath = 'src/modules/comment/';
          
            
            $sp.state('comment',{
               url:'/comment/:artworkId',
              
               
               views:{ mainView: {
                       templateUrl: basePath + 'comment.tpl.html',
                       controller: 'commentCtrl'
                       
                   }}
          
            
               
               
               
            });
           $sp.state('commentList',{
               url: '/list',
               parent: 'comment',
               views: {
                   commentView: {
                       templateUrl: basePath + 'list/comment.list.tpl.html',
                       controller : 'commentListCtrl'
                   }
               }
               
                   
               
           });   
        }
    ]);
})(window.angular);
  


