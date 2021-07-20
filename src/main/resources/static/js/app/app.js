var angularApp = angular.module('angularApp', ['ngRoute']);

angularApp.config(function ($routeProvider) {
    $routeProvider
    .when('/search', {
        templateUrl: 'views/search.html',
        controller: 'mainController'
    })
    .when('/favorite', {
        templateUrl: 'views/favorite.html',
        controller: 'favController'
    })
    .otherwise({
    			redirectTo: "/search"
    });

});

angularApp.controller('mainController', function($scope, $http){
    $scope.getNumber = function(num) {
        return new Array(num);
    }
    $scope.currentseason=1
    $scope.searchseries = function(){
        if(!$scope.webseriesname || $scope.webseriesname.length===0){
             alert("Please enter a webseries name");
             return;
        }
        $scope.IsVisible = true;
        $http({
          method: 'GET',
          url: '/series/no-of-episodes/'+$scope.webseriesname
        }).then(function successCallback(response) {
            $scope.episodecount = response.data;
          }, function errorCallback(response) {
            console.log(response);
          });
    }

    $scope.listepisodes = function(season){
            $scope.currentseason=season
            $scope.IsListVisible = true;
            $http({
              method: 'GET',
              url: '/series/episodes-for-season?series=' + $scope.webseriesname + '&season=' + season
            }).then(function successCallback(response) {
                $scope.episodelist = response.data;
              }, function errorCallback(response) {
                console.log(response);
              });
        }

    $scope.changeepisodestatus = function(seriesSeasonEpisodes){
    console.log(JSON.stringify(seriesSeasonEpisodes))
    var urlString='';
    if(seriesSeasonEpisodes.isFavorite){
        urlString='/write/add-favorite';
    } else {
        urlString='/write/remove-favorite';
    }
        console.log(urlString);
        $http({
          method: 'POST',
          url: urlString,
          data: JSON.stringify(seriesSeasonEpisodes)
        }).then(function successCallback(response) {
            console.log(response)
          }, function errorCallback(response) {
            console.log(response);
          });
    }

});


angularApp.controller('favController', function($scope, $http) {
    $scope.$on('$viewContentLoaded', function() {
        $http({
                     method: 'GET',
                      url: '/series/get-all-favorite'
                    }).then(function successCallback(response) {
                        $scope.favepisodelist = response.data;
                      }, function errorCallback(response) {
                        console.log(response);
                      });
    });

});
