pipeline {
    agent any

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
                    sh 'mvn clean install' // Change this command if necessary
                }
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests...'
                withMaven(maven: 'Maven') {
                    sh 'mvn test'
                }
            }
        }

        stage('Code Quality Check via SonarQube') {
             steps {
                 script {
                     // SonarQube analysis
                     withSonarQubeEnv('SonarQube') {
                         sh '''
                             mvn clean verify sonar:sonar \
                             -Dsonar.projectKey=sqp_0027321b43c30106a2ca082e87f542b288a71fc5 \
                             -Dsonar.projectName="Devops" \
                             -Dsonar.host.url=http://192.168.56.3:9000
                         '''
                     }
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
