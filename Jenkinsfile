node {

	def mvnHome
	def server =Artifactory.server 'azure_art'
	try{
		//git fetch
		stage('Preparation') {
			git 'https://github.com/srinivasbv22/project1.git'

			mvnHome = tool 'Maven'
		}
		//sonarqube analysis
		stage('Sonarcloud') {
			sh '''mvn sonar:sonar \
				  -Dsonar.projectKey=srinivasbv22_project1 \
				  -Dsonar.organization=srinivasbv22-github \
				  -Dsonar.host.url=https://sonarcloud.io \
				  -Dsonar.login=c564dd4a829fc905797d4b17f9873b357bc20518'''
		}
		//sonarqube
		 stage('SonarQube analysis') {
		       withSonarQubeEnv('sonarqube1') {
				   sh '''mvn sonar:sonar \
			    -Dsonar.host.url=http://40.87.47.38 \
			    -Dsonar.login=8c19834e275a20c1aa5fa760bf5952d2e9d0949f '''
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
			sh 'scp  /var/lib/jenkins/workspace/project1/guns.war ubuntu@srinivas.eastus.cloudapp.azure.com:/opt/tomcat/apache-tomcat-8.5.14/webapps/'
		}
		
		currentBuild.result == 'SUCCESS'
	}
	
	catch(err)
	{
		currentBuild.result = 'FAILURE'
		//Mail on failure
		mail bcc: '', body:"${err}", cc: '', from: '', replyTo: '', subject: 'Job failed', to: 'srinivasbv22@gmail.com'
	}
	stage('JIRA') {
		withEnv(['JIRA_SITE=Jira1']) {
				//Current build is failure
		    if(currentBuild.result == 'FAILURE'){
					//Previous build is success
			if(currentBuild.previousBuild.result!='FAILURE'){
						//Create new issue
			    def testIssue = [fields: [ project: [key: 'PROJ'],
					 summary: 'Build Fail',
					 description: 'Build has failed for the project 1 application.',
					 issuetype: [name: 'Bug']]]

			    response = jiraNewIssue issue: testIssue

			    echo response.successful.toString()
			    echo response.data.toString()
			    env.PROJ_ISSUE=response.data.key
			    echo env.PROJ_ISSUE

						//Assigning issue
			    jiraAssignIssue idOrKey: env.PROJ_ISSUE, userName: 'anoopjainduplicate'

						//Storing issue in a file
			    sh 'echo $PROJ_ISSUE > /var/lib/jenkins/jiraissue'
			}

					//previous build is also failure
			else if(currentBuild.previousBuild.result=='FAILURE'){
			    def issue=readFile '/var/lib/jenkins/jiraissue'
			    issue=issue.trim()
			    echo issue

						//Adding comment
			    jiraAddComment idOrKey:issue, comment: 'Build is failed again'
			}
		    }
				//current build is success
				//previous build is failure
		    else if(currentBuild.previousBuild.result=='FAILURE'){
					//move transaction to done
			def transitionInput =
			[
			    transition: [
				id: '41'
			    ]
			]
			def issue=readFile '/var/lib/jenkins/jiraissue'
			    issue=issue.trim()
			jiraTransitionIssue idOrKey:issue, input: transitionInput

					//Adding comment
			jiraAddComment idOrKey:issue, comment: 'Build is successful' 

		    }
		}
	}
	
}
