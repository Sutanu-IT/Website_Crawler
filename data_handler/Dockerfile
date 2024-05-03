FROM openjdk:17-jdk-alpine
# Set the working directory
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
# Expose port 8080
EXPOSE 8080
# Entry point to run the application
ENTRYPOINT ["java","-jar","app.jar"]




## Copy the Gradle wrapper files
#COPY gradlew .
#COPY gradle ./gradle
#
## Copy the build file and settings
#COPY build.gradle .
#COPY settings.gradle .
#
## Download dependencies and build the application
##RUN ./gradlew build --no-daemon
#
## Copy the compiled application
#COPY . .

# Build the application
#RUN ./gradlew build

# Second stage, use a smaller OpenJDK 11-based image
#FROM openjdk:11-jre-slim
#
# Set the working directory
#WORKDIR /app
#
# Copy the compiled application from the build stage
#COPY --from=build /app/build/libs/*.jar app.jar