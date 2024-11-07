pipeline {
    agent any

    environment {
        registryCredentials = "nexus"
        registry = "192.168.50.6:8083"
          DOCKER_USERNAME = 'abirrh'  // Your Docker username
          DOCKER_PASSWORD = 'docker123'  // Your Docker password
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
                    sh 'mvn clean install'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Ensure token value is not hard-coded in a secure environment
                    def sonarToken = 'sqa_28c1b7500df800b00f51b07545dcac6b6a4605c2'
                    sh "mvn sonar:sonar -Dsonar.login=${sonarToken}"
                }
            }
        }

       stage('Build Docker Image') {
                  steps {
                      echo "Building Docker image..."
                      script {
                          // Construire l'image Docker avec la version spécifiée
                          sh "docker build -t \"${registry}/kaddemapp:1.0\" ."
                      }
                  }
              }

              stage('Push Docker Image') {
                  steps {
                      echo "Pushing Docker image to registry..."
                      script {
                          // Pousse l'image vers le registre
                          sh "docker push ${registry}/kaddemapp:1.0"
                      }
                  }
              }

       stage('Deploy to Nexus') {
           steps {
               sh "mvn deploy "
           }
       }


              stage('Test Prometheus') {
                  steps {
                      script {
                          sh "mvn test -Dproject=prometheus"
                      }
                  }
              }

              stage('Test Grafana') {
                  steps {
                      script {
                          sh "mvn test -Dproject=grafana"
                      }
                  }
              }

         stage('Unit Test') {
             steps {
                 script {

                     sh 'mvn test'
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
