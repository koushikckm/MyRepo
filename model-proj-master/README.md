##POC for storm framework

##Prerequisties for compiling and deploying on storm clusters
1. JDK 6 or above.

2. apache-maven-3.2.5

	• Download the zip from the link (https://maven.apache.org/download.cgi) and extract.
	• Once you have downloaded, unzip it and set the M2_HOME environment variable as unzipped location and add %M2_HOME%\bin to Path environment variable.
	• In command prompt give command 'mvn -version' to check if maven setup is proper.

		C:\>mvn -version
		Apache Maven 3.2.5 (12a6b3acb947671f09b81f49094c53f426d8cea1; 2014-12-14T22:59:23+05:30)
		Maven home: C:\apache-maven-3.2.5
		Java version: 1.8.0_31, vendor: Oracle Corporation
		Java home: C:\javaRepo\jdk1.8.0_31\jre
		Default locale: en_US, platform encoding: Cp1252
		OS name: "windows 8.1", version: "6.3", arch: "amd64", family: "dos"

3. MySql 5.6.24

	• Download the MySQL installer msi from (http://dev.mysql.com/downloads/mysql/) and install.

4. zookeeper-3.4.6

   Configuring in windows:
	• Download the zip from the link (http://zookeeper.apache.org/releases.html) and extract.
	• Once you have downloaded, unzip it and set the ZK_HOME environment variable.
	• Rename the configuration file, zoo_sample.cfg to zoo.cfg, at the $ZK_HOME/conf directory.

   Execution:
	• In command prompt $ZK_HOME/bin directory run zkServer.cmd.

		C:\zookeeper-3.4.6\bin>zkServer.cmd

5. apache-storm-0.9.4

   Configuring in windows:
	• Download the zip from the link (https://storm.apache.org/downloads.html) and extract.
	• Once you have downloaded, unzip it and set the  STORM_HOME environment variable.
	• Add %STORM_HOME%\bin to PATH.
	• Edit the storm.yaml configuration file at $STORM_HOME/conf with below details.

		storm.zookeeper.servers:
		    - "127.0.0.1"
		#     - "server2"
		#
		storm.zookeeper.port: 2181
		nimbus.host: "127.0.0.1"


   Execution:
	  Starting nimbus node

		• In command prompt $STORM_HOME/bin directory run 'storm nimbus' to start nimbus node.
			  C:\apache-storm-0.9.4\bin>storm nimbus
	  Starting drpc node
	  	
	  	• In command prompt $STORM_HOME/bin directory run 'storm drpc' to start drpc server.
			  C:\apache-storm-0.9.4\bin>storm drpc

	  Starting supervisor node

		• In command prompt $STORM_HOME/bin directory run 'storm supervisor' to start supervisor node.
			  C:\apache-storm-0.9.4\bin>storm supervisor

	  Starting ui

		• In command prompt $STORM_HOME/bin directory run 'storm ui' to launch UI daemon.
		  The UI provides a web interface for a Storm cluster and shows detailed stats about running topologies.
		• To check the same type url 'http://localhost:8080/' in browser.


6. Python 2.7.6

	• Download and install python (https://www.python.org/download/releases/2.7.6/).
	• Check if python is properly installed.
		C:\>python
		Python 2.7.9 (default, Dec 10 2014, 12:24:55) [MSC v.1500 32 bit (Intel)] on win
		32
		Type "help", "copyright", "credits" or "license" for more information.
		>>>

Before deploying the topology the database is to be prepopulated with data.
##Creating MySQL database
	• Open MySQL command line client and create a database.
	  mysql> create database database_name;

	• execute GenerateDatabaseData in jar to insert test entries to database.
		Path to target> java -cp mp-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.consensus.prototypes.mongoose.storm.GenerateDatabaseData


##Deploying the topology on cluster (local)

	• Build your Maven project by running the following command on the project home directory.
		C:\> mvn clean install
	• We can deploy the topology to the cluster using the following Storm client command
		C:\> storm jar storm-spring-jar-with-dependencies.jar com.consensus.prototypes.mongoose.storm.topology.MpToplogy MpToplogy
	• To deactivate topology run the below command.
		C:\> storm deactivate MpToplogy
	• To remove(kill) topology run the below command.
		C:\> storm kill MpTopology


##Input to storm
Input is passed to storm through DRPC. Execute a simple application "DrpcClient" under package "com.consensus.prototypes.mongoose.storm.client" after modifying the input
