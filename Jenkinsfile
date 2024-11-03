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
                           sh "docker build -t \"${registry}/nodemongoapp:5.0\" ."
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
                   sh "docker run -d --name prometheus -p 9090:9090 \ -v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus "                 }
              }
        stage('Test Grafana') {
                  steps {
                   sh " docker run -d --name grafana -p 3000:3000 \ -e "GF_SECURITY_ADMIN_PASSWORD=admin" grafana/grafana "               }
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
