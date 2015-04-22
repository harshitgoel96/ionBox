angular.module('starter.controllers', [])



.controller('AppCtrl', function($scope, $ionicModal, $timeout) {
  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})

.controller('PlaylistsCtrl', function($scope) {
  $scope.playlists = [
    { title: 'Reggae', id: 1 },
    { title: 'Chill', id: 2 },
    { title: 'Dubstep', id: 3 },
    { title: 'Indie', id: 4 },
    { title: 'Rap', id: 5 },
    { title: 'Cowbell', id: 6 }
  ];
})

	
	
	/*
	
{"access_token":"9E83tzHtGjoAAAAAAAAAN9qyr5QPgYpDdZVzFhx1evTcoXFoSNSmkLPBjtbAq_io","token_type":"bearer","uid":"366677678"}"

	
	*/
.controller('StartCtrl',function($scope, $cordovaOauth,$ionicPlatform){
	$ionicPlatform.ready(function() {
	
	$scope.isAuthenticated=false;
	$scope.token='none';
	if(!$scope.isAuthenticated)
	{
		 console.log("********tring*************");
	$cordovaOauth.dropbox('kn14g1sqmk5byv9').then(function(result) {
            console.log(JSON.stringify(result));
        }, function(error) {
            console.log(error);
        });
		
	}
	
		else{
	$scope.token=	'9E83tzHtGjoAAAAAAAAANWb6ev6wgFsRJtfF7Ki_Gi1ZrJ-2LH9TrMQS9uoaFD3T';
		}
		})
})
	
.controller('PlaylistCtrl', function($scope, $stateParams) {
});
