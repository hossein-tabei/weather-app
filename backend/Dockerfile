FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM eclipse-temurin:17
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#EXPOSE 8080
#ENTRYPOINT ["java","-cp","app:app/lib/*","demo.application.backend.WebApplication"]