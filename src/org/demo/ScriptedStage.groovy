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
    void execute(String name) {
        script.echo "Triggering ${name} stage..."
        if (name == "Checkout SCM") {
            steps.stage(name) {
                 steps.checkout scm
            }
        }
        if (name == "Tests") {
            steps.stage(name) {
                steps.bat "mvn test"
            }
        }
        if (name == "Sonar") {
            steps.stage(name) {
                steps.bat "mvn clean verify sonar:sonar -Dsonar.login=f260730b8650aba93bb9cdad3310b95dbb1eec4e"
            }
        }
        if (name == "Artifactory") {
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
        }
        
    } // end execute
        
}
