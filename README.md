<h3 align="center" style="margin:0px">
	<img width="200" src="https://kinetica-web-assets.s3.us-east-1.amazonaws.com/assets/kinetica_logo_gray.svg" alt="Kinetica Logo"/>
</h3>
<h5 align="center" style="margin:0px">
	<a href="https://www.kinetica.com/">Website</a>
	|
	<a href="https://docs.kinetica.com/7.2/">Docs</a>
	|
	<a href="https://docs.kinetica.com/7.2/connectors/sql_guide/">JDBC Docs</a>
	|
	<a href="https://join.slack.com/t/kinetica-community/shared_invite/zt-1bt9x3mvr-uMKrXlSDXfy3oU~sKi84qg">Community Slack</a>   
</h5>


# Kinetica JDBC Driver

-  [Overview](#overview)
-  [Configure](#configure)
-  [Connect](#connect)
-  [Examples](#examples)
-  [Documentation](#documentation)
-  [Support](#support)
-  [Contact Us](#contact-us)
 

## Overview

The Kinetica JDBC Driver supports connections from a wide array of JDBC-aware
applications to the Kinetica database.


Ideally, the version of the driver must match that of the database; e.g., the
7.2 version of the driver is interoperable with any Kinetica 7.2.X.Y database.
However, the 7.2 driver may be compatible with 7.1 database instances for most
use cases.


## Configure

Kinetica JDBC driver JAR:

    kinetica-jdbc-7.2.<X>.<Y>-jar-with-dependencies.jar

Driver class:

    com.kinetica.jdbc.Driver


### Which JAR to use

Use `jar-with-dependencies` unless you have a specific reason not to.

Every driver JAR bundles the Kinetica API, which is what makes the driver
self-contained; none of them contains it twice.  The `shaded` and `fullshaded`
variants additionally relocate their third-party libraries (Jackson, Apache
Commons, JLine) out of the way, for host applications that ship conflicting
versions of those libraries.  That rewriting reaches inside the bundled API as
well, so its classes keep their `com.gpudb` names while their method signatures
no longer match an ordinary build's.

If a shaded/relocated JDBC JAR is loaded into a process that already has the
Kinetica API, the two copies collide.

    java.lang.NoSuchMethodError: 'com.gpudb.apache.avro.Schema com.gpudb.Type.getSchema()'

A `com.gpudb.apache.` prefix in such an error is the tell: that package name
exists only in a shaded driver, so seeing it means a shaded JAR is loaded
somewhere it should not be.

### Datasources and datasinks

A `jdbc:kinetica:` datasource or datasink needs no driver JAR.  The database
supplies its own, and omitting `jdbc_driver_jar_path` is the supported setup.

Setting `jdbc_driver_jar_path` overrides that entirely, and the JAR named is
then loaded into a process that already has the Kinetica API, and a collision
ensues.  If a Kinetica-to-Kinetica export fails that way, check the datasource
for an explicit `jdbc_driver_jar_path` and remove it.


### Common Connection Parameters

For the full parameter set, including every authentication option, see the
[Connection Guide](docs/CONNECTION_GUIDE.md) in this repository, or the
[ODBC/JDBC Connection Guide](https://docs.kinetica.com/7.2/connectors/sql_guide/#odbc-jdbc-configuration).

| Parameter      | Description
| :---           | :---
| `URL`          | Kinetica connection URL; used in lieu of the default host/port configuration for SSL or failover connections
| `UID`          | Connection user ID
| `PWD`          | Connection user password
| `Timeout`      | Minutes to wait for a server response; no timeout is `0` (default)
| `RowsPerFetch` | Number of rows to be requested in a query at a time; default is 10,000 rows


### SSL Parameters

| Parameter              | Description
| :---                   | :---
| `BypassSslCertCheck`   | Allow (`1`) or disallow (`0`) the Kinetica certificate host name and server host name to be different
| `SslCACertPassword`    | Password to the certificate store specified by either `SslCACertPath` or `TrustedStorePath`
| `SslCACertPath`        | Path to client's trust store, used to validate the certificate from the Kinetica server
| `TrustedStorePath`     | Path to client's trust store, used to validate the certificate from the Kinetica server (override for `SslCACertPath` parameter)


## Connect

JDBC connection string:

    jdbc:kinetica://<ip or hostname>:<port>[;<key1>=<value1>...]

    jdbc:kinetica:URL=<connection URL>[;<key1>=<value1>...]


## Examples

To connect to a local instance on the default port with username/password:

    jdbc:kinetica://localhost:9191;UID=auser;PWD=apassword

To connect over SSL:

    jdbc:kinetica:URL=https://12.34.56.78:8082/gpudb-0

To connect using HA, pass a comma-separated list of cluster URLs for the `URL`
parameter, and the URL of the one to use first for the `PrimaryURL` parameter:

    jdbc:kinetica:URL=http://kineticaA:9191,http://kineticaB:9191,http://kineticaC:9191;PrimaryURL=http://kineticaB:9191


## Documentation
- [Connection Guide](docs/CONNECTION_GUIDE.md) - every connection-string parameter, authentication methods and examples
- [Full Documentation](https://docs.kinetica.com/7.2/)
- [ODBC/JDBC Connection Guide](https://docs.kinetica.com/7.2/connectors/sql_guide/)


## Support

For bugs, please submit an
[issue on Github](https://github.com/kineticadb/kinetica-client-jdbc/issues).

For support, you can post on
[stackoverflow](https://stackoverflow.com/questions/tagged/kinetica) under the
``kinetica`` tag or
[Slack](https://join.slack.com/t/kinetica-community/shared_invite/zt-1bt9x3mvr-uMKrXlSDXfy3oU~sKi84qg).


## Contact Us

* Ask a question on Slack:
  [Slack](https://join.slack.com/t/kinetica-community/shared_invite/zt-1bt9x3mvr-uMKrXlSDXfy3oU~sKi84qg)
* Follow on GitHub:
  [Follow @kineticadb](https://github.com/kineticadb) 
* Email us:  <support@kinetica.com>
* Visit:  <https://www.kinetica.com/contact/>
