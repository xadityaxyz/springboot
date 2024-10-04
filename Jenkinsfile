pipeline {
    agent any
    environment {
        MYSQL_USER = credentials('mysqlup') 
        MYSQL_PASSWORD = credentials('mysqlup')
    }

    stages {
        stage('code') {
            steps {
                echo "cloning the code"
                git url:"https://github.com/xadityaxyz/springboot.git", branch: "master"
            }
        }

        stage('delete images and containers') {
            steps {
               
                echo "stop and delete docker container and images"
                sh 'docker stop $(docker ps -a -q)'
                sh 'docker rm $(docker ps -a -q)' 
                sh 'docker rmi $(docker images -q) -f' 
            }
        }
        stage('build') {
            steps {
               
                echo "deployment stage"
                sh 'docker build -t sprint_project-app .'

            }
        }
        stage('deploy') {
            steps {
                echo "deploying the docker image to the container"
                 sh 'docker-compose down'
                 sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'Build and Deployment Successful!'
        }
        failure {
            echo 'Build or Deployment Failed'
        }
    }
}
