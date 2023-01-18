pipeline {
    agent any
    tools{
        maven '3.8.7'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('package'){
            steps{
            bat 'mvn package'
            }
        }
        stage('test'){
            steps {
                bat 'mvn test'
            }
        }
        stage('deploy'){
            steps{
                bat 'mvn spring-boot:run'
            }
        }
    }
}
