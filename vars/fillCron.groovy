def call (Map pipelineParams) {
    //getCron(){
    sh 'printenv'
    /*def cron="""H/2 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=alpha-broder-test
            H/40 * * * * % SERVICE=inventory;SCRIPT=alpha-broder-test
            H/10 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=orderBlanks/order
            H/50 * * * * % SERVICE=inventory;SCRIPT=orderBlanks/order
            H/15 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=rejections/pruneRejections
            H/55 * * * * % SERVICE=inventory;SCRIPT=rejections/pruneRejections
            H/20 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=updateOrder/importMissing
            H/35 * * * * % SERVICE=inventory;SCRIPT=updateOrder/importMissing
            H/5 * * * * % RUN_ENV=production;SERVICE=inventory;SCRIPT=updatePrimeInventory/pullAos"""*/
    def cron = readFile "${env.WORKSPACE}/cron.txt"
    return cron
    //}
    /*def lines = readFile.readFileInList("cron.txt")
    println lines*/
}