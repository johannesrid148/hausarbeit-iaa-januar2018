'use strict';
application.controller('userPageController', [
    '$scope',
    'userService',
    'surveyService',
    '$location',
    function ($scope, userService,surveyService,$location) {
        //get all logged in users
        userService.getLoggedInUser()
            .then(function (response) {
                $scope.users = response.data;
            });

        //get all active Surveys
        surveyService.findActive()
            .then(function (response) {
                $scope.activesurveys = response.data;
            });

        //get all inactive Surveys
        surveyService.findInactive()
            .then(function (response) {
                $scope.inactivesurveys = response.data;
            });

        // function to logout user
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