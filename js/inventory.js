app.controller("ListInventoriesController", ['$scope', function($scope){
    $.get("getAllInventories", null, function(data, statusText){
        if (data.exception) {
            alert("与服务器交互出现异常：" + data.exception);
        } else {
            $scope.$apply(function() {
                $scope.inventories = data;
            });
        }
    });
    $scope.viewItems = function(myevent) {
        let inventoryId = myevent.target.dataset.inventoryid;
        $.get("getItemsById", {inventoryId: inventoryId},
            function(data, statusText){
                if (data.exception) {
                    alert("与服务器交互出现异常：" + data.exception);
                } else {
                    $scope.$apply(function() {
                        $scope.items = data;
                        $('.modal').modal('show');
                    });
                }
            }
        );
    }
}])
.controller("InventoryBookController", ['$scope', function($scope){
    $.get("getAllBooks", null, function(data, statusText){
        if (data.exception) {
            alert("与服务器交互出现异常：" + data.exception);
        } else {
            $scope.$apply(function() {
                $scope.books = data;
            });
        }
    });
    $scope.deleteItem = function(myevent) {
        $(myevent.target).parents("#itemRow").remove();
    };
    $scope.addItem = function() {
        $("#itemRow").first().clone(true).insertBefore("#lastBnGroup");  
    };
    $scope.add = function() {
        $.post("addInventory", $(".form-horizontal").serializeArray(),
            function(data, statusText) {
                if (data.exception) {
                    alert("与服务器交互出现异常：" + data.exception);
                } else if (data.status > 0) {
                    alert("图书入库成功！");
                    $(".form-horizontal").get(0).reset();
                } else {
                    alert("图书入库失败！");
                }
            }, "json");
        return false;
    }
}]);