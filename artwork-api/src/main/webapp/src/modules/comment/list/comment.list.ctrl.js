/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
   var mod = ng.module('commentModule');
   
   
   mod.controller('commentListCtrl',["$scope","$state","$stateParams",'comments','model',
       function($scope, $state,$stateParams,comments,model){
          
          var artSelection= $scope.records.filter(function(art){
              return art.id == $stateParams.artworkId;
          });
    $scope.artName=artSelection[0].name;
    $scope.artImage=artSelection[0].image;
    console.log($scope.artName);
        console.log($scope.artImage);
        console.log(artSelection[0].id);
        console.log(JSON.stringify(comments));
        $scope.comment={};
        
        $scope.submitComment= function(comment){
            $scope.comment=comment;
            console.log($scope.comment.email);
            
        }
        
        $scope.commentDescription='';
        $scope.artworkId;
        
       }
       
       
   ]);
   
})(window.angular);


