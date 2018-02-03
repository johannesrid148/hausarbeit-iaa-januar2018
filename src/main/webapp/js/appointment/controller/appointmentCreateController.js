/*
 * Controller for the surveyEdit view
 */
/*Klasse geschrieben von Max Schumann*/
'use strict';
application.controller('appointmentCreateController', [
    '$scope',
    'surveyService',
    'appointmentService',
    '$location',
    function ($scope, surveyService, appointmentService, $location) {

        var id = $routeParams.surveyID;

        $scope.saveAppointment = function () {
            appointmentService.saveAppointment($scope.appointment, id)
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
                })
        }

    }
])
;

