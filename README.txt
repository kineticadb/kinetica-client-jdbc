The 7.0 JDBC jar will not work with GPUdb Servers lower than 7.0.  Or vice versa -- the previous JDBC jars will not connect to a GPUdb Server version 7.0 or later.

JDBCDriver -->

The JDBCDriver folder contians the JDBC jar, and kisql shell script.  If you are using a JDBC Client, or writing your own JDBC Client, then you only need the jar file.  The class to use in this jar is: com.kinetica.jdbc.Driver

The kisql utility is a SQL environment with similarities to isql.  It can also process SQL commands in a file, or from the command-line.

The JDBC Connection string can be as simple as: jdbc:kinetica://gpudb-machine-name-or-ip:9191

If the GPUdb Administrator has changed the default port from 9191, then you should replace that in the connection string.  If your GPUdb connection requires HTTPS, then you will need to use the URL parameter of the connection string.  Some of the common connection string options are:
* URL - GPUdb connection URL (e.g., “http://127.0.0.1:9191”)
* UID - User ID.  Only needed if security is turned on and a User Name must be specified.
* PWD - Password.  Only needed if security is turned on and a password is needed.
* Timeout - Minutes to wait for a GPUdb Server response.  0 = No Timeout.  Default: 0.
* SslCACertPath - Directory where SSL Certificates are stored.  Only needed for SSL connections.
* SslAllowHostMismatch - How strict to be when checking SSL Host Name in Certificates.
* RowsPerFetch - Number of rows to be requested at a time from GPUdb.  Default=10000.

E.g., jdbc:kinetica://gpudb-machine-name-or-ip:9191;UID=MyName;PWD=MyPassword

JDBC Client Sample Program -->

JDBCSample is a sample jdbc client project. You can compile by executing

"mvn clean package"

and then you execute with the following command. Change as needed for a linux based invocation. 

java -cp .;"<your_path>\kinetica-jdbc-7.0.0.0-jar-with-dependencies.jar" com.gisfederal.gpudbquery.JDBCSample -DriverClass com.kinetica.jdbc.Driver -URL "jdbc:kinetica://gpudb-machine-name-or-ip:9191" -PWD pwd -UID uid

Example invocation - C:\1GAIA\gpudb-client-jdbc\JDBCSample\target>java -cp .;gpudb-java-sample-0.1.0-jar-with-dependencies.jar;..\..\JDBCDriver\kinetica-jdbc-7.0.0.0-jar-with-dependencies.jar com.gisfederal.gpudbquery.JDBCSample -DriverClass com.kinetica.jdbc.Driver -URL "jdbc:kinetica://gpudb-machine-name-or-ip:9191" -PWD pwd -UID uid

If the project is imported into eclipse, the program arguments are (as an exmaple)

-DriverClass com.kinetica.jdbc.Drivercom.kinetica.jdbc.Driver -URL jdbc:kinetica://gpudb-machine-name-or-ip:9191 -PWD pwd -UID uid

where http://gpudb-machine-name-or-ip:9191 is where GPUdb is running. 

Please note the kinetica-jdbc-7.0.0.0-jar-with-dependencies.jar.jar has to be in the classpath and this jar is available as part of this repo under the directory connectors/jdbc.
