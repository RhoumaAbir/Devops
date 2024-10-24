pipeline {
    agent any
    environment {
        GITHUB_CREDENTIALS_ID = 'helmi_github'
    }
    tools {
        maven 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "Checking out the repository..."
                    git url: 'https://github.com/RhoumaAbir/Devops.git', branch: 'helmi', credentialsId: "${env.GITHUB_CREDENTIALS_ID}"
                }
            }
        }
        stage('Build and Package Application') {
            steps {
                echo 'Building the application...'
                withMaven(maven: 'Maven') {
                    sh 'mvn clean install'
                }
            }
        }
    }
    post {
        success {
            echo 'The build was successful, the deliverable is available in the target directory.'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'The build failed. Check the logs for more details.'
        }
    }
}