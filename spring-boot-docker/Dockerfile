# get Java Image first
FROM openjdk:11
# exponer el puerto 8080. the same as spring
EXPOSE 8080
# el jar creado en el pom se agrega acá
ADD target/app.jar app.jar
# indicar comando para ejecutar jar "java -jar app.jar". aca tmb se puede limitar la memoria con otros argumentos
ENTRYPOINT ["java", "-jar", "app.jar"]


