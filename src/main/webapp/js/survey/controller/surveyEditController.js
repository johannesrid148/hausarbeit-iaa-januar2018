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

        $scope.deleteDate = function(index) {
            $scope.dates.splice(index, 1);
        }


        $scope.saveSurvey = function () {
            var start = new Date($scope.startdate);
            var end = new Date($scopte.enddate);
            var appointmentOptions = new Array();
            var appointment = newArray();
            appointment.add(start);
            appointment.add(end);
            appointmentOptions.add(appointment);
            surveyService.saveSurvey($scope.survey, appointmentOptions)
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

        var lectureDate = new Date($scope.startdate).getTime();
        var startDate = new Date($scope.enddate).getTime();
        var endDate = new Date($scope.endTime).getTime();


        // function to return to the previous view
        $scope.goBack = function () {
            window.history.back();
        }
    }
]);

