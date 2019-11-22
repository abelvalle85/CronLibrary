import jenkins.model.*
import fillCron
jenkins = Jenkins.instance

def call(Map pipelineParams) {

    pipeline {
        agent any
        parameters {
            string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
            string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')
        }
        triggers {
            parameterizedCron("""H/2 * * * * %RUN_ENV=production
                H/3 * * * * %SERVICE=case
                H/3 * * * * %SERVICE=inventory
                H/3 * * * * %SERVICE=router
                H/3 * * * * %SERVICE=shipping""")
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