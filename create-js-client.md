# Create javascript Frontend

# Generate web project with maven

```
mvn archetype:generate -DgroupId={project-packaging} 
	-DartifactId={project-name} 
	-DarchetypeArtifactId=maven-archetype-webapp 
	-DinteractiveMode=false
```

# init your bower project and install jQuery SOAP

```
cd project-name/src/main/webapp/
bower init
bower install jquery.soap
```

# include jQuery and jQuery SOAP

add to src/main/webapp/index.jsp the two following lines:

```
<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="bower_components/jquery.soap/jquery.soap.js"></script>
```

# use the following Javascript code to acces to one of your service's method

This is an exemple with a service named Bank, and a method defined like this:
```
boolean pay(String from, String to, float amount, String label)
```

The namespaceURL is defined on top of your .wsdl inside "xmlns:ns" (wsdl 1.1) or "targetNamespace" (wsdl 2.0) attribute.

```
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
```

# deploy the java web application

```
mvn package
```

then go to wso2 > Add > Web Applications and select the .war file inside your project target directory.

You can now see the result, with wso2 > List > project-name > "Go To URL".
