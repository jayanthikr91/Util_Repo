def sendMail(prop){
stage('success email'){
    emailext body: '${DEFAULT_CONTENT}', subject: '${DEFAULT_SUBJECT}', to: prop.RECEPIENT_MAIL_ID
  }
}
