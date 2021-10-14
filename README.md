<img src="https://2wz2rk1b7g6s3mm3mk3dj0lh-wpengine.netdna-ssl.com/wp-content/uploads/2018/08/kinetica_logo.svg" alt="Kinetica Logo" width="300"/>

### The Database for Time and Space


# Kinetica JDBC Driver

-  [Overview](#overview)
-  [Configure](#configure)
-  [Connect](#connect)
-  [Examples](#examples)
-  [Documentation](#documentation)
-  [About Us](#about-us)
-  [Contact Us](#contact-us)
-  [License](#license)
 

## Overview

The Kinetica JDBC Driver supports connections from a wide array of JDBC-aware
applications to the Kinetica database.


Note that the version of the driver must match that of the database; e.g., the
7.1 version of the driver is interoperable with any Kinetica 7.1.X.Y database.


## Configure

Kinetica JDBC driver JAR:

    kinetica-jdbc-7.1.<X>.<Y>-jar-with-dependencies.jar        (Java 1.8+)
    kinetica-jdbc-java7-7.1.<X>.<Y>-jar-with-dependencies.jar  (Java 1.7)

Driver class:

    com.kinetica.jdbc.Driver


### Common Connection Parameters

For the full parameter set, see
[ODBC/JDBC Connection Guide](https://docs.kinetica.com/7.1/connectors/sql_guide/#configuration).

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
| `SslAllowHostMismatch` | Allow (`1`) or disallow (`0`) the Kinetica certificate host name and server host name to be different
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
- [Full Documentation](https://docs.kinetica.com/7.1/)
- [ODBC/JDBC Connection Guide](https://docs.kinetica.com/7.1/connectors/sql_guide/)


## About Us
Kinetica is an analytics database for fusing data across streams and data lakes
to unlock value from spatial and temporal data at scale and speed. Learn about
us [here](https://www.kinetica.com/).


## Contact Us
- Follow on Github: <a class="github-button" href="https://github.com/kineticadb" data-size="large" aria-label="Follow @kineticadb on GitHub">Follow @kineticadb</a>
- Email: [support@kinetica.com](mailto:support@kinetica.com)
- Slack: [Slack](https://www.kinetica.com/slack)
- Visit: [https://www.kinetica.com/contact/](https://www.kinetica.com/contact/)


## License

The software is licensed under the MIT license.
