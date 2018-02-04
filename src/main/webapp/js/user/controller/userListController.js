'use strict';
application.controller('userListController', [
    '$scope',
    'userService',
    '$location',
    function ($scope, userService,$location) {
        // get all user
        userService.findAll()
            .then(function (response) {
                $scope.users = response.data;
            });


        // function to navigate to the timetable
        $scope.logout = function (kennung) {
            userService.logout(kennung)
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert(response.data.errorMessage);
                        }
                        else {
                            alert("User " + response.data.kennung + " erfolgreich ausgeloggt.");
                            $location.path('/userLogin')
                        }
                    }
                });
        }


    }
]);