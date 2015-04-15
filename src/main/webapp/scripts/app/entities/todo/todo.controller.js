'use strict';

angular.module('practiceApp')
    .controller('TodoController', function ($scope, Todo, User, ParseLinks) {
        $scope.todos = [];
        $scope.users = User.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Todo.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.todos.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.todos = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            $scope.todo.status = 'To Do';
            Todo.update($scope.todo,
                function () {
                    $scope.reset();
//                    $('#saveTodoModal').modal('hide');
                    $scope.clear();
                });
        }

        $scope.update = function (id) {
            Todo.get({id: id}, function(result) {
                $scope.todo = result;
                $('#saveTodoModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Todo.get({id: id}, function(result) {
                $scope.todo = result;
                $('#deleteTodoConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Todo.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteTodoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.todo = {title: null, status: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
