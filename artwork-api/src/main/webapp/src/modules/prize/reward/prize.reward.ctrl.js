/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("prizeModule");

    mod.controller("prizeRewardCtrl", ["$scope", "artworks","prizes","$state","Restangular", 
        function ($scope, artworks,prizes,$state,Restangular) {
           
            $scope.artworks = artworks;
            $scope.prizes = prizes;
            
            $scope.artworkInstance= function(artwork){
             $scope.artInstance=artwork;
             
            };
            $scope.addPrize= function(prize,artInstance){
             
             prize.artwork=artInstance;
             console.log(JSON.stringify(prize));
             prize.put().then(function(r){
              console.log(JSON.stringify(r));
              $state.go("prizeReward",artworks,{reload:true});
             });
       //      if(!$scope.artInstance.prizes){
         //     $scope.artInstance.prizes=[];
        //     }
             
   
        //     $scope.artInstance.prizes.push(prize);
        //    $scope.artInstance.customPUT("prizes",artInstance).then(function(){
        //      $state.go("prizeReward",artworks,{reload:true});
        //     });
            
           //  console.log(JSON.stringify(prize));
          //   console.log(JSON.stringify(artInstance));
            };
            
        }]);
})(window.angular);