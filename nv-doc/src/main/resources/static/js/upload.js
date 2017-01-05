
<html>
<head>
<meta charset="UTF-8">
<title>Nisum Drop Box App</title>

<!-- Bootstrap Core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/agency.css" rel="stylesheet">
<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Open+Sans:600'>
<link rel="stylesheet" href="css/style.css">
<!-- link href="css/gateway-assets.css" rel="stylesheet"-->

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">


<style type="text/css">
[ng\:cloak], [ng-cloak], .ng-cloak {
	display: none !important;
}

.button {
	-moz-appearance: button;
	/* Firefox */
	-webkit-appearance: button;
	/* Safari and Chrome */
	padding: 10px;
	margin: 10px;
	width: 126px;
}

.drop-box {
	background: #F8F8F8;
	border: 5px dashed #DDD;
	height: 65px;
	text-align: center;
	padding-top: 17px;
	margin: 10px;
}

.dragover {
	border: 5px dashed blue;
}

.progress div {
	font-size: smaller;
	background: orange;
	width: 0;
}

ul {
	list-style-type: none;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body ng-app="fileUpload" ng-cloak class="ng-cloak">

	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">upload docs</button>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">upload files</h4>
				</div>
				<div class="modal-body">

					<div ng-app="fileUpload" ng-controller="MyCtrl">
						<div ng-show="legthIncreased" style="color: red">{{length}}</div>
						<div ng-show="SizeIncreased" style="color: red">{{Size}}</div>
					<div ng-show="fileFormat" style="color: red">{{File}}</div>
					<div ng-show="fileFormat" style="color: red">{{Type}}</div>

						<div ngf-drop ngf-select ng-model="files" class="drop-box"
							ngf-drag-over-class="dragover" accept="image/*,.doc,.xslx,application/pdf,.zip,.rar,.mp4,.avi,.wmv,.mpeg" ngf-multiple="true"
							ngf-allow-dir="true">Drop files here or click to
							upload</div>
						<div ngf-no-file-drop>File Drag/Drop is not supported for
							this browser</div>
						

						<div ng-show="ShowFilesProgress">
						Files:
							<ul>
								<li ng-repeat="fileName in files" style="font: smaller">Uploading
									:: {{fileName.name}} <span class="progress"
									ng-show="fileName.progress >= 0">
										<div style="width: {{fileName.progress}}%"
											ng-bind="fileName.progress + '%'"></div>
								</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" 
						data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	</div>

	<script src="js/angular-bootstrap.js" type="text/javascript"></script>
	<script src="js/ng-file-upload-shim.min.js"></script>
	<!-- for no html5 browsers support -->
	<script src="js/ng-file-upload.min.js"></script>
	<script src="js/document.js" type="text/javascript"></script>

</body>
</html>