def call (Map pipelineParams) {
    def cron = ["H/2 * * * * %RUN_ENV=production",
       "H/3 * * * * %SERVICE=case",
       "H/3 * * * * %SERVICE=inventory",
       "H/3 * * * * %SERVICE=router",
       "H/3 * * * * %SERVICE=shipping "]
    return cron
}