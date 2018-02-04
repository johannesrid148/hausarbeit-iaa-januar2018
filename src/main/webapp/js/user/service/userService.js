/*
 * Service for users
 *
 * @author Johannes Ridinger
 */
'use strict';
application.service('userService', [
    '$http',

    function ($http) {
        // function to get all surveys
        this.findAll = function () {
            return $http.get('/api/users/showUser');
        };

        // function to get logged in User
        this.getLoggedInUser = function () {
            return $http.get('/api/users/getLoggedInUser');
        };

        // function to save a new user
        this.saveUser = function (user) {
            return $http.post('/api/users',user);
        };

        // function to check if a user is logged in
        this.userLoggedIn = function () {
            return $http.get('/api/users/userLoggedIn');
        };

        // function to login User
        this.login = function (enteredPassword,enteredKennung) {
            const options = {
                params: {
                    "enteredPassword": enteredPassword,
                    "enteredKennung": enteredKennung
                }
            };
            return $http.get('/api/users/userLogin',options);
        };

        // function to logout User
        this.logout = function (kennung) {
            const options = {
                params: {
                    "kennung": kennung
                }
            };
            return $http.get('/api/users/userLogout',options);
        };


    }
]);
