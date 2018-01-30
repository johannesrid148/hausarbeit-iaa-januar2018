'use strict';
application.controller('appointmentSurveyListController', [
    '$scope',
    '$location',
    'appointmentSurveyService',
    function ($scope, $location, appointmentSurveyService) {
        // get all professors
        appointmentSurveyService.findAll()
            .then(function (response) {
                $scope.appointmentSurveys = response.data;
            });

        // function to navigate to the timetable
        $scope.getAppointmentSurvey = function (appointmentSurveyID) {
            $location.path('/timeTable/Professor/' + professorId);
        };
    }
]);
