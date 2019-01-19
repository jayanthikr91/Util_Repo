def execute(){
try{
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
stage('artifact upload'){
    commonutility.uploadArtifact();
    }  



    
stage('success email'){
    commonutility.sendSuccessMail();
       
  }
 }
  catch(Exception e){
  stage('failure email'){
      commonutility.sendFailureMail();
    
  }
  }


}
return this
