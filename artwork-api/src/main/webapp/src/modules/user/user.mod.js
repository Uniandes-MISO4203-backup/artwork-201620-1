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
 var mod = ng.module('userModule', ['ngCrud', 'ui.router']);

 mod.constant('userModel', {
  name: 'user',
  displayName: 'User',
  url: 'users',
  fields: {userName: {
    displayName: 'UserName',
    type: 'String',
    required: true
   }
   , email: {
    displayName: 'Email',
    type: 'String',
    required: true
   }
   , givenName: {
    displayName: 'Name',
    type: 'String',
    required: true
   }
   , middleName: {
    displayName: 'MiddleName',
    type: 'String',
    required: true
   }
   , surName: {
    displayName: 'Roles',
    type: 'String',
    required: true
   }
  }
 });

 mod.config(['$stateProvider',
  function ($sp) {
   var basePath = 'src/modules/user/';
   $sp.state('user', {
    url: '/users',
    abstract: true,
    views: {
     mainView: {
      templateUrl: basePath + 'user.tpl.html'
     }
    },
    resolve: {
     model: 'userModel',
     clients: ['Restangular', function (r) {
       return r.all("clients").getList();
      }],
     items: ['Restangular', 'clients', function (r, clients) {
       return r.all("/payments?clientsId=" + clients[0].id).getList();
      }]
    }
   });
   $sp.state('userChangePass', {
    url: '/change',
    parent: 'user',
    views: {
     userView: {
      templateUrl: basePath + 'edit/user.changePassword.tpl.html',
      controller: 'userEditCtrl',
      controllerAs: 'ctrl'
     }
    }
   });
   $sp.state('userData', {
    url: '/data',
    parent: 'user',
    views: {
     userView: {
      templateUrl: basePath + 'data/user.data.tpl.html',
      controller: 'userDataCtrl',
      controllerAs: 'ctrl'
     }
    }
   });
   $sp.state('userPurchases', {
    url: '/purchases',
    parent: 'user',
    views: {
     userView: {
      templateUrl: basePath + 'purchases/user.purchases.tpl.html',
      controller: 'userPurchasesCtrl',
      controllerAs: 'ctrl'

     }

    }
   });
  }]);
})(window.angular);

