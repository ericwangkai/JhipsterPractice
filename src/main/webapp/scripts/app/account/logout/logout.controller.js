'use strict';

angular.module('practiceApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
