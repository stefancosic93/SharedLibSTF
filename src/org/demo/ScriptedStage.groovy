class ScriptedStage {
    private final Script script

    ScriptedStage(Script script) {
        this.script = script
    }
    
    void scmCheckout() {

    }

    // You can pass as many parameters as needed
    void execute(String name) {
        if (name == "Checkout SCM") {
                script.stage("Checkout SCM") {
                    script.echo "Triggering Checkout SCM stage..."
                    script.bat "checkout scm"
            }
        }
        script.stage(name) {
            script.echo "Triggering ${name} stage..."
           // script.sh "echo 'Execute your desired bash command here'"
            script.bat "mvn -v"
        }
    }
    
    
}
