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

        $scopte.appoinmentlist = new Array();
        for (var i = 1; i <= $scope.choices.length; i++){
            $scope.appoinmentlist.push($scope.appointment)  ;
        }

        $scope.saveAppointment = function () {
            console.log("Choices: " + $scope.choices);
            for (var i in $scope.choices){
                console.log($scope.appointmentlist[i].start = i.start);
                ($scope.appointmentlist[i].end = i.end);
                ($scope.appointmentlist[i].endtime = i.endtime);
                ($scope.appointmentlist[i].starttime = i.starttime);
            }
            console.log("AL: " + $scope.appointmentlist);

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

