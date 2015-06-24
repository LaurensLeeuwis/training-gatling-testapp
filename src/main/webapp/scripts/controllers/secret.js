'use strict';

angular.module('testApp')
    .controller('SecretCtrl', function ($scope, $http) {
        $scope.secret = 'retrieving..';

        $http.get('/my')
            .success(function(data) {
                $scope.secret = data.message;
            } )
            .error(function(data) {
                $scope.secret = data.message;
            });
    });
