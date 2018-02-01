/*
 * Controller for the surveyEdit view
 */
'use strict';
application.controller('surveyEditController', [
    '$scope',
    'surveyService',
    '$location',
    function ($scope, surveyService, $location) {
        $scope.dates = new Array();
        // function to save a new survey
        $scope.addDate = function () {

            $scope.dates.push($scope.newDate);
            $scope.dates.push($scope.newDate)
            $scope.newDate = '';
        }

        $scope.deleteDate = function (index) {
            $scope.dates.splice(index, 1);
        }


        var result = surveyService.saveSurvey($scope.survey)  //appointmentOptions
            .then(function (response) {
                if (response.status === 200) {
                    if (response.data.errorMessage != null) {
                        alert(response.data.errorMessage);
                    }
                    else {
                        alert("Sie haben erfolgreich die Umfrage " + response.data.description
                            + " angelegt.");
                        $location.path('/appointmentCreate/survey/' + result.id);
                    }

                }
            });



        var lectureDate = new Date($scope.startdate).getTime();
        var startDate = new Date($scope.enddate).getTime();
        var endDate = new Date($scope.endTime).getTime();


        // function to return to the previous view
        $scope.goBack = function () {
            window.history.back();
        }
    }
]);

