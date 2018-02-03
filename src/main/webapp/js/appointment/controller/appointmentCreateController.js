/*
 * Controller for the surveyEdit view
 */
/*Klasse geschrieben von Max Schumann*/
'use strict';
application.controller('appointmentCreateController', [
    '$scope',
    'appointmentService',
    '$location',
    '$routeParams',
    function ($scope, appointmentService, $location, $routeParams) {

        var surveyID = $routeParams.surveyID;



        $scope.saveAppointment = function () {
            appointmentService.saveAppointment($scope.appointment, surveyID)
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
                    else {alert("Error beim hinzufügen der Daten: " + response.data.erroMessage)}
                })
        }


    }
])
;

