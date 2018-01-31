'use strict';
application.controller('surveyListController', [
    '$scope',
    '$location',
    'appointmentSurveyService',
    function ($scope, $location, surveyService) {
        // get all professors
        surveyService.findAll()
            .then(function (response) {
                $scope.appointmentSurveys = response.data;
            });

        // function to navigate to the timetable
        $scope.getSurvey = function (surveyID) {
            $location.path('/survey/' + appointmentSurveyID);
        };
    }
]);
