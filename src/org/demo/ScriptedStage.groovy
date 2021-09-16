class ScriptedStage {
    private final Script script

    ScriptedStage(Script script) {
        this.script = script
    }
    
    void scmCheckout() {
        script.stage("SCM Checkout") {
            script.echo "Triggering SCM Checkout stage..."
            script.bat "scm checkout"
        }
    }

    // You can pass as many parameters as needed
    void execute(String name, boolean param1) {
        script.stage(name) {
            script.echo "Triggering ${name} stage..."
           // script.sh "echo 'Execute your desired bash command here'"
            script.bat "mvn -v"
            if (param1) {
                script.bat "echo 'Executing conditional command, because param1 == true'"
            }
        }
    }
    
    
}
