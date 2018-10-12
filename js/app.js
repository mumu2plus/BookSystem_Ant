jQuery.ajaxSetup({
    error: function(xhr, textStatus, error){
        alert("服务器交互出现异常，错误信息：" + textStatus);
    }
});

// 创建模块，加载ui.router模块
let app = angular.module("book-app", ['ui.router'])
    .config(['$stateProvider', '$urlRouterProvider',
        function($stateProvider, $urlRouterProvider){
            $urlRouterProvider.otherwise('/main');
            $stateProvider
            .state('main', {
                url: '/main',
                templateUrl: 'res/main.html'
            })
            .state('listCategories', {
                url: '/listCategories',
                controller: 'ListCategoriesController',
                templateUrl: 'res/listCategories.html'
            });
    }]);
app.controller("MainController", angular.noop);