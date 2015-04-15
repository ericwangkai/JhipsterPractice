'use strict';

angular.module('practiceApp')
    .controller('TodoDetailController', function ($scope, $stateParams, Todo, User) {
        $scope.todo = {};
        $scope.load = function (id) {
            Todo.get({id: id}, function(result) {
              $scope.todo = result;
            });
        };
        $scope.load($stateParams.id);
    });
