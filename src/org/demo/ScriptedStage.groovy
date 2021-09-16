class ScriptedStage {
    private final Script script
    private final Object scm
    
    ScriptedStage(Script script, Object scm) {
        this.script = script
        this.scm = scm
    }
    
    // You can pass as many parameters as needed
    void execute(String name) {
        script.echo "Triggering ${name} stage..."
        if (name == "Checkout SCM") {
            script.stage(name) {
                
            }
            checkout scm
        }
        if (name == "Tests") {
            script.stage(name) {
                script.bat "mvn test"
            }
        }
        if (name == "Sonar") {
            script.stage(name) {
                script.bat "mvn clean verify sonar:sonar -Dsonar.login=f260730b8650aba93bb9cdad3310b95dbb1eec4e"
            }
        }
        if (name == "Artifactory") {
            script.stage(name) {
                script.bat "echo implement artifactory"
            }
        }
        if (name == "Foo") {
            script.stage(name) {
                script.echo "Triggering ${name} stage..."
                script.bat "mvn -v"
            }
        }
    }
    
    
}
