import jenkins.model.*
//import fillCron
jenkins = Jenkins.instance

def call(Map pipelineParams) {
def fc= new fillCron()



    pipeline {
        agent any
        parameters {
            string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
            string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')
            string(name: 'SCRIPT', defaultValue: 'dashboard', description: 'Script to run')
        }
        triggers {
            /*parameterizedCron("""H/2 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=alpha-broder-test
            H/40 * * * * % SERVICE=inventory;SCRIPT=alpha-broder-test
            H/10 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=orderBlanks/order
            H/50 * * * * % SERVICE=inventory;SCRIPT=orderBlanks/order
            H/15 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=rejections/pruneRejections
            H/55 * * * * % SERVICE=inventory;SCRIPT=rejections/pruneRejections
            H/20 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=updateOrder/importMissing
            H/35 * * * * % SERVICE=inventory;SCRIPT=updateOrder/importMissing
            H/5 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=updatePrimeInventory/pullAos """)*/
            parameterizedCron(fc)
            //parameterizedCron(lines)

        }
        stages {
           // node {
                stage("Cron") {
                    steps {
                        echo "${params.SERVICE} ${params.RUN_ENV}"
                        script { currentBuild.description = "${params.SERVICE} ${params.SCRIPT} ${params.RUN_ENV}" }
                        //sh 'PROJECT=oo SERVICE=case . /var/oo/etc/scripts/init-nvm.sh '
                        echo "PROJECT=oo SERVICE=${params.SERVICE} . /var/oo/etc/scripts/init-nvm.sh "
                        //sh 'NODE_ENV=production coffee /var/oo/oo-case/crons/reject2.coffee"
                        echo "NODE_ENV=${params.RUN_ENV} coffee /var/oo/oo-${params.SERVICE}/crons/${params.SCRIPT}.coffee"
                    }
                }
            //}
        }
    }

}