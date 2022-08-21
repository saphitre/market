var app = angular.module("PRODUCTS", []);
app.controller("GET_ALL_PRODUCTS",function ($scope,$http){

    $scope.productList=[]

    $scope.getAllProducts =function (){
        $http({
            url:"/products",
            method:"GET",
        }).then(function (response){
            for (var i=0; i<response.data.length; i++)
            {
                if (response.data[i].quantity>"0") {
                    response.data[i].quantity="В наличии";
                }
            }
            $scope.productList=response.data;
        });
    }
    $scope.getAllProducts();


    $scope.findProductByID =function (product_id){
        $http({
            url:"/searchById/"+product_id,
            method:"GET",
            data:{
                id: product_id
            }
        }).then(function (response){
            console.log(response.data)
            if(response.data!==null){
                if (response.data.quantity > "0") {
                response.data.quantity = "В наличии";
                }
                $scope.productList = [];
                $scope.productList.push(response.data)
            }
            else{
                console.log("ff");
                var elems = document.getElementsByClassName('btn');
                for(var i = 0; i != elems.length; ++i)
                {
                    elems[i].style.visibility = "hidden"; // hidden has to be a string
                }
            }
        });
    }

    $scope.inputValidation=function (product_id){
        if (typeof product_id === 'number' & product_id!==NaN){
            $scope.findProductByID(product_id);
        }
        if(product_id===null){
            $scope.getAllProducts();
        }
    }

});