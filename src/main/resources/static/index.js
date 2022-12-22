angular.module('app',['ngStorage']).controller('indexController', function($scope, $http, $localStorage){

    console.log("test console.log: index.js - is working!")

    const contextPath = 'http://localhost:8189/winter/api/v1';
    $scope.pageNumber = 1;


//Authentication-----------------------------
$scope.tryToAuth = function(){
    $http.post('http://localhost:8189/winter/auth', $scope.user)
        .then(function successCallback(response){
            if(response.data.token){
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

console.log("User is authorized, token: " + $http.defaults.headers.common.Authorization)

                $scope.user.username = null;
                $scope.user.password = null;
            }
        }, function errorCallback(response){
        });
};

$scope.tryToLogout = function(){
    $scope.clearUser();
    $scope.user = null;
};

$scope.clearUser = function(){
    delete $localStorage.winterMarketUser;
    $http.defaults.headers.common.Authorization = '';
        console.log("User shutdown, [Authorization] is empty")
};

$scope.isUserLoggedIn = function(){
    if ($localStorage.winterMarketUser){
        return true;
    }else{
        return false;
    }
};

$scope.authCheck= function(){
    $http.get('http://localhost:8189/winter/auth_check').then(function(response){
        alert(response.data.value);
    });
};

if($localStorage.winterMarketUser){
    try{
        let jwt = $localStorage.winterMarketUser.token;
        let payload = JSON.parse(atob(jwt.split('.')[1]));
        let currentTime = parseInt(new Date().getTime() / 1000);
        if(currentTime > payload.exp){
            console.log("Token is expired!");
            delete $localStorage.winterMarketUser;
            $http.defaults.headers.common.Authorization ='';
            }
    }catch (e){
    }

    $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
};
//Authentication-----------------------------


//Получение списка продуктов
$scope.loadProducts = function() {
                $http({
                    url: contextPath +'/products',
                    method: 'GET',
                    params: {
                             p: $scope.pageNumber,
                             min_price: $scope.filter ? $scope.filter.min_price : null,
                             max_price: $scope.filter ? $scope.filter.max_price : null,
                             title_part: $scope.filter ? $scope.filter.title_part : null
                    }
                }).then(function(response) {
                                  $scope.productList = response.data.content;
                })
}
//Пагинация
$scope.change_page = function(pageVar) {
             $scope.pageNumber = $scope.pageNumber + pageVar;
             if($scope.pageNumber <= 0){
                    $scope.pageNumber = 1
             }
             $http({
                    url: contextPath +'/products',
                    method: 'GET',
                    params: {
                             p: $scope.pageNumber,
                             min_price: $scope.filter ? $scope.filter.min_price : null,
                             max_price: $scope.filter ? $scope.filter.max_price : null,
                             title_part: $scope.filter ? $scope.filter.title_part : null
                    }
             }).then(function(response) {
                     $scope.productList = response.data.content;
             })
}
//Информация о продукте
$scope.showProductInfo = function(productId){
    $http.get(contextPath + '/products/'+productId)
            .then(function(response){
            alert(response.data.title);
    })
}
//Удаление
$scope.deleteProductById = function(id){
            $http.delete(contextPath + '/products/' + id)
            .then(function(response) {
                $scope.loadProducts();
            })
}
//Добавить в корзину
$scope.addToCart = function(productId){
    $http.get(contextPath + '/cart/add/' + productId).then(function(response){
    $scope.loadCart();
    })
}
//Отображение корзины
$scope.loadCart = function(){
    $http.get(contextPath + '/cart').then(function(response){
    $scope.cart = response.data;
    })
}
//Удаление из корзины
$scope.deleteFromCart = function(productId){
    $http.delete(contextPath + '/cart/'+ productId).then(function(response){
    $scope.loadCart();
    })
}
//Очистить корзину
$scope.clearCart = function(productId){
    $http.delete(contextPath + '/cart').then(function(response){
    $scope.loadCart();
    })
}
//Количество в корзине
$scope.changeQuantity = function(productId, number){
    if(number < 0){
            $http.put(contextPath + '/cart/decrease/' + productId)
            .then(function(response){
                $scope.loadCart();
            })
    }
    if(number > 0){
            $http.put(contextPath + '/cart/increase/' + productId)
            .then(function(response){
                $scope.loadCart();
            })
    }

}
//Оформление заказа
$scope.createOrder = function(){
     $http.post(contextPath + '/orders')
                .then(function(response){
                        alert('Заказ оформлен!');
                        $scope.loadCart();
                })
};



$scope.loadProducts();
$scope.loadCart();

});

