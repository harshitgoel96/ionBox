angular.module('starter.controllers', [])
/*
<feature name="IonBoxPlugin">
<param name="android-package" value="com.harshit.ionBox.DB.IonBoxPlugin" />
</feature>
 */
.controller('AppCtrl', function ($scope, $ionicModal, $timeout) {
	// Form data for the login modal
	$scope.loginData = {};

	// Create the login modal that we will use later
	$ionicModal.fromTemplateUrl('templates/login.html', {
		scope : $scope
	}).then(function (modal) {
		$scope.modal = modal;
	});

	// Triggered in the login modal to close it
	$scope.closeLogin = function () {
		$scope.modal.hide();
	};

	// Open the login modal
	$scope.login = function () {
		$scope.modal.show();
	};

	// Perform the login action when the user submits the login form
	$scope.doLogin = function () {
		console.log('Doing login', $scope.loginData);

		// Simulate a login delay. Remove this and replace with your login
		// code if using a login system
		$timeout(function () {
			$scope.closeLogin();
		}, 1000);
	};
})

.controller('PlaylistsCtrl', function ($scope) {
	$scope.playlists = [{
			title : 'Reggae',
			id : 1
		}, {
			title : 'Chill',
			id : 2
		}, {
			title : 'Dubstep',
			id : 3
		}, {
			title : 'Indie',
			id : 4
		}, {
			title : 'Rap',
			id : 5
		}, {
			title : 'Cowbell',
			id : 6
		}
	];
})

/*{"access_token":"9E83tzHtGjoAAAAAAAAAN9qyr5QPgYpDdZVzFhx1evTcoXFoSNSmkLPBjtbAq_io","token_type":"bearer","uid":"366677678"}"


 */
.controller('StartCtrl', function ($scope, $cordovaOauth, $ionicPlatform, $cordovaSQLite) {
	$ionicPlatform.ready(function () {

		$scope.isAuthenticated = false;
		$scope.token = 'empty';
		// end of db.transaction

		cordova.exec(function success(data) {
			console.log("call completed with success");
			console.log("response from CORDOVA EXEC" + JSON.stringify(data));

			if (data.success == true) {

				if (data.valuePresent == true) {
					$scope.isAuthenticated = true;
					$scope.token = data.value;
					console.log("!!!!!!!!!!!!!! trying to get token::" + data.value);

				} else {
					$scope.isAuthenticated = false;
					$scope.token = 'none';
					console.log("********tring*************");
					$cordovaOauth.dropbox('kn14g1sqmk5byv9').then(function (result) {
						console.log(JSON.stringify(result));
						//var data = JSON.parse(result);
						cordova.exec(function success(data) {
							console.log("**************" + JSON.stringify(data));
							$scope.isAuthenticated = true;
							$scope.token = result.access_token;

						}, function error(err) {
							console.log("!!!! error " + err);
						}, 'IonBoxPlugin',
							'storeInCache',
							['token', result.access_token]);

					}, function (error) {
						console.log(error);
					});
				}
				
			}
		}, function error(err) {
			console.log("call completed with error");
			console.log("!!!! error " + err);
		}, 'IonBoxPlugin',
			'getFromCache',
			['token']);

		});
})

//browseCtrl
.controller('browseCtrl', function ($scope, $stateParams) {
	$scope.Data = JSON.parse('{"hash": "0edd30c0be941dc48f2281cc638eee05", "thumb_exists": false, "bytes": 0, "path": "/", "is_dir": true, "icon": "folder", "root": "dropbox", "contents": [{"rev": "42e41048b",	"thumb_exists": false, "path": "/dummy folder", "is_dir": true, "icon": "folder", "read_only": false, "modifier": null, "bytes": 0, "modified": "Thu, 23 Apr 2015 05:50:05 +0000", "size": "0 bytes", "root": "dropbox",	"revision": 4}, {"rev": "12e41048b", "thumb_exists": false, "path": "/Getting Started.pdf", "is_dir": false, "client_mtime": "Thu, 11 Dec 2014 11:22:10 +0000", "icon": "page_white_acrobat", "read_only": false,	"modifier": null, "bytes": 249159, "modified": "Thu, 11 Dec 2014 11:22:09 +0000", "size": "243.3 KB", "root": "dropbox", "mime_type": "application/pdf", "revision": 1}, {"rev": "32e41048b", "thumb_exists": false,	"path": "/Henning Nelms - Magic and Showmanship.pdf", "is_dir": false, "client_mtime": "Fri, 12 Dec 2014 06:17:26 +0000", "icon": "page_white_acrobat", "read_only": false, "modifier": null, "bytes": 56311222,	"modified": "Fri, 12 Dec 2014 06:17:26 +0000", "size": "53.7 MB", "root": "dropbox", "mime_type": "application/pdf", "revision": 3}, {"rev": "22e41048b", "thumb_exists": false, "path": "/mycolt.zip", "is_dir": false,	"client_mtime": "Thu, 11 Dec 2014 11:31:08 +0000", "icon": "page_white_compressed", "read_only": false, "modifier": null, "bytes": 86047754, "modified": "Thu, 11 Dec 2014 11:31:08 +0000", "size": "82.1 MB", "root":	"dropbox", "mime_type": "application/zip", "revision": 2}], "size": "0 bytes"}'); 
	$scope.contents = $scope.Data.contents;
	$scope.shouldShowDelete = false;
	$scope.alerter = function (text) {
		alert(text);
	}
	$scope.toggleDelete = function () {
		$scope.shouldShowDelete = !$scope.shouldShowDelete;
	}
	$scope.getIconClass = function (appIcon) {
		//console.log(appIcon);
		switch (appIcon) {
		case "page_white_compressed":
			return "icon fa fa-file-archive-o";
			break;
		case "page_white_acrobat":
			return "icon fa fa-file-pdf-o";
			break;
		case "folder":
			return "icon ion-folder";
			break;
		}
		return "icon ion-document"
	}
})

.controller('PlaylistCtrl', function ($scope, $stateParams) {});
