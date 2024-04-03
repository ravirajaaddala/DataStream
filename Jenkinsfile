pipeline {
    agent any

    environment {
        GCLOUD_PATH = '/var/jenkins_home/google-cloud-sdk/bin'
        PROJECT_ID = 'your-gcp-project-id'
        SERVICE_NAME = 'your-service-name'
        IMAGE = 'gcr.io/$PROJECT_ID/$SERVICE_NAME'
        REGION = 'your-region' // e.g., us-central1
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t $IMAGE .'
            }
        }
        stage('Push') {
            steps {
                echo 'Pushing image to Google Container Registry...'
                sh '$GCLOUD_PATH/gcloud auth configure-docker'
                sh 'docker push $IMAGE'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying to Google Cloud Run...'
                sh '$GCLOUD_PATH/gcloud run deploy $SERVICE_NAME --image $IMAGE --platform managed --region $REGION --allow-unauthenticated'
            }
        }
    }
}
