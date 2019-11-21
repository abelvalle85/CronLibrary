def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    //echo "Hello, ${name}."
    fillCron()

    def schedule = fillCron()

    pipeline {
        agent any
        parameters {
            string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
            string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')
        }

        fillCron()
//        triggers {
//            parameterizedCron('''${schedule} ''')
//        }

        stages {
            stage("Cron") {
                steps {
                    echo "${params.SERVICE} ${params.RUN_ENV}"
                    script { currentBuild.description = "${params.SERVICE} ${params.RUN_ENV}" }
                }
            }
        }
    }

}