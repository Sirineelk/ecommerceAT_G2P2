pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'edge', 'firefox'])
    }

    environment {
        XRAY_TOKEN = credentials('jenkins-xray-token')
        XRAY_EXPORT_URL = 'https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=POEI25G2P2-102'
        XRAY_IMPORT_URL = 'https://xray.cloud.getxray.app/api/v2/import/execution/cucumber/multipart'
        BROWSER = "${params.BROWSER}"
    }

    stages {
        stage('Xray Export') {
            steps {
                bat 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer %XRAY_TOKEN%" %XRAY_EXPORT_URL% -o features.zip'
            }
        }

        stage('Clean Features') {
            steps {
                bat 'rmdir /S /Q src\\test\\resources\\features'
                bat 'mkdir src\\test\\resources\\features'
            }
        }

        stage('Unzip Features') {
            steps {
                bat 'powershell -Command Expand-Archive -Force features.zip src/test/resources/features'
            }
        }

        stage('Clean & Test') {
            steps {
                bat 'mvn clean test'
            }

            post {
                always {
                    bat 'curl -H "Authorization: Bearer %XRAY_TOKEN%" -F "results=@target/cucumber.json;type=application/json" -F "info=@info.json;type=application/json" %XRAY_IMPORT_URL%'
                }
            }
        }
    }
}