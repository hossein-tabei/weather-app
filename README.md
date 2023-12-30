# Weather Forecast Web application : Angular + Spring Boot

This is a repository of weather forecast web application implemented with Spring Boot in back-end side and Angular in front-end side.

## Technologies and concepts used in the project

Front-end:
- Angular 17
- Typescript
- Angular Material
- Bootstrap
	- Responsive design
- Implemented with the architecture explained [here](https://dev-academy.com/angular-architecture-best-practices/)
	- Core Module
	- Shared Module
	- Feature Module
		- Reactive state management
		- Core Layer
		- Abstraction Layer
		- Presentation Layer
			- Smart Component
			- Dumb Component

Back-end:
- Java 17
- Spring Boot
- Test Driven Development (TDD)
- Unit testing with JUnit 5
- Maven

## API calling considerations
<p>This application uses OpenWeather API to display forecast data. Using the API requires an API token.</p>
<p>By default if no API token is embedded in the app, some mocked data are displayed.</p>
<p>In order to apply the API to the application do the following steps:</p>
<ol>
	<li> Go to <a href="https://openweathermap.org/api">OpenWeather</a> and get a free API token</li>
	<li> Create a .yml file in <code>src/main/resources</code> directory which its name is <code>application-production.yml</code></li>
	<li> Add the API token to the .yml file as following:</li>
			<code>ioConfig.apiKey: [your API token]</code>
</ol>

## Development mode
Two independent applications with example of proxy settings usage.
<br>Use ``npm start`` command to run the front-end app.

## Deployment
Building the whole application as single JAR file: backend module depends on frontend one.