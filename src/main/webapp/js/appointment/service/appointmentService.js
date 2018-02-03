/*Klasse geschrieben von Max Schumann*/
'use strict';
application.service('appointmentService', [
    '$http',
    function ($http) {
        // function to get all appointments
        this.findAll = function () {
            return $http.get('/api/backAppointment');
        };

        // function to save a new survey
        this.saveAppointment = function (appointment, id) {
            console.log(appointment);
            console.log(id);
            return $http.put('api/backSurvey/' + id, appointment);
        };
    }
]);
