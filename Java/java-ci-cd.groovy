def execute(){

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
    stage('deployment'){
     	 
       sh prop.DOCKER_CMD
       sh prop.DOCKER_CP_CMD+prop.SRC_DEPLOY_LOC+prop.DOCKER_CONT_LOC
       echo prop.DOCKER_RUN+prop.DOCKER_CONT_NAME+prop.DOCKER_PORT_CMD+prop.DOCKER_CONT_PORT+prop.DOCKER_PORT
    }    
   
}
return this
