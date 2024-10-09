pipeline {
    agent any
    environment {
        MYSQL_USER = credentials('mysqlup')  // Fetches the username from Jenkins credentials
        MYSQL_PASSWORD = credentials('mysqlup')  // Fetches the password from Jenkins 
    }

    stages {
        stage('Code') {
            steps {
                echo "Cloning the code"
                git url: "https://github.com/xadityaxyz/springboot.git", branch: "master"
            }
        }

        stage('Delete Images and Containers') {
            steps {
                echo "Stopping and deleting Docker containers and images"
                sh 'docker stop $(docker ps -a -q) || true'
                sh 'docker rm $(docker ps -a -q) || true' 
                sh 'docker rmi $(docker images -q) -f || true' 
            }
        }

        stage('Build') {
            steps {
                echo "Building the Docker image"
                sh 'docker build -t sprint_project-app .'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying the Docker image to the container"
                sh 'docker-compose down || true'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            echo "MySQL User: ${MYSQL_USER}"  // Print username for debugging
            echo "MySQL Password: ${MYSQL_PASSWORD}"  // Print password for debugging (use with caution)
        }
        success {
            echo 'Build and Deployment Successful!'
        }
        failure {
            echo 'Build or Deployment Failed'
        }
    }
}
