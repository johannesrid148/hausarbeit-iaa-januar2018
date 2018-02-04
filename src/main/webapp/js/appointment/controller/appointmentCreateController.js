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

        $scope.choices = [{id: 'choice1'}, {id: 'choice2'}];

        $scope.addNewChoice = function() {
            var newItemNo = $scope.choices.length+1;
            $scope.choices.push({'id':'choice'+newItemNo});
        };

        $scope.removeChoice = function() {
            var lastItem = $scope.choices.length-1;
            $scope.choices.splice(lastItem);
        };


       /* for (var i = 1; i <= $scope.choices.length; i++){
            $scope.appoinmentlist.push($scope.appointment)  ;
        }*/

        $scope.saveAppointment = function () {
            $scope.appoinmentlist = [""];
            for (var i in $scope.choices){
                ($scope.appointmentlist.push(i.start));
                ($scope.appointmentlist.push(i.end));
                ($scope.appointmentlist.push(i.endtime));
                ($scope.appointmentlist.push(i.starttime));
            }

            appointmentService.saveAppointment($scope.appointmentlist, surveyID)
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

