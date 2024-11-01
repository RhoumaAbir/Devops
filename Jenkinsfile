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
                    sh 'mvn clean install' // Changer cette commande si nécessaire
                }
            }
        }

          stage('SonarQube Analysis') {
                   steps {
                  sh "mvn sonar:sonar -Dsonar.login=${'sqa_28c1b7500df800b00f51b07545dcac6b6a4605c2'}"

                        }
              }
          stage('Nexus') {
                          steps {
                              sh 'mvn deploy'
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
