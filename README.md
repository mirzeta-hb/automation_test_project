# ehs_regression
clone project from github: git clone projectname
navigate to project folder: cd projectfolder
import all maven updates: mvn clean install
set browser value in regression.xml: value="chrome" or remote-chrome if you run TCs in docker

docker-compose build
docker-compose up -d
VNC credentials : localhost/secret

run TC : mvn clean test -DsuiteXmlFile=regression.xml

Check reports in target folder