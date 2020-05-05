pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        sh 'echo "Deploy"'
      }
    }

    stage('QA Testing') {
      parallel {
        stage('QA Testing') {
          steps {
            sh 'echo "QA Testing"'
          }
        }

        stage('API testing') {
          steps {
            sh 'echo "API test"'
          }
        }

      }
    }

    stage('Stage testing') {
      steps {
        sh 'echo "Stage testing"'
      }
    }

    stage('Prod testing') {
      parallel {
        stage('Prod testing') {
          steps {
            sh 'echo "Prod testing"'
          }
        }

        stage('Deployment') {
          steps {
            sh 'echo "Deploy"'
          }
        }

        stage('Env config') {
          steps {
            sh 'echo "Env config"'
          }
        }

      }
    }

    stage('Sanity test') {
      steps {
        sh 'echo "Sanity test"'
      }
    }

  }
}
