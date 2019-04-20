#!/usr/bin/env bash
echo $WORKSPACE
export JENKINS_NODE_COOKIE=dontKillMe
classpath=./out/production/classes:./tool/wiremock-standalone-2.19.0.jar:./tool/commons-csv-1.6.jar:./tool/org.json.jar
/usr/lib/jvm/java-1.8.0/bin/javac -cp $classpath -d ./out/production/classes ./src/main/java/com/stub/framework/driver/*.java
/usr/lib/jvm/java-1.8.0/bin/javac -cp $classpath -d ./out/production/classes ./src/main/java/com/stub/framework/csvreader/*.java
/usr/lib/jvm/java-1.8.0/bin/javac -cp $classpath -d ./out/production/classes ./src/main/java/com/stub/framework/stubs/*.java
/usr/lib/jvm/java-1.8.0/bin/javac -cp $classpath -d ./out/production/classes ./src/main/java/com/stub/framework/transformers//*.java
echo "compilation done "
nohup java -DportNumer=$1  -cp "$classpath" com.stub.framework.driver.mainDriver &
echo "node started $1"