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
            $scope.survey.creator = "Testcreator";
            $scope.survey.appointmentOptions = new Array();
            surveyService.saveSurvey($scope.survey)  //appointmentOptions
                .then(function (response) {
                    if (response.status === 200) {
                        if (response.data.errorMessage != null) {
                            alert("Alert save Survey: " + response.data.errorMessage);
                        }
                        else {
                            alert("Sie haben erfolgreich die Umfrage mit der ID " + response.data.id + " und der Beschreibung " +  response.data.description
                                + " angelegt.");
                            $location.path('/appointmentCreate/survey/' + response.data.id);
                        }

                    }
                    else ("Error Save Survey: " + response.data.errorMessage);
                })
        };

        // function to return to the previous view
        $scope.goBack = function () {
            window.history.back();
        }
    }
]);

