// Chekout repo1 to ./repo1 and repo2 to ./repo2
node ('master') {
    stage ('Checkout repo1'){
        sshagent (credentials: ['credentials-id']) {
            // Ugly adding gerrit host key, do not use in real job
            sh "ssh-keyscan -p 29418 gerrit.example.com > ~/.ssh/known_hosts" 
            sh "if [ -d repo1 ];then cd repo1; git fetch; git rebase; else git clone ssh://jenkins-user@gerrit.example.com:29418/repo1/repo1; fi"
        }
    }
    stage ('Checkout repo2'){
        sshagent (credentials: ['credentials-id']) {
            sh "if [ -d repo2 ];then cd repo2; git fetch; git rebase; else git clone ssh://jenkins-user@gerrit.example.com:29418/example-repo1/repo2; fi"
        }
        sh "ls -l"
    }
}
