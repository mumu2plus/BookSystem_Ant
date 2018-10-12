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
}]);