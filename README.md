# currency-converter

### Run through docker

(mvn clean package -DskipTests) -and (copy target\myapp.war docker\tomcat\myapp.war)

cd docker

docker-compose up --build

### URL

http://localhost:8080/myapp/
