//@Library('SharedLibSTF_Jenkins') _
//welcomeJob "lambdatest"
@Library('SharedLibSTF_Jenkins')
import org.demo.Utilities

def utils = new Utilities(env, steps)
node {
  utils.mvn 'clean package'
}

node {
  
  stage("MVN Check") {
    bat "mvn -v"
  }
  
}
