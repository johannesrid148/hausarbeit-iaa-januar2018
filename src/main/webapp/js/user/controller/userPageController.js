'use strict';
application.controller('userPageController', [
    '$scope',
    'userService',
    function ($scope, userService) {
        $scope.getLoggedInUser = function () {
            userService.getLoggedInUserName()
                .then(function (response) {
                    if (response.data.errorMessage) {
                        alert(response.data.errorMessage);
                    } else {
                        $scope.users = response.data;
                    }
                });
        };

    }
]);