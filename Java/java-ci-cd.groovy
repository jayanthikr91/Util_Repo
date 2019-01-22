def execute(){

stage('checkout')

{
git prop.GIT_URL
pom=readMavenPom  file: prop.POM_FILE
}

stage ('build'){

echo "${prop.BUILD_CMD}"
sh prop.BUILD_CMD

}

stage('code analysis'){
    echo "${prop.SONAR_CMD}+${prop.SONAR_URL}"
    sh prop.SONAR_CMD +prop.SONAR_URL
}
stage('artifact upload'){
    commonutility.uploadArtifact();
 }  
 stage('deployment'){
     	 
       sh prop.DOCKER_CMD
       sh prop.DOCKER_CP_CMD+prop.SRC_DEPLOY_LOC+" "+prop.DOCKER_CONT_LOC
    }    
   
}
return this
