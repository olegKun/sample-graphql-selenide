pipeline {
    agent any
    environment {
        test_variable = 'testVar'
    }
    parameters {
        string(name: 'STATEMENT', defaultValue: 'hello; ls /', description: 'What should I say?')
    }
    tools {
        maven 'Maven 3.8.5'
        jdk 'Java 8'
    }
    stages {

        stage("Example"){
            steps{
                sh("echo ${STATEMENT}")
            }
        }
        stage("Check maven is installed") {
            steps {
                cleanWs()
                sh "mvn --version"
            }
        }
        stage("Clone repo") {
            steps {
                git url: 'https://github.com/joshelser/simple-rest-war.git'
                sh "git branch -a"
                sh "git checkout master"
            }
        }

        stage("Build war") {
            steps {
                sh "mvn package"
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }

//        stage("Deploy war in Docker") {
//            steps {
//                echo "building an image"
//                echo "The server is up and running"
//            }
//        }

        stage('Run tests') {
            steps {
                echo "running tests"
//            sh 'bash ./gradlew test --tests "com.oleh.graphql.GraphqlSampleTest.testGraphQl"'
            }
        }
    }
//      stage('Pull browser') {
//         steps {
//            echo "pulling the browser"
//         }
//      }

//      stage('Reports') {
//         steps {
//          echo "allure reports"
//
//         allure([
//          includeProperties: false,
//          jdk: '',
//          properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s']],
//          reportBuildPolicy: 'ALWAYS',
//          results: [[path: 'build/allure-results'], [path: 'other_target/allure-results']]
//          ])
//          }
//      }
}