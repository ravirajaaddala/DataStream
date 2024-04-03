pipeline {
	agent any

	environment {
		mavenHome = tool 'jenkins-maven'
		CLOUDSDK_CORE_PROJECT='thermal-rain-417820'
		GCLOUD_CREDS=credentials('gcloud-creds')
		CLIENT_EMAIL='jenkins-gcloud@thermal-rain-417820.iam.gserviceaccount.com'
	}

	tools {
		maven 'jenkins-maven' 
		jdk 'jdk21'
	}

	stages {

		stage('Build'){
			steps {
				bat 'gcloud version'
                    		bat 'gcloud auth activate-service-account --key-file=%GCLOUD_CREDS%'
                    		bat 'gcloud compute zones list'
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				echo "mvn test"
			}
		}

		stage('Deploy') {
			steps {
			    bat "mvn jar:jar deploy:deploy"
			}
		}
	}
	post {
		always{
			bat 'gcloud auth revoke %CLIENT_EMAIL%'
		}
	}
}
