/*
 * Controller for the surveyEdit view
 */
'use strict';
application.controller('appointmentCreateController', [
    '$scope',
    'surveyService',
    'appointmentService'
    '$location',
    function ($scope, surveyService, $location) {

        var title = $routeParams.title;
        var descr = $routeParams.descr;
        $scope.saveAppointment = function () {



        }
    }
]);

