var angularApp = angular.module('angularApp',[]);

angularApp.controller('mainController', function($scope, $http){
    $scope.getNumber = function(num) {
        return new Array(num);
    }
    $scope.searchseries = function(){
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

});
