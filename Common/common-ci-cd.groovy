def sendSuccessMail(prop){

   emailext body: '${DEFAULT_CONTENT}', subject: '${DEFAULT_SUBJECT}', to: prop.RECEPIENT_MAIL_ID
 
}
def sendFailureMail(prop){
    emailext body: '${DEFAULT_CONTENT}', subject: '${JOB_NAME} - BUILD # ${BUILD_NUMBER} -  FAILURE', to: prop.RECEPIENT_MAIL_ID
}
def clearWorkspace(){
deleteDir()
}

def uploadArtifact(prop){
script{   
def server = Artifactory.server prop.ARTIFACT_ID
def uploadSpec = """{
 	
"files":[
{
"pattern":"${prop.ARTIFACT_PATTERN}",
"target":"${prop.ARTIFACT_REPO}/${pom.artifactId}/${pom.version}.${BUILD_NUMBER}/"
}
]
}"""
server.upload(uploadSpec)
 }
}

