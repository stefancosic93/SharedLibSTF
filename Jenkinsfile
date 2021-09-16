@Library('SharedLibSTF_Jenkins') _
welcomeJob "lambdatest"

node {
  
  tools {
     maven 'Maven 3.8.2'
     jdk 'jdk11'
  }
  stage("MVN Check") {
    bat "mvn -v"
  }
  
}
