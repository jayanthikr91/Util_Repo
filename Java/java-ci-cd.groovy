def execute(prop){
try{
def pom    


stage('checkout')

{
git prop.GIT_URL
pom=readMavenPom  file: prop.POM_FILE
}

stage ('build'){

echo "${prop.MAVEN_CMD}"
sh prop.MAVEN_CMD

}

stage('code analysis'){
    echo "${prop.MAVEN_SONAR_CMD}+${prop.SONAR_URL}"
    sh prop.MAVEN_SONAR_CMD +prop.SONAR_URL
}
       
stage ('Final deploy'){

sh prop.SECURE_COPY_CMD+prop.SRC_DEPLOY_LOC+" "+prop.DEST_DEPLOY_LOC
}
    
stage('success email'){
    emailext body: '${DEFAULT_CONTENT}', subject: '${DEFAULT_SUBJECT}', to: prop.RECEPIENT_MAIL_ID
    
  }
 }
  catch(Exception e){
  stage('failure email'){
    emailext body: '${DEFAULT_CONTENT}', subject: '${JOB_NAME} - BUILD # ${BUILD_NUMBER} -  FAILURE', to: prop.RECEPIENT_MAIL_ID
  }
  }


}
return this
