'use strict';

angular.module('practiceApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('todo', {
                parent: 'entity',
                url: '/todo',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'practiceApp.todo.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/todo/todos.html',
                        controller: 'TodoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('todo');
                        return $translate.refresh();
                    }]
                }
            })
            .state('todoDetail', {
                parent: 'entity',
                url: '/todo/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'practiceApp.todo.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/todo/todo-detail.html',
                        controller: 'TodoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('todo');
                        return $translate.refresh();
                    }]
                }
            });
    });
