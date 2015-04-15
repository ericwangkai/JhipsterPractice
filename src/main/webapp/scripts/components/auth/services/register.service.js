'use strict';

angular.module('practiceApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


