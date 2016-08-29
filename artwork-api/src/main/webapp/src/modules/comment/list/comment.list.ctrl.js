/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
   var mod = ng.module('commentModule');
   
   
   mod.controller('commentListCtrl',["$scope","$state","$stateParams",'comments','model',
       function($scope, $state,$stateParams,comments,model){
          console.log(JSON.stringify($stateParams));
          var artSelection= $scope.records.filter(function(art){
              return art.id == $stateParams.artworkId;
          });
          $scope.artId=artSelection[0].id;
    $scope.artName=artSelection[0].name;
    $scope.artImage=artSelection[0].image;
    console.log($scope.artName);
        console.log($scope.artImage);
        console.log(artSelection[0].id);
        console.log(JSON.stringify(comments));
        $scope.comment={};
        $scope.commentSent={};
       
        
        
        $scope.submitComment= function(comment){
            $scope.commentSent.name=comment.email;
            $scope.commentSent.comment=comment.description;
            $scope.commentSent.artwork=$scope.artId;
           
            console.log(JSON.stringify($scope.commentSent));
            comments.post(angular.toJson($scope.commentSent));
        }
        
        $scope.commentDescription='';
        $scope.artworkId;
        
       }
       
       
   ]);
   
})(window.angular);


