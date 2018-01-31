
'use strict';
application.service('surveyService', [
    '$http',
    function ($http) {
        // function to get all surveys
        this.findAll = function () {
            return $http.get('/backSurvey');
        };

        // function to save a new survey
        this.saveSurvey = function (survey) {
            return $http.post('/backSurvey', survey);
        };
    }
]);
