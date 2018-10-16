app.controller("ListBooksController", ['$scope', function($scope){
    $.get("getAllBooks", null, function(data, statusText){
        if (data.exception){
            alert("与服务器交互出现异常：" + data.exception);
        } else {
            $scope.$apply(function() {
                $scope.books = data;
            });
        }
    });
}])
.controller("AddBookController", ['$scope', function($scope){
    $.get("getAllCategories", null, function(data, statusText){
        if (data.exception){
            alert("与服务器交互出现异常：" + data.exception);
        } else {
            $scope.$apply(function(){
                $scope.categories = data;
            });
        }
    });
    $scope.add = function(){
        $.post("addBook", $(".form-horizontal").serializeArray(), 
            function(data, statusText) {
                if(data.exception) {
                    alert("与服务器交互出现异常：" + data.exception);
                } else if(data.status > 0) {
                    alert("图书添加成功！");
                    $(".form-horizontal").get(0).reset();
                } else {
                    alert("图书添加失败！");
                }
            }, "json");
        return false;
    }
}])
.controller("UpdateBookController", ['$scope', '$state', '$stateParams',
     function($scope, $state, $stateParams){
        // 获取所有图书种类
        $.get("getAllCategories", null, function(data, statusText){
            if (data.exception) {
                alert("与服务器交互出现异常：" + data.exception);
            } else {
                $scope.$apply(function() {
                    $scope.categories = data;
                    $scope.book = $.parseJSON($stateParams.book);
                });
            }
        });
        
        $scope.update = function() {
            $.post("updateBook", $(".form-horizontal").serializeArray(),
                function(data, statusText) {
                    if (data.exception) {
                        alert("与服务器交互出现异常：" + data.exception);
                    } else if (data.status > 0) {
                        alert("图书更新成功！");
                        $(".form-horizontal").get(0).reset();
                        $state.go("listBooks");
                    } else {
                        alert("图书更新失败！");
                    }
                }, "json");
            return false;
        }
     }
]);