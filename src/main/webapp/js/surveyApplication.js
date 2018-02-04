'use strict';
/*Klasse geschrieben von Max Schumann*/
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
        controller: 'surveyDetailController',
        templateUrl: 'view/survey/surveyDetail.html'
    })
        .when('/appointmentCreate/survey/:surveyID', {
            controller: 'appointmentCreateController',
            templateUrl: 'view/appointment/appointmentCreate.html'
        })
        .when('/users', {
            controller: 'userListController',
            templateUrl: 'view/user/userList.html'
        })
        .when('/userEdit', {
            controller: 'userEditController',
            templateUrl: 'view/user/userEdit.html'
        })
        .when('/userLogin', {
            controller: 'userLoginController',
            templateUrl: 'view/user/userLogin.html'
        })
        .when('/userPage', {
            controller: 'userPageController',
            templateUrl: 'view/user/userPage.html'
        })
});

