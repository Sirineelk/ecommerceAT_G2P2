pipeline {
    agent any

    parameters {
        string(name: 'SELENIUM_BROWSER', defaultValue: 'CHROME')
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Utilisation du code source cloné par Jenkins'
            }
        }

        stage('Get Xray Token') {
            steps {
                bat '''
                @echo off
                echo Recuperation du token Xray...
                curl -s -H "Content-Type: application/json" -X POST ^
                     --data "{\\"client_id\\":\\"AE8CFFEBED9D442D90AC19F872B22D79\\",\\"client_secret\\":\\"42a7a7d70520a256f83e069ca96c4eb3a05e59a41a1f2cd168f2c03efa181d25\\"}" ^
                     https://xray.cloud.getxray.app/api/v1/authenticate > raw_token.txt

                REM Nettoyage du token (enlever les guillemets)
                set /p RAW_TOKEN=<raw_token.txt
                set TOKEN=%RAW_TOKEN:"=%
                echo %TOKEN% > token.txt
                echo Token recupere et nettoye.
                '''
            }
        }

        stage('Export Features from Xray') {
            steps {
                bat '''
                @echo off
                set /p TOKEN=<token.txt
                echo Export features from Xray...
                curl -H "Authorization: Bearer %TOKEN%" ^
                     "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=POEI25G2P2-145" ^
                     -o exported_features.zip

                for %%F in (exported_features.zip) do if %%~zF LSS 500 (
                    echo Erreur : Le fichier ZIP est invalide ou contient une erreur JSON.
                    type exported_features.zip
                    exit /b 1
                )
                '''

                powershell '''
                $zip = "exported_features.zip"
                $dest = "exported_features"
                if (Test-Path $dest) { Remove-Item $dest -Recurse -Force }
                Expand-Archive -Path $zip -DestinationPath $dest -Force
                $target = "src/test/resources/features"
                if (!(Test-Path $target)) { New-Item -ItemType Directory -Path $target | Out-Null }
                Copy-Item "$dest\\*.feature" -Destination $target -Recurse -Force
                '''
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                // catchError permet de continuer le pipeline même si les tests échouent
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean test -Dbrowser=${params.SELENIUM_BROWSER}"
                }
            }
        }

        stage('Upload Results to Xray') {
            steps {
                bat '''
                @echo off
                set /p TOKEN=<token.txt
                if exist target\\cucumber.json (
                    echo Import des resultats vers Xray...
                    curl -H "Content-Type: application/json" -X POST ^
                         -H "Authorization: Bearer %TOKEN%" ^
                         --data @"target\\cucumber.json" ^
                         "https://xray.cloud.getxray.app/api/v2/import/execution/cucumber"
                ) else (
                    echo Erreur : cucumber.json introuvable !
                    exit /b 1
                )
                '''
            }
        }
    }

    post {
        always {
            echo 'Archivage des artifacts et nettoyage de l’espace de travail'
            archiveArtifacts artifacts: '*.zip', fingerprint: true
            deleteDir()
        }
    }
}