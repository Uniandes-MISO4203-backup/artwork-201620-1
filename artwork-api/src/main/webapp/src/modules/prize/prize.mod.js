
(function (ng) {
    var mod = ng.module('prizeModule', ['ngCrud', 'ui.router']);
    
    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/prize/';
            
           $sp.state('prize',{
            url: '/prizes&page&limit',
            
            views: {
             mainView:{
             templateUrl: basePath + "prize.tpl.html",
             }
            
           },
            resolve :{
             prizes :['Restangular','$stateParams', function(r,$params){
               return r.all('prizes').getList($params);
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
        }]);
})(window.angular);

