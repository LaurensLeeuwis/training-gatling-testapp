'use strict';

angular
    .module('testApp', ['ngRoute'])
    .config(
        function($routeProvider) {
            $routeProvider.
                when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'LoginCtrl'
                }).
                when('/main', {
                    templateUrl: 'views/main.html',
                    controller: 'MainCtrl'
                }).
                when('/secret', {
                    templateUrl: 'views/secret.html',
                    controller: 'SecretCtrl'
                }).
                otherwise({
                    redirectTo: '/main'
                });
        });