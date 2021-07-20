var angularApp = angular.module('angularApp',[]);

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
              url: '/series/episodes-for-season?series='+$scope.webseriesname + '&season=' + season
            }).then(function successCallback(response) {
                $scope.episodelist = response.data;
              }, function errorCallback(response) {
                console.log(response);
              });
        }

    $scope.changeepisodestatus = function(episode){
        alert(episode.isFavorite)
    }

});
