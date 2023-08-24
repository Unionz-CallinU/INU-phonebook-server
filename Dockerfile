FROM openjdk:11
ENV TZ=Asia/Seoul
ARG JAR_FILE=./inu-phonebook/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]