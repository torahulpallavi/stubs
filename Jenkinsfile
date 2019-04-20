pipeline {

  //  agent { label 'stubs-slave' }

  agent any

     parameters {
            booleanParam(defaultValue: false, description: 'Stop the stubs', name: 'StopStub')
            string(defaultValue: '9096', description: 'port number to run the stub server', name: 'portNum1')
            string(defaultValue: '9098', description: 'port number to run the stub server', name: 'portNum2')
            string(defaultValue: '9100', description: 'port number to run the stub server', name: 'portNum3')

        }

    options { buildDiscarder(logRotator(numToKeepStr: '2')) }

    stages {

        stage(' Install Stubs - 1   ') {

                       steps {
                            script{
                                if(!params.StopStub)
                                    {
                                               echo "test started node 1"
                                               sh "chmod 777 startup.sh"
                                               sh "./startup.sh $params.portNum1"
                                    }
                                }
                            }

        }


         stage(' Install Stubs -2 ') {
               steps {
                    script{
                        if(!params.StopStub)
                            {
                                       echo "test started node 2"
                                       sh "chmod 777 startup.sh"
                                       sh "./startup.sh $params.portNum2"
                            }
                        }
                    }
                }


          stage(' Install Stubs -3 ') {
                               steps {
                                    script{
                                        if(!params.StopStub)
                                            {
                                                       echo "test started node 2"
                                                       sh "chmod 777 startup.sh"
                                                       sh "./startup.sh $params.portNum3"
                                            }
                                        }
                                    }
                                }




        stage(' Configure Nginx') {
                  steps {
                          echo "configure NGINX"

                        }
                  }




         stage('  Stop Stub ') {
              steps {
                      script{
                              if(params.StopStub)
                              {
                                sh "chmod 777 stop.sh"
                                sh "./stop.sh"
                              }
                    }      }
               }
    }
}
