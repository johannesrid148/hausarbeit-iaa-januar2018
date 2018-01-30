
'use strict';
const application = angular.module('surveyAdministration', ['ngRoute', 'ui.bootstrap']);

application.config(function ($routeProvider) {
    $routeProvider
        .when('/surveys', {
            controller: 'surveyListController',
            templateUrl: 'view/survey/surveyList.html'
        })
        .when('/surveyEdit', {
            controller: 'surveyEditController',
            templateUrl: 'view/survey/surveyEdit.html'
        })
        .otherwise({
            redirectTo: '/survey'
        });
});
