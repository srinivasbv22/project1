node {

	def mvnHome
	def server =Artifactory.server 'azure_art'
	try{
		stage('Preparation') {
			git 'https://github.com/srinivasbv22/project1.git'

			mvnHome = tool 'Maven'
		}
		
		stage('SonarQube analysis') {
			sh '''mvn sonar:sonar \
				  -Dsonar.projectKey=srinivasbv22_project1 \
				  -Dsonar.organization=srinivasbv22-github \
				  -Dsonar.host.url=https://sonarcloud.io \
				  -Dsonar.login=c564dd4a829fc905797d4b17f9873b357bc20518'''
		}

		stage('Build') {
			sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
		}
		

		stage('Artifactory upload') {
			def uploadSpec = """
				{ "files": [ { "pattern": "/var/lib/jenkins/workspace/project1/target/*.war", "target": "project1" } ] }""" 
			server.upload(uploadSpec) 

		}

		stage('downloading artifact') 
		{ 
			def downloadSpec="""{ "files":[ { "pattern":"project1/guns.war", "target":"/var/lib/jenkins/workspace/project1/" } ] }""" 
					server.download(downloadSpec)
		}    
		
		stage ('Final deploy'){
			sh 'scp  /var/lib/jenkins/workspace/project1/guns.war ubuntu@srinivas.eastus.cloudapp.azure.com:/opt/tomcat/apache-tomcat-8.5.14/webapps/'
		}
		currentBuild.result == 'SUCCESS'
	}
	
	catch(err)
	{
		currentBuild.result = 'FAILURE'
		mail bcc: '', body:"${err}", cc: '', from: '', replyTo: '', subject: 'Job failed', to: 'srinivasbv22@gmail.com'
	}
	
}
