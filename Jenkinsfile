//@Library('SharedLibSTF_Jenkins') _
//welcomeJob "lambdatest"

def myUtils = new org.demo.buildUtils()
myUtils.checkOutFrom(repo)


node {
  
  stage("MVN Check") {
    bat "mvn -v"
  }
  
}
