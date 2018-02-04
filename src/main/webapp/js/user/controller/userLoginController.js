'use strict';
application.controller('userLoginController', [
    '$scope',
    'userService',
    '$location',
    // function to return to the previous view
    function ($scope, userService, $location) {
        $scope.login = function () {
            userService.login($scope.user.enteredPassword,$scope.user.enteredKennung)
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert(response.data.errorMessage);
                        }
                        else {
                            alert("User " + response.data.kennung + " erfolgreich angemeldet.");
                            $location.path('/userPage')
                        }
                    }
                });
        }

    }
]);