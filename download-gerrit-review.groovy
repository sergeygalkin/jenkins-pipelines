node ('master') {
    stage ('Checkout'){
        git url: 'ssh://jenkins-user@gerrit.example.com:35730/database', branch: 'master'
    }
    stage ('Fetch review'){
        if (params.REFSPEC != ""){
            sh "git clean -xfd"
            sshagent (credentials: ['credentials-id']) {
            sh "git fetch ssh://jenkins-user@gerrit.example.com:35730/database $REFSPEC"
            sh "git checkout FETCH_HEAD"
        }
      }
    }
}

