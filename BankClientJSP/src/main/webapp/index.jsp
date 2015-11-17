<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
</head>
<body>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="bower_components/jquery.soap/jquery.soap.js"></script>
	<script>
		$.soap({
			url: 'http://localhost:9763/services/Bank/',
			namespaceURL:'http://bank.cashbaby.services.alma.org'
		});
		$.soap({
			method: 'pay',
			data: {
				from: 'A',
				to: 'Ca$hBaby',
				amount: 10.5,
				label: 'I am a label'
			},
			soap12: true,
			success: function (soapResponse) {
				// do stuff with soapResponse
				console.log(soapResponse);
				console.log(soapResponse.toString());
			},
			error: function (soapResponse) {
				console.log('that other server might be down...');
				console.log(soapResponse);
				console.log(soapResponse.toString());
			}
		});
		$.soap({
			method: 'pay',
			data: {
				from: 'D',
				to: 'Ca$hBaby',
				amount: 10.5,
				label: 'I am a label'
			},
			success: function (soapResponse) {
				// do stuff with soapResponse
				console.log(soapResponse);
				console.log(soapResponse.toString());
			},
			error: function (soapResponse) {
				console.log('that other server might be down...');
				console.log(soapResponse);
				console.log(soapResponse.toString());
			}
		});
		
	</script>
</body>
</html>
