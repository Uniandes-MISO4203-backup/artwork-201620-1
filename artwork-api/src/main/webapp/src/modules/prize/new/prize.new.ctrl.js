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

    var mod = ng.module("prizeModule");

    mod.controller("prizeNewCtrl", ["$scope", "$state","prizes",
     function ($scope, $state,prizes) {
            $scope.prizeData={};
            $scope.colors=["#00A064","#027DD1","#FFCC5C","#EE553D","black"];
            $scope.trophies=["fa fa-certificate","fa fa-sun-o","fa fa-trophy"];
            $scope.prizeForm = function(prize){
             prize.date=new Date();
             prizes.post(prize).then(function(){
              $state.go("prizeList",prizes,{reload: true});
             });
            };
        }]);
})(window.angular);


