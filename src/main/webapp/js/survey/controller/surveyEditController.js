/*
 * Controller for the surveyEdit view
 */
/*Klasse geschrieben von Max Schumann*/
'use strict';
application.controller('surveyEditController', [
    '$scope',
    'surveyService',
    '$location',
    function ($scope, surveyService, $location) {

        $scope.deleteDate = function (index) {
            $scope.dates.splice(index, 1);
        }

        $scope.saveSurvey = function () {
            surveyService.saveSurvey($scope.survey)  //appointmentOptions
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert(response.data.errorMessage);
                        }
                        else {
                            alert("Sie haben erfolgreich die Umfrage " + response.data.description
                                + " angelegt.");
                            $location.path('/appointmentCreate/survey/' + response.data.id);
                        }

                    }
                })
        };

        // function to return to the previous view
        $scope.goBack = function () {
            window.history.back();
        }
    }
]);

