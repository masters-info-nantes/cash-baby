var app = angular.module('cashbaby', ['ui.router', 'ngStorage']);

app.run(['$localStorage',
    function($localStorage) {
      if(!$localStorage.panier) {
        $localStorage.panier = [];
      }
    }]);

app.factory('CashbabyAPI', ['$q',
  function($q){
    return {

            run : function(apiName, data) {
              var deferred = $q.defer();
$.soap({
  url: 'http://localhost:9763/services/Bank/',
  namespaceURL:'http://bank.cashbaby.services.alma.org',
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
                            templateUrl: 'partials/catalogue.html',
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
      $scope.produits = data;
      console.log(data);
    }, function(data) {
      console.log(data);
    });

  }
]);

app.controller('cashbaby.controller.produit', ['$scope', 'ngStorage', '$stateParams',
  function clientList($scope, ngStorage, $stateParams) {
    CashbabyAPI.run('getItem', {id: $stateParams.id}).then(function(data) {
      $scope.produit = data;
    });
    $scope.validate = function() {
      CashbabyAPI.run('getItem', {orderId: ngStorage.orderId, itemId: $stateParams.id, quantity: $scope.form.nbProduct}).then(function(data) {
        $scope.produit = data;
      });
      $scope.form.nbProduct = 0;
    }
  }
]);

app.controller('cashbaby.controller.panier', ['$scope', 'ngStorage',
  function clientList($scope, ngStorage) {
    $scope.getTotal = function(){
        var total = 0;
        for(var i = 0; i < $scope.cart.products.length; i++){
            var product = $scope.cart.products[i];
            total += (product.price * product.quantity);
        }
        return total;
    }
  }
]);

app.controller('cashbaby.controller.info', ['$scope', 'ngStorage',
  function clientList($scope, ngStorage) {

  }
]);
