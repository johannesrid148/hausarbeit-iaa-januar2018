
'use strict';
const application = angular.module('surveyAdministration', ['ngRoute', 'ui.bootstrap']);

application.config(function ($routeProvider) {
    $routeProvider
        .when('/professors', {
            controller: 'professorListController',
            templateUrl: 'view/professor/professorList.html'
        })
        .otherwise({
            redirectTo: '/survey'
        });
});
