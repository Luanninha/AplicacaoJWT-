pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'Luanninha', url: 'https://github.com/Luanninha/AplicacaoJWT-DesafioItau.git'
            }
        }

        stage('Build') {
            steps {
                // Exemplo usando Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Executar testes
                sh 'mvn test'
            }
            post {
                always {
                    // Publicar resultados dos testes
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                // Comandos para implantação
                sh 'echo "Fazendo a implantação..."'
            }
        }
    }
}
