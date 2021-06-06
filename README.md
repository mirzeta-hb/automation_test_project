# ehs_regression
clone project from github: git clone automation_test_project

navigate to project folder: cd automation_test_project

import all maven updates: mvn clean install
set browser value in regression.xml: value="chrome" or remote-chrome if you run TCs in docker

setup application url in regression.xml
<parameter name="url" value="pathToApp/Application/EHS.html"/>

docker-compose build
docker-compose up -d
VNC credentials : localhost/secret

run TC : mvn clean test -DsuiteXmlFile=regression.xml

Check reports in target folder
