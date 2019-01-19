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
    sh prop.SECURE_COPY_CMD+prop.SRC_DEPLOY_LOC+" "+prop.DEST_DEPLOY_LOC
    }    
   
}
return this
