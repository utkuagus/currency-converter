# currency-converter

### Run through docker (powershell)

(mvn clean package) -and (copy target\myapp.war docker\tomcat\myapp.war)

cd docker

docker-compose up --build

### URL

http://localhost:8080/myapp/

### if sh files not found error

dos2unix docker/postgres/*.sh docker/tomcat/*.sh (in git bash)
