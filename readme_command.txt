sudo kill -9 $(ps -ef | grep "wiremock" | awk '{print $2}')
sudo yum install java-1.8.0-openjdk-devel
/usr/lib/jvm/java-1.7.0/bin
/lib/jvm/java-1.8.0/bin
semanage port -l | grep http_port_t
semanage port -a -t http_port_t  -p tcp 8090
http://XXXXXXXX:9096/EmployeeSalary?EmployeeID=1


                                 # echo "script started"
                                  #chmod 777 gradlew
                                  #chmod 777 startup.sh
                                  #export JENKINS_NODE_COOKIE=dontKillMe
                                  #export JAVA_OPTS="-Xmx1048m -XX:MaxPermSize=512m -DportNumer=9096"
                                 #nohup ./gradlew execute &
