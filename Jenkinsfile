node {
def mvnHome
stage('Prepare') {
git url: 'https://github.com/Jaasir/spring-devops.git', branch: 'develop'
mvnHome = tool 'maven'
}
stage('Build') {
if (isUnix()) {
sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
} else {
bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
}
}

stage('Run Docker image') {
steps {
echo "-=- run Docker image -=-"
sh "docker run --name ${TEST_CONTAINER_NAME} --detach --rm --network ci --expose 6300 --env JAVA_OPTS='-javaagent:/jacocoagent.jar=output=tcpserver,address=*,port=6300' ${ORG_NAME}/${APP_NAME}:latest"
}
}
stage('Unit Test') {
junit '**/target/surefire-reports/TEST-*.xml'
archive 'target/*.jar'
}
stage('Integration Test') {
if (isUnix()) {
sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean verify"
} else {
bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean verify/)
}
}
stage('Sonar') {
if (isUnix()) {
sh "'${mvnHome}/bin/mvn' sonar:sonar"
} else {
bat(/"${mvnHome}\bin\mvn" sonar:sonar/)
}
}

stage("Docker build") {
steps {
sh "docker build -t Jaasir/spring-devops:${BUILD_TIMESTAMP} ."
}
}

stage("Docker login") {
steps {
withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '',
usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
sh "docker login --username $USERNAME --password $PASSWORD"
}
}
}

stage("Docker push") {
steps {
sh "docker push Jaasir/spring-devops:${BUILD_TIMESTAMP}"
}
}
}

