/*
 * Controller for the surveyEdit view
 */
'use strict';
application.controller('surveyEditController', [
    '$scope',
    'surveyService',
    '$location',
    function ($scope, surveyService, $location) {
        // function to save a new survey
        $scope.saveSurvey = function () {
            surveyService.saveSurvey($scope.survey)
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert(response.data.errorMessage);
                        }
                        else {
                            alert("Sie haben erfolgreich die Umfrage " + response.data.description
                                + " angelegt.");
                            $location.path('/surveys')
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

