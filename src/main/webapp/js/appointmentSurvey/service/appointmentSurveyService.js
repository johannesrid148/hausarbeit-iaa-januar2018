
'use strict';
application.service('appointmentSurveyService', [
    '$http',
    function ($http) {
        // function to get all surveys
        this.findAll = function () {
            return $http.get('/surveys');
        };

        // function to save a new survey
        this.saveSurvey = function (apppointmentSurvey) {
            return $http.post('iaa/api/appointmentSurvey', apppointmentSurvey);
        };
    }
]);
