class ScriptedStage {
    private final Script script
    private final hudson.plugins.git.GitSCM scm
    def env
    def steps
    
    ScriptedStage(Script script, hudson.plugins.git.GitSCM scm, env, steps) {
        this.script = script
        this.scm = scm
        this.env = env
        this.steps = steps
    }
    
    // You can pass as many parameters as needed
    void execute(Object params) {
        steps.bat "echo ${params.Sonar1}"
        steps.bat "echo ${params.Tests2}"
  
        
        //script.echo "Triggering ${name} stage..."

        steps.stage("Checkout SCM") {
             steps.bat "echo Triggering Checkout SCM stage..."
             steps.checkout scm
        }
       
        if (params.Tests.contains("mvn tests")) {
            steps.stage("Tests") {
                steps.bat "echo Triggering Tests stage..."
                steps.bat "${params.Tests}"
            }
        }
        
        if (params.Sonar.contains("sonar")) {
            steps.stage("Sonar") {
                steps.bat "${params.Sonar} ${params.SonarLogin}"
            }
        }
      /*  if (name == "Artifactory") {
          /*
            steps.stage(name) {
                steps.script {
                    def server = Artifactory.server 'artifactory-server'

                    def uploadSpec = """{
                      "files": [
                        {
                          "pattern": "target/*.jar",
                          "target": "MavenRepo/stefan.cosic/"
                        }
                     ]
                    }"""

                    def buildInfo = Artifactory.newBuildInfo()
                    server.upload spec: uploadSpec, buildInfo: buildInfo
                    server.publishBuildInfo buildInfo 
                }
            }
          
        }  */
        
    } // end execute
        
}
