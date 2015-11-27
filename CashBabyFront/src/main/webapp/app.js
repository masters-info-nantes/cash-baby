var app = angular.module('cashbaby', ['ui.router', 'ngStorage']);

app.factory('CashbabyAPI', ['$q',
  function($q){
    return {

            run : function(apiName, data) {
              var deferred = $q.defer();
               data = typeof data !== 'undefined' ? data : {};
$.soap({
  url: 'http://localhost:9763/services/Shop/',
  namespaceURL:'http://services.api.shop.cashbaby.services.alma.org',
  method: apiName,
  data: data,
  soap12: true,
  success: function (soapResponse) {
      deferred.resolve(soapResponse.toJSON());
  },
  error: function (soapResponse) {
      deferred.reject(soapResponse);
  },

});
return deferred.promise;
            }
        }
  }]);

app.run(['$localStorage', 'CashbabyAPI',
        function($localStorage, CashbabyAPI) {

            if(!$localStorage.orderId) {
            $localStorage.orderId = null;
            CashbabyAPI.run('startOrder').then(function(data) {
              $localStorage.orderId = data.return;
            }, function(data) {
               console.log("ko");
            });
          }


        }]);

app
    .config(['$urlRouterProvider',
        function($urlRouterProvider) {

            $urlRouterProvider.otherwise("/");

        }]);

app
    .config(['$stateProvider',
        function($stateProvider) {

            $stateProvider

                .state('webapp', {
                    abstract: true,
                    url: '',
                    templateUrl: 'partials/page.html',
                })

                .state('webapp.home', {
                    url :'/',
                    views :  {
                        '': {
                            templateUrl: 'partials/catalogue.html',
                            controller: 'cashbaby.controller.catalogue'
                        },
                    },
                })

                .state('webapp.produit', {
                    url :'/produit-{id}',
                    views :  {
                        '': {
                            templateUrl: 'partials/produit.html',
                            controller: 'cashbaby.controller.produit'
                        },
                    },
                })

                .state('webapp.panier', {
                    url :'/panier',
                    views :  {
                        '': {
                            templateUrl: 'partials/panier.html',
                            controller: 'cashbaby.controller.panier'
                        },
                    },
                })

                .state('webapp.info', {
                    url :'/info',
                    views :  {
                        '': {
                            templateUrl: 'partials/infoclient.html',
                            controller: 'cashbaby.controller.info'
                        },
                    },
                })
    }]);


app.controller('cashbaby.controller.catalogue', ['$scope', 'CashbabyAPI',
  function clientList($scope, CashbabyAPI) {
    CashbabyAPI.run('getItems').then(function(data) {
      $scope.produits = data.return;
      console.log(data);
    }, function(data) {
      console.log("ko");
    });

  }
]);

app.controller('cashbaby.controller.produit', ['$scope', '$localStorage', '$stateParams', 'CashbabyAPI',
  function clientList($scope, $localStorage, $stateParams, CashbabyAPI) {
    $scope.nbProduct = 1;
    $scope.msg = "";
    CashbabyAPI.run('getItem', {id: $stateParams.id}).then(function(data) {
      $scope.produit = data.return;
      console.log(data.return);
    });
    $scope.validate = function() {
      CashbabyAPI.run('reserve', {orderId: $localStorage.orderId, itemId: $stateParams.id, quantity: $scope.nbProduct}).then(function(data) {
        $scope.msg = $scope.nbProduct+" produit(s) a bien été ajouté";
        $scope.nbProduct = 1;
        console.log(data);
      }, function(data) {
        console.log("ko");
      });
    }
  }
]);

app.controller('cashbaby.controller.panier', ['$scope', '$localStorage', 'CashbabyAPI',
  function clientList($scope, $localStorage, CashbabyAPI) {
    CashbabyAPI.run('getCart', {orderId: $localStorage.orderId}).then(function(data) {
      $scope.cart = data.return;
      console.log(data.return);
    });
    $scope.getTotal = function(){
        var values = 0; // declarate all vars, it's a good pratice
        angular.forEach($scope.cart, function(produit, index) {
          values += produit.quantity * produit.price;
        });
        return values;
    }
    $scope.removeOne = function(id) {
      CashbabyAPI.run('unreserve', {orderId: $localStorage.orderId, itemId: id, quantity: 1}).then(function(data) {
        console.log(data.return);
        angular.forEach($scope.cart, function(produit, index) {
          if(produit.id == id) {
            $scope.cart[index].quantity -= 1;
          }
        });
      });
    }
    $scope.addOne = function(id) {
      CashbabyAPI.run('reserve', {orderId: $localStorage.orderId, itemId: id, quantity: 1}).then(function(data) {
        console.log(data.return);
        angular.forEach($scope.cart, function(produit, index) {
          if(produit.id == id) {
            $scope.cart[index].quantity = parseInt($scope.cart[index].quantity)+1;
          }
        });
      });
    }
    $scope.removeAll = function(id, quantity) {
      CashbabyAPI.run('unreserve', {orderId: $localStorage.orderId, itemId: id, quantity: quantity}).then(function(data) {
        console.log(data.return);
        angular.forEach($scope.cart, function(produit, index) {
          if(produit.id == id) {
            $scope.cart.splice(index, 1);
          }
        });
      });
    }
  }
]);

app.controller('cashbaby.controller.info', ['$scope', '$localStorage', '$state', 'CashbabyAPI',
  function clientList($scope, $localStorage, $state, CashbabyAPI) {
    $scope.validCommand = function() {
      $scope.card.expirationDate = $scope.month+'/'+$scope.year;
      $scope.addressInline = $scope.card.ownerName+'<br>'+$scope.address.address+'<br>'+$scope.address.zipcode+'<br>'+$scope.address.city+'<br>'+$scope.address.country
    CashbabyAPI.run('order', {orderId: $localStorage.orderId, shippingAddress: $scope.addressInline, card: $scope.card}).then(function(data) {
      CashbabyAPI.run('startOrder').then(function(data) {
              $localStorage.orderId = data.return;
              $state.go('webapp.home')
            }, function(data) {
               console.log("ko");
            });
    }, function() {
      console.log('ko');
    });
  }
  }
]);
