pipeline {
	agent any

    tools {
		maven 'M3'
        jdk 'JDK21'
    }

    stages {
		stage('Checkout') {
			steps {
				git branch: 'main',
                    url: 'https://github.com/majedalshehri1/springboot-jenkins-junit.git'
            }
        }

        stage('Build') {
			steps {
				sh 'mvn clean compile'
            }
        }

        stage('Test') {
			steps {
				sh 'mvn test'
            }
            post {
				always {
					junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
			steps {
				sh 'mvn package -DskipTests'
            }
        }
    }

    post {
		always {
			cleanWs()
        }
        success {
			echo 'Pipeline completed successfully!'
        }
        failure {
			echo 'Pipeline failed!'
        }
    }
}
