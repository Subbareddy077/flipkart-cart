pipeline{ 
   agent any
   triggers{
       * * * * *   
   }
   environment{

   }
   stages{
    stage(build){
        steps{
            script{
            sh'mvn compile'
        }
        }
    }
    stage(test){
        steps{
            script{
            sh'mvn test'
            }
        }
    }
    stage(artifact){
        steps{
            script{
             sh'mvn package'
            }
        }
    }
    stage(docker image){
        steps{
            script{
             docker build -t welltech/buildnumber .
            }  
        }
    }
    stage(docker push){
        steps{
            script{
                docker push imagename
            }
        }
    }
    stage(deploy){
        steps{
            script{
                 sh'kubectl apply -f deployment.yaml'
            }
        }
    } 
   }
}