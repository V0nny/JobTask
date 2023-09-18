pipeline {
  agent any
  tools {
    maven 'MVN'
  }
  stages {
    stage ("build") {
      steps {
        echo 'building stage'
        sh 'mvn compile'
      }
    }

    stage ("test") {
      steps {
        echo 'testing stage'
        sh 'mvn test'
      }
    }

    stage ("deploy") {
      steps {
        echo 'deploying stage'
      }
    }
    
  }
}
