FROM eclipse-temurin:17
COPY build/libs/*.jar order_application.jar
ENTRYPOINT ["java","-jar","/order_application.jar"]