pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'abir_github'
        SONAR_HOST_URL = 'http://192.168.50.6:9000'
        SONAR_PROJECT_KEY = 'Devops'
        SONAR_TOKEN = 'sqa_c31be291ebb4af3922e17e7f0acef56130059e73'
    }

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "Checking out the repository..."
                    git url: 'https://github.com/RhoumaAbir/Devops.git', branch: 'abir_rhouma', credentialsId: "${env.GITHUB_CREDENTIALS_ID}"
                }
            }
        }

        stage('Build and Package Application') {
            steps {
                echo 'Building the application...'
                withMaven(maven: 'Maven') {
                    sh 'mvn clean install' // Changer cette commande si nécessaire
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                withMaven(maven: 'Maven') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                        -Dsonar.host.url=${env.SONAR_HOST_URL} \
                        -Dsonar.login=${env.SONAR_TOKEN}
                    """
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
