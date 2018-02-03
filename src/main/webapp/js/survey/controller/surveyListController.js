'use strict';
/*Klasse geschrieben von Max Schumann*/
application.controller('surveyListController', [
    '$scope',
    '$location',
    'surveyService',
    function ($scope, $location, surveyService) {
        // get all surveys


        surveyService.findAll()
            .then(function (response) {
                $scope.surveys = response.data;
            });

        // function to navigate to the surveys
        $scope.getSurvey = function (surveyID) {
            console.log(surveyID);
            $location.path('/survey/' + surveyID);
        };
    }
]);