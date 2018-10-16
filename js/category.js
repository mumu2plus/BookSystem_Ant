app.controller("ListCategoriesController", ['$scope', function($scope){
    $.get("getAllCategories", null, function(data, statusText){
       if (data.exception) {
            alert("与服务器交互出现异常：" + data.exception);
       } else {
            $scope.$apply(function() {
                $scope.categories = data;
            });
       } 
    });
}])
.controller("AddCategoryController", ['$scope', function($scope){
    $scope.add = function(){
        $.post("addCategory", $(".form-horizontal").serializeArray(),
            function(data, statusText){
                if(data.exception) {
                    alert("与服务器交互出现异常：" + data.exception);
                } else if(data.status > 0) {
                    alert("图书种类添加成功！");
                    $(".form-horizontal").get(0).reset();
                } else {
                    alert("图书种类添加失败！");
                }
            }, "json");
        return false;// 阻止默认的提交
    }
}])
.controller("UpdateCategoryController", ['$scope', '$state', '$stateParams', function($scope, $state, $stateParams){
    $scope.category = $.parseJSON($stateParams.category);
    $scope.update = function() {
        $.post("updateCategory", $(".form-horizontal").serializeArray(),
            function(data, statusText){
                if(data.exception){
                    alert("与服务器交互出现异常：" + data.exception);
                } else if(data.status > 0) {
                    alert("图书种类更新成功！");
                    $(".form-horizontal").get(0).reset();
                    $state.go("listCategories");
                } else{
                    alert("图书种类更新失败！");
                }
            }, "json");
        return false; //阻止默认的提交
    }
}]);