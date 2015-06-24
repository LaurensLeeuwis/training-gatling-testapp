'use strict';

angular.module('testApp')
    .controller('LoginCtrl', function ($scope, $http) {
        $scope.credentials = {
            username: 'fill credentials',
            password: ''
        };

        $scope.status = 'not send';

        $scope.mySubmit = function() {
            $http.post('/login', $scope.credentials).
                success(function () {
                    $scope.status = 'logged in';
                }).
                error(function () {
                    $scope.status = 'login failed';
                });
        };
    });
