job('NodeJS Docker example') {
    scm {
        git('git://github.com/enesccinar/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@enescinar.net')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('enesccinar/docker-nodejs-demo') // docker repo name on dockerhub
            tag('${GIT_REVISION,length=9}') // taging with unique id/sha. if we leave it empty, it would be latest
            registryCredentials('dockerhub')
            forcePull(false) // http://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.dockerBuildAndPublish
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
