/*Klasse geschrieben von Max Schumann*/
'use strict';
application.service('surveyService', [
    '$http',
    function ($http) {
        // function to get all surveys
        this.findAll = function () {
            return $http.get('/api/backSurvey');
        };

        // function to get all active surveys
        this.findActive = function () {
            return $http.get('/api/backSurvey/getActiveSurveys');
        };

        // function to get all inactive surveys
        this.findInactive = function () {
            return $http.get('/api/backSurvey/getInactiveSurveys');
        };

        this.findOne = function (surveyID) {
            return $http.get('/api/backSurvey/' + surveyID);
        };

        // function to save a new survey
        this.saveSurvey = function (survey) {
            return $http.post('/api/backSurvey', survey);
        };
    }
]);
