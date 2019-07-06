node {

	def mvn
	def server =Artifactory.server 'artifactory'
	
		//git fetch
		stage('Preparation') {
			git 'https://github.com/srinivasbv22/project1.git'

			mvn = tool 'Maven'
		}
		
		
		//sonarqube
		 stage('Quality Analysis') {
			withSonarQubeEnv('sonarqube') {
			sh 'mvn clean package sonar:sonar'
			}
		    }

		
		
		//sonarqube quality gate
		 stage("Quality Gate"){
			  timeout(time: 1, unit: 'HOURS') {
			      def qg = waitForQualityGate()
			      if (qg.status != 'OK') {
				   error "Pipeline aborted due to quality gate failure: ${qg.status}"
				  mail bcc: '', body:"${qg.status}", cc: '', from: '', replyTo: '', subject: 'Quality fail', to: 'srinivasbv22@gmail.com'

			      }
			  }
		      }
		//maven build
		stage('Build') {
			sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
		}
		
		//Jfrog artifactory Upload
		stage('Artifactory upload') {
			def uploadSpec = """
				{ "files": [ { "pattern": "/var/lib/jenkins/workspace/project1/target/*.war", "target": "project1" } ] }""" 
			server.upload(uploadSpec) 

		}
		
		//Jfrog artifactory download
		stage('downloading artifact') 
		{ 
			def downloadSpec="""{ "files":[ { "pattern":"project1/guns.war", "target":"/var/lib/jenkins/workspace/project1/" } ] }""" 
					server.download(downloadSpec)
		}    
		//Deployment on tomcat
		stage ('Final deploy'){
			sh 'cp  /var/lib/jenkins/workspace/project1/guns.war /home/devopsinfra/'
		}
		
		currentBuild.result == 'SUCCESS'
	}
	
	
