def call(Map pipelineParams) {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    //echo "Hello, ${name}."
  //  fillCron()
   // def schedule = new fillCron()

    pipeline {
        agent any
        //parameters {
          //  string(name: 'RUN_ENV', defaultValue: 'stage', description: 'Which environment will run?')
          //  string(name: 'SERVICE', defaultValue: 'dashboard', description: 'Service to run')
       // }

       // fillCron()
        triggers {
            parameterizedCron("""fillCron()""")
        }
    /* triggers {
            parameterizedCron('''
               H/2 * * * * %RUN_ENV=production
               H/3 * * * * %SERVICE=case
               H/3 * * * * %SERVICE=inventory
               H/3 * * * * %SERVICE=router
               H/3 * * * * %SERVICE=shipping
                ''')
        }*/
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