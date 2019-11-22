def call(Map pipelineParams) {
    pipeline {
        agent any
        triggers {
            parameterizedCron(fillCron())
        }
        //parameters {
          //  string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
          //  string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')

        node {
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

}