pipeline {
    agent any

    environment {
        PROJECT_ID = 'thermal-rain-417820'
        SERVICE_NAME = 'datastream'
        REGION = 'us-central1' // e.g., us-central1
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building Docker image...'
                bat 'docker build -t gcr.io/%PROJECT_ID%/%SERVICE_NAME%' .'
            }
        }
        stage('Push') {
            steps {
                echo 'Pushing image to Google Container Registry...'
                bat 'gcloud auth configure-docker'
                bat 'docker push %IMAGE%'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying to Google Cloud Run...'
                bat 'gcloud run deploy %SERVICE_NAME% --image %IMAGE% --platform managed --region %REGION% --allow-unauthenticated'
            }
        }
    }
}
