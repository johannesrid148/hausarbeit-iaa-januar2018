/*
 * Controller for the userEdit view
 */
'use strict';
application.controller('userEditController', [
    '$scope',
    'userService',
    '$location',
        function ($scope, userService, $location) {
        $scope.saveUser = function () {
            console.log("user: " + $scope.user);
            console.log("user: " + $scope.user.firstName);
            userService.saveUser($scope.user)
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert(response.data.errorMessage);
                        }
                        else {
                            alert("Sie haben erfolgreich den User " + response.data.firstName + " angelegt.");
                            $location.path('/users')
                        }
                    }
                });
        };

        // function to return to the previous view
        $scope.goBack = function () {
            window.history.back();
        }
    }
]);

