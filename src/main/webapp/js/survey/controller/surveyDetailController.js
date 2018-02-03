'use strict';
/*Klasse geschrieben von Max Schumann*/
application.controller('surveyDetailController', [
    '$scope',
    '$location',
    'surveyService',
    '$routeParams',
    function ($scope, $location, surveyService,$routeParams) {
        // get all surveys

        var surveyID = $routeParams.surveyID;

        surveyService.findOne(surveyID)
            .then(function (response) {
                $scope.surveyOne = response.data;
                console.log(response.data.description);
            });


    }
]);