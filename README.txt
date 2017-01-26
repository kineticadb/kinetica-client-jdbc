How does a JDBC program communicate with GPUdb -->

A JDBC java program needs a running GPUdb ODBC server. JDBC program talks to the ODBC server which in turn contacts GPUdb for the database 
operations. Note that the address of the GPUdb to connect to is part of the URL that the client program sends over to the ODBC server (as opposed
to a configuration parameter in the ODBC server itself)

JDBC Client Sample Program -->

JDBCSample is a sample jdbc client project. You can compile by executing

"mvn client package"

and then you execute with the following command. Change as needed for a linux based invocation. 

java -cp .;"<your_path>\SimbaJDBCClient4.jar" com.gisfederal.gpudbquery.JDBCSample -DriverClass com.simba.client.core.jdbc4.JDBC4Driver -URL "jdbc:simba://yourodbcserverIP:9292;URL=http://yourgpudbIP:9191;ParentSet=MASTER" -PWD pwd -UID uid

Example invocation - C:\1GAIA\gpudb-client-jdbc\JDBCSample\target>java -cp .;gpudb-java-sample-0.1.0-jar-with-dependencies.jar;..\..\JDBCDriver\SimbaJDBCClient4.jar com.gisfederal.gpudbquery.JDBCSample -DriverClass com.simba.client.core.jdbc4.JDBC4Driver -URL "jdbc:simba://172.30.70.6:9292;URL=http://172.30.70.6:9191;ParentSet=MASTER" -PWD pwd -UID uid

If the project is imported into eclipse, the program arguments are (as an exmaple)

-DriverClass com.simba.client.core.jdbc4.JDBC4Driver -URL jdbc:simba://172.30.70.6:9292;URL=http://172.30.70.6:9191;ParentSet=MASTER -PWD pwd -UID uid

where jdbc:simba://172.30.70.6:9292 is the ODBC server address and http://172.30.70.6:9191 is where GPUdb is running. 

Please note the SimbaJDBCClient4.jar has to be in the classpath and this jar is available as part of this repo under the directory JDBCDriver.
