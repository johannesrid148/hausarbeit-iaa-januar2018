
'use strict';
const application = angular.module('surveyAdministration', ['ngRoute', 'ui.bootstrap']);

application.config(function ($routeProvider) {
    $routeProvider
        .when('/survey', {
            controller: 'surveyListController',
            templateUrl: 'view/user/userList.html'
        })
        .otherwise({
            redirectTo: '/survey'
        });
});
