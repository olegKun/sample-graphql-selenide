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
           sh 'gradle test --tests "com.oleh.graphql.GraphqlSampleTest.testGraphQl"'
         }
     }
     stage('Reports') {
        steps {
         echo "allure reports"
         }
     }
  }
} 