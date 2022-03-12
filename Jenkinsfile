pipeline {
  agent any
  stages {
     stage("Build image") {
        steps {
    	echo "building an image"
       }
    }
     stage('Pull browser') {
        steps {
           echo "pulling the browser"
        }
     }
     stage('Run tests') {
        steps {
           echo "running tests"
           sh 'bash ./gradlew test --tests "com.oleh.graphql.GraphqlSampleTest.testGraphQl"'
         }
     }
     stage('Reports') {
        steps {
         echo "allure reports"

        allure([
         includeProperties: false,
         jdk: '',
         properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s']],
         reportBuildPolicy: 'ALWAYS',
         results: [[path: 'build/allure-results'], [path: 'other_target/allure-results']]
         ])
         }
     }
  }
} 