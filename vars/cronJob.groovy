import jenkins.model.*
import fillCron
jenkins = Jenkins.instance

def call(Map pipelineParams) {

    pipeline {
        agent none
        parameters {
            string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
            string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')
        }
        triggers {
            parameterizedCron(fillCron())
        }
        stages {
            node {
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