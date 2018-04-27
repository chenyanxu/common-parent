// refer to https://github.com/siamaksade/cart-service/blob/jenkinsfiles/Jenkinsfile
node('maven') {
  stage('Build Common App') {
    git url: "https://github.com/chenyanxu/common-parent.git"
    sh "mvn install -DskipTests=true -s settings.xml"
  }
  stage('Deploy common-parent App') {
    sh "mvn deploy -DskipTests=true -s settings.xml"
  }
}
