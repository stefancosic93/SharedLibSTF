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

    void execute(Object params) {
        steps.bat "echo Checkout:${params.Checkout}"
        steps.bat "echo RunTests:${params.RunTests}"
        steps.bat "echo RunSonar:${params.RunSonar}"
        
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
        
    } // end execute
        
}
