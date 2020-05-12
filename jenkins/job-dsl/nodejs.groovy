job('NodeJS example') {
    scm {
        git('git://github.com/enesccinar/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@enescinar.net')
        }
    }
    triggers {
        scm('H/5 * * * *') // triggered every 5 hours
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install") // this shell command will be executed
    }
}
