'use strict';

angular.module('testApp')
    .controller('MainCtrl', function ($scope, $http) {
        $scope.hi = 'Hi there!'.toUpperCase();
        $scope.yolo = 'echo';

        $http.get('/echo?message=echooo')
            .success(function(data) {
                console.log(data);
                $scope.yolo = data.echo;
            });
    });
