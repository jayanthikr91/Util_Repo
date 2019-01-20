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
    install = false;
	try {
		input message: 'Install on new container?', ok: 'Install'
		Install = true
		} catch (err) {
	Install = false
	
	}
	
       if (Install){   
	 
	  sh "docker run -d --name tomcat3 -p 8009:8080 tomcat"
        }
    }    
   
}
return this
