/**
 * Created by admin on 2017/5/8.
 */
angular.module('app', [])
    .controller('home', function($scope) {
        $http.get('/resource/').success(function(data) {

        })
    })
