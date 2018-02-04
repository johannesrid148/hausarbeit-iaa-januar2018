
'use strict';
application.service('userService', [
    '$http',

    function ($http) {
        // function to get all surveys
        this.findAll = function () {
            return $http.get('/api/users/showUser');
        };

        // function to save a new survey
        this.saveUser = function (user) {
            return $http.post('/api/users',user);
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

        // function to login User
        this.logout = function (kennung) {
            const options = {
                params: {
                    "kennung": kennung
                }
            };
            return $http.get('/api/users/userLogout',options);
        };

        // function to logged in User name
        this.getLoggedInUserName = function () {
            return $http.get('/api/users/getLoggedInUser');
        };
    }
]);
