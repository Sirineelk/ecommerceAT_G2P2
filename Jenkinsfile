pipeline {
    agent any

    parameters {
        string(name: 'SELENIUM_BROWSER', defaultValue: 'CHROME')
    }

    stages {
        stage('Define Workspace path') {
            steps {
                script {
                    env.WORKSPACE_PATH = "C:/Dev/jenkins/workspace/POEI_P2_G2/src/test/resources/features"
                }
            }
        }

        stage('Get Features') {
            steps {
                bat """
                    @echo off
                    set /p TOKEN=<token.txt
                    echo Export features from Xray...
                    curl -H "Authorization: Bearer %TOKEN%" ^
                         "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=POEI25G2P2-72" ^
                         -o exported_features.zip

                    for %%F in (exported_features.zip) do if %%~zF LSS 500 (
                        echo Erreur : Le fichier ZIP est invalide ou contient une erreur JSON.
                        type exported_features.zip
                        exit /b 1
                    )
                """

                powershell """
                    \$zip = "exported_features.zip"
                    \$dest = "${env.WORKSPACE_PATH}"
                    if (!(Test-Path \$dest)) { New-Item -ItemType Directory -Path \$dest | Out-Null }
                    Expand-Archive -Path \$zip -DestinationPath \$dest -Force
                """

                bat "del exported_features.zip"
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -Dselenium.browser=${params.SELENIUM_BROWSER}"
            }
        }
    }

    post {
        always {
            echo 'Récupération du token Xray...'
            bat """
                @echo off
                curl -s -H "Content-Type: application/json" -X POST ^
                     --data "{\\"client_id\\":\\"AE8CFFEBED9D442D90AC19F872B22D79\\",\\"client_secret\\":\\"42a7a7d70520a256f83e069ca96c4eb3a05e59a41a1f2cd168f2c03efa181d25\\"}" ^
                     https://xray.cloud.getxray.app/api/v1/authenticate > raw_token.txt

                set /p RAW_TOKEN=<raw_token.txt
                set TOKEN=%RAW_TOKEN:"=%
                echo %TOKEN% > token.txt
                echo Token récupéré et nettoyé.
            """
        }

        success {
            echo 'Tests exécutés avec succès :tada:'
        }

        failure {
            echo 'Des tests ont échoué :x:'
        }
    }
}