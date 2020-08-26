# CryptoWebservice
A spring-boot based simple web service client and server that encrypts and decrypts data and runs over SSL using self signed certificates
Starts a tomcat server on port 8443. Client and server can use mutual SSL authentication (but is disabled for simplicity in life).

This was originally an assignment during an interview process. 

# Build the server 
cd CryptoService-Server
mvn clean install
mvn spring-boot:run

(starts the tomcat service at port 8080)


# Build the client
cd CryptoService-Client
mvn clean install
java -jar target/CryptoService-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

Id:8872b551-19fe-4201-bf69-451b0d092ea3
Encrypted Content:a4651d06dafd55ec327838b00442630c$c467d10bb67f2bf6f32274127b4e5ed9
Id:8872b551-19fe-4201-bf69-451b0d092ea3
Decrypted Content:Hello world

