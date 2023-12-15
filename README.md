# Weather Forecast Web application : Angular + Spring Boot

This is a repository of weather forecast web application implemented with Spring Boot in back-end side and Angular in front-end side.

Technologies and concepts used in the project are as follows.

Front-end:
- Angular 17.0.4
- Typescript 5.2.2
- Node.js 20.10.0
- Angular Material 17.0.1
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
- Java 17.0.9
- Spring Boot 3.0.4
- Test Driven Development (TDD)
- Unit testing with JUnit 5
- Maven 3.9.4

### Development mode
Two independent applications with example of proxy settings usage.
<br>Run ``npm start`` to start the front-end app

### Production mode
Building the whole application as single JAR file: back-end module depends on front-end one.