'use strict';
const application = angular.module('surveyApplication', ['ngRoute', 'ui.bootstrap']);

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
        .when('/survey/:surveyID', {
            controller: 'surveyListController',
            templateUrl: 'view/survey/surveyList.html'
        })
        .otherwise({
            redirectTo: '/survey'
        });
});

