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
        steps.bat "echo Checkout:${params.Checkout}"
        steps.bat "echo RunTests:${params.RunTests}"
        steps.bat "echo RunSonar:${params.RunSonar}"
        steps.bat "echo RunArtifactory:${params.RunArtifactory}"

        
        
        if (params.Checkout) {
            steps.stage("Checkout SCM") {
                 steps.bat "echo Triggering Checkout SCM stage..."
                 steps.checkout scm
            }
        }
       
        if (params.RunTests) {
            steps.stage("Tests") {
                steps.bat "echo Triggering Tests stage..."
                steps.bat "${params.Tests}"
            }
        }
        
        if (params.RunSonar) {
            steps.stage("Sonar") {
                steps.bat "echo Triggering Sonar stage..."
                steps.bat "${params.Sonar} ${params.SonarLogin}"
            }
        }
        
        if (params.RunArtifactory) {
            steps.stage("Artifactory") {
                steps.bat "echo Triggering Artifactory stage..."
                def a = 1
                steps.bat "echo aaaaaaaaaaaaaaaaaaaaaa ${a}"
                
                //def server = Artifactory.server 'artifactory-server'
                /*
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
                */
                
            }
          
        }  
        
    } // end execute
        
}
