# Kinetica JDBC/KiSQL

## Version 7.2

### Version 7.2.3.3 - 2025-11-18

#### Added
-   Complete support for 12-byte `DECIMAL` data type
-   Support for multiple sets of parameterized query parameters

#### Changed
-   Updated underlying Java API to 7.2.3.2.

#### Fixed
-   Preservation of HA failover order in user-given URLs
-   Check of HA queue draining status on fail-back


### Version 7.2.3.2 - 2025-10-07

#### Changed
-   Switched retry handler to retry on higher-level `SocketException` instead of
    lower-level `ConnectException` & `NoRouteToHostException`
-   Updated underlying Java API to 7.2.3.1.


### Version 7.2.3.1 - 2025-09-26

#### Added
-   Support for new 12-byte `DECIMAL` type


#### Fixed
-   Issue handling the response of a query using `KI_HINT_KEY_LOOKUP`.


### Version 7.2.3.0 - 2025-09-03

#### Changed
-   Improved efficiency of dropping temporary tables.
-   Improved data type reporting.
-   Updated underlying Java API to 7.2.3.0.

#### Fixed
-   Issue creating an external table from a remote query containing a WKT type.
-   Update count reported by `getLargeUpdateCount()`.


### Version 7.2.2.17 - 2025-11-12

#### Fixed
-   Preservation of HA failover order in user-given URLs

#### Changed
-   Updated underlying Java API to 7.2.2.16


### Version 7.2.2.16 - 2025-10-07

#### Changed
-   Switched retry handler to retry on higher-level `SocketException` instead of
    lower-level `ConnectException` & `NoRouteToHostException`
-   Updated underlying Java API to 7.2.2.15


### Version 7.2.2.15 - 2025-09-03

#### Fixed
-   Handling of multi-part KiFS upload that is too big for the target directory.

#### Changed
-   Updated underlying Java API to 7.2.2.14


### Version 7.2.2.14 - 2025-08-24

#### Changed
-   Improved forward-compatibility for new `DECIMAL` type
-   Upgraded Apache Commons Lang3 library to 3.18.0
-   Updated underlying Java API to 7.2.2.13


### Version 7.2.2.13 - 2025-08-18

#### Fixed
-   Issue with query iterating over a batch size that is greater
    than the configured `max_get_records_size` on the server


### Version 7.2.2.12 - 2025-06-08

#### Added
-   Added HA connection string options:
    - `FailbackPollInterval`:  Seconds to wait between fail-back service polls
      of the primary cluster
    - `FailoverOrder`:  Determines whether the cluster failed over to is
      selected randomly or in the sequence specified by the user.  Valid values
      are `RANDOM` and `SEQUENTIAL`.


### Version 7.2.2.11 - 2025-05-20

#### Added
-   Added support for the HA Replication hints when doing an INSERT:
    - `KI_HINT_REPL_NONE`
    - `KI_HINT_REPL_SYNC`
    - `KI_HINT_REPL_ASYNC`
    - `KI_HINT_REPL_SYNC_PARALLEL`
    - `KI_HINT_REPL_ASYNC_PARALLEL`
-   Added `Replication` connection string option.  Supported values are:
    - `NONE`
    - `SYNC`
    - `ASYNC`
    - `SYNC_PARALLEL`
    - `ASYNC_PARALLEL`

#### Changed
-   `NoSync` connection string option is now deprecated, but still supported.  Use new `Replication=NONE` option instead.
-   Simplified design of fail-back poller.

#### Fixed
-   Handling of connection & fail-back when server blocks connections while
    processing HA-queued requests.
-   Fail-back poller not initiating after first fail-over/fail-back.


### Version 7.2.2.10 - 2025-04-17

#### Added
-   Handled connection scenario where primary cluster is initially down.

#### Changed
-   Updated underlying Java API to 7.2.2.9


### Version 7.2.2.9 - 2025-03-27

#### Added
-   Retrying connections in case of HTTP 502 responses

#### Changed
-   Updated underlying Java API to 7.2.2.8


### Version 7.2.2.8 - 2025-02-24

#### Added
-   More error types supported in retrying connections

#### Changed
-   Updated underlying Java API to 7.2.2.7
-   Upgraded Logback library to 1.3.15


### Version 7.2.2.7 - 2025-02-04

#### Changed
-   Updated underlying Java API to 7.2.2.6
-   Switched read timeout on system status checks to the configured server
    connection timeout


### Version 7.2.2.6 - 2025-01-31

#### Added
-   Support for JDBC getLargeUpdateCount()
-   Parameters for modifying the initial connection per-server timeout and
    overall connection negotiation timeout in seconds

#### Changed
-   Updated underlying Java API to 7.2.2.5

#### Fixed
-   KiSQL now correctly reports counts of changed rows when greater than 2 billion


### Version 7.2.2.5 - 2024-12-17

#### Added
-   Support for key lookups returning a subset of a table's columns with fewer
    lookup restrictions when using `KI_HINT_KEY_LOOKUP` hint or `UseKeyLookup`
    JDBC option

#### Changed
-   Updated underlying Java API to 7.2.2.4
-   Inserting values with functions like `NOW()` is now handled by the JDBC
    driver via multi-head insert


### Version 7.2.2.4 - 2024-12-03

#### Fixed
-   Queries with query parameters only returning first page of results
-   Schema support for KiSQL command-invoked catalog queries

#### Removed
-   Support for time zone override


### Version 7.2.2.3 - 2024-10-29

#### Added
-   Failback to a primary cluster after failing over to a secondary cluster

#### Changed
-   Updated underlying Java API to 7.2.2.3
-   Upgraded Avro library to 1.11.4

#### Fixed
-   Error message for bad URLs with auto-discovery disabled
-   Potential resource leaks upon connection errors


### Version 7.2.2.2 - 2024-10-24

#### Changed
-   Updated underlying Java API to 7.2.2.2

#### Fixed
-   Issue with lookup up server version when auto-discovery is disabled


### Version 7.2.2.1 - 2024-10-22

#### Changed
-   Reduced endpoint call overhead for connections and `RecordRetriever`
-   Updated underlying Java API to 7.2.2.1


### Version 7.2.2.0 - 2024-10-15

#### Added
-   OAuth2 authentication support

#### Changed
-   Updated underlying Java API to 7.2.2.0
-   Modified POM for publishing to Maven Central Repository
-   Upgraded Jackson core library to 2.17.1
-   Downgraded Logback library to 1.3.14
-   Upgraded SLF4j library to 2.0.13

#### Fixed
-   Issue with `fullshaded` driver terminating with no linked Snappy library
-   Issue with default string column values overwriting blank user input
-   Issue with `CREATE PROCEDURE` permission commands not being parsed properly


### Version 7.2.1.0 - 2024-09-08

#### Changed
-   Updated underlying Java API to 7.2.1.0

#### Fixed
-   Snappy error for `fullshaded` JAR


### Version 7.2.0.7 - 2024-06-07

#### Changed
-   Better parsing of multi-line SQL terminating semicolon
-   Updated underlying Java API to 7.2.0.5

#### Fixed
-   Returned Time and Timestamp objects will now always output 3 milliseconds in their toString() methods
-   Out-of-memory error when downloading large files


### Version 7.2.0.6 - 2024-05-16

#### Added
-   Added --stopOnFirstError option for KiSQL

#### Fixed
-   Thread-safety with JDBC Date fields
-   getTime(column).toString() was not showing milliseconds


### Version 7.2.0.5 - 2024-04-16

#### Added
-   Support for DataSource interface

#### Fixed
-   TimeZoneOverride setting
-   Output now works with TimeZone's default


### Version 7.2.0.4 - 2024-04-04

#### Changed
-   Lowered default server connection timeout to 5 seconds
-   Made server connection timeout (user-specified or default) govern connection
    timeouts in all cases of initially connecting to a server
-   Updated underlying Java API to 7.2.0.4


### Version 7.2.0.3 - 2024-03-20

#### Added
-   Support for unsigned long type in arrays

#### Changed
-   Updated underlying Java API to 7.2.0.3

#### Fixed
-   Various timezone-related issues


### Version 7.2.0.2 - 2024-03-13

#### Changed
-   Added support for setArray() for query parameters
-   Increased connection timeout from ~1 to 20 seconds to account for
    connections over high-traffic and public networks
-   Upgraded Snappy library from 1.1.10.4 to 1.1.10.5

#### Fixed
-   Outputting floats in scientific notation with negative-two-digit exponents


### Version 7.2.0.1 - 2024-02-27

#### Changed
-   Upgraded Apache HTTPClient5 library from 5.3 to 5.3.1


### Version 7.2.0.0 - 2024-02-11

#### Changed
-   Updated underlying Java API to 7.2.0.0


## Version 7.1

### Version 7.1.10.6 - 2025-04-29

#### Added
-   Failover support for a cluster starting up and draining its HA queue


### Version 7.1.10.5 - 2024-09-08

#### Fixed
-   Snappy error for `fullshaded` JAR


### Version 7.1.10.4 - 2024-08-07

#### Changed
-   Publishing to Maven Central Repository


### Version 7.1.10.3 - 2024-08-02

#### Fixed
-   Dependency-reduced POM for `fullshaded` JAR overwriting POM for unshaded JAR


### Version 7.1.10.2 - 2024-07-15

#### Changed
-   Several security-related dependency updates
-   Updated underlying Java API to 7.1.10.2


### Version 7.1.10.1 - 2024-06-07

#### Changed
-   Updated underlying Java API to 7.1.10.1

#### Fixed
-   Returned Time and Timestamp objects will now always output 3 milliseconds in their toString() methods
-   Out-of-memory error when downloading large files


### Version 7.1.10.0 - 2024-05-16

#### Added
-   Added --stopOnFirstError option for KiSQL

#### Fixed
-   Thread-safety with JDBC Date fields
-   getTime(column).toString() was not showing milliseconds


### Version 7.1.9.22 - 2024-04-16

#### Fixed
-   TimeZoneOverride setting
-   Output now works with TimeZone's default


### Version 7.1.9.21 - 2024-04-04

#### Changed
-   Lowered default server connection timeout to 5 seconds
-   Made server connection timeout (user-specified or default) govern connection
    timeouts in all cases of initially connecting to a server
-   Updated underlying Java API to 7.1.9.18


### Version 7.1.9.20 - 2024-03-20

#### Changed
-   Updated underlying Java API to 7.1.9.17


### Version 7.1.9.19 - 2024-03-13

#### Changed
-   Increased connection timeout from ~1 to 20 seconds to account for
    connections over high-traffic and public networks
-   Upgraded Snappy library from 1.1.10.4 to 1.1.10.5
-   Updated underlying Java API to 7.1.9.16

#### Fixed
-   Outputting floats in scientific notation with negative-two-digit exponents


### Version 7.1.9.18 - 2024-02-29

#### Changed
-   Upgraded Apache HTTPClient5 library from 5.3 to 5.3.1
-   Updated underlying Java API to 7.1.9.15


### Version 7.1.9.17 - 2024-01-31

#### Fixed
-   Fixed getting user's default schema to support external users


### Version 7.1.9.16 - 2023-12-21

#### Changed
-   Updated underlying Java API to 7.1.9.14


### Version 7.1.9.15 - 2023-12-07

#### Changed
-   Updated underlying Java API to 7.1.9.13


### Version 7.1.9.14 - 2023-12-03

#### Added
-   Support for Connection.getSchema()

#### Changed
-   Updated underlying Java API to 7.1.9.12

#### Fixed
-   Inserting TIMESTAMP column data using string formatted times with fractions of seconds


### Version 7.1.9.13 - 2023-10-24

#### Added
-   Added "disableSnappy" option for JDBC and KiSQL


### Version 7.1.9.12 - 2023-10-17

#### Changed
-   Updated underlying Java API to 7.1.9.10


### Version 7.1.9.11 - 2023-10-10

#### Changed
-   Updated underlying Java API to 7.1.9.9


### Version 7.1.9.10 - 2023-10-05

#### Changed
-   Updated underlying Java API to 7.1.9.8


### Version 7.1.9.9 - 2023-09-17

#### Changed
-   Support more options in WITH OPTIONS on INSERT statements
-   Updated underlying Java API to 7.1.9.7

#### Fixed
-   Bug in DOWNLOAD FILE


### Version 7.1.9.8 - 2023-08-12

#### Changed
-   Updated underlying Java API to 7.1.9.6
-   Better handling when /show/tables returns a blank attribute_index


### Version 7.1.9.7 - 2023-07-31

#### Changed
-   Updated underlying Java API to 7.1.9.5


### Version 7.1.9.6 - 2023-07-26

#### Fixed
-   SET USER and EXECUTE AS USER allow more characters in user's name.


### Version 7.1.9.5 - 2023-06-08

#### Added
-   UUID support

#### Changed
-   Updated underlying Java API

#### Fixed
-   Issue with ingesting Byte column data
-   KiSQL password prompting (on some terminals)


### Version 7.1.9.4 - 2023-05-09

#### Fixed
-   Removed setting of impersonate header when impersonate user is blank


### Version 7.1.9.3 - 2023-04-30

#### Changed
-   Updated underlying Java API


### Version 7.1.9.2 - 2023-04-26

#### Changed
-   Updated underlying Java API


### Version 7.1.9.1 - 2023-04-23

#### Added
-   Added the following connection string options:
    - `ErrorMode` (values: `Permissive`, `Skip`, `Abort`)
    - `FileReadCommentChar`
    - `FileReadDelimiter`
    - `FileReadEscapeChar`
    - `FileReadHasHeader`
    - `FileReadInitialClear`
    - `FileReadLimit`
    - `FileReadNullString`
    - `FileReadQuoteChar`
    - `FileReadSkip`
    - `ImpersonateUser`
    - `UseApproxCountDistinct`

#### Changed
-   Updated underlying Java API


### Version 7.1.9.0 - 2023-03-19

#### Added
-   Added `BypassSslCertCheck` connection string option
-   Added `bypassSslCertCheck` KiSQL option

#### Changed
-   Updated underlying Java API

#### Fixed
-   Support for NULL values in query parameters (for non-multi-head-insert statements)
-   Truncate Strings not handling Unicode characters properly


### Version 7.1.8.7 - 2023-02-17

#### Changed
-   Updated underlying Java API


### Version 7.1.8.6 - 2023-01-27

#### Added
-   Support for `KI_HINT_IGNORE_EXISTING_PK`
-   Added `IgnoreExistingPk` connection string options to make all statements act as
    if `KI_HINT_IGNORE_EXISTING_PK` had been specified
-   Streamlined KiSQL output


### Version 7.1.8.5 - 2023-01-08

#### Added
-   Support for $ style query parameters (e.g., "$1", "$2", etc.)
-   Support for BOOLEAN columns and data

#### Fixed
-   Support for BigDecimal query parameters


### Version 7.1.8.4 - 2022-11-30

#### Changed
-   Updated underlying Java API
-   Reporting of Kinetica `DECIMAL` type as SQL `DECIMAL(18,4)`


### Version 7.1.8.3 - 2022-11-21

#### Added
-   New `DIRECTORY` option for `UPLOAD` and `DOWNLOAD` commands
-   Support for `NoSync` connection string option to override HA Sync Mode

#### Changed
-   `FILE` and `DIRECTORY` are now optional on `UPLOAD` and `DOWNLOAD` commands


### Version 7.1.8.2 - 2022-11-02

#### Added
-   Support for inequalities on indexed columns with key lookup
    (i.e., `KI_HINT_KEY_LOOKUP`)
-   Added the following connection string options (to make all statements act as
    if the associated hint had been specified):
    -  `UseKeyLookup` (for `KI_HINT_KEY_LOOKUP`)
    -  `TruncateStrings` (for `KI_HINT_TRUNCATE_STRINGS`)
    -  `UpdateOnExistingPk` (for `KI_HINT_UPDATE_ON_EXISTING_PK`)

#### Fixed
-   Throw error on Prepare, if table or specified columns don't exist
-   Inserting duplicate PK's in the same batch with `UPDATE_ON_EXISTING_PK` is
    now a warning and not an error


### Version 7.1.8.1 - 2022-10-26

#### Added
-   Support for `UID` and `PWD` connection string options
-   New `CombineResults` connection string option to return a single affected
    rows count when executing multiple commands that all return an affected rows
    count
-   New `FakeTransactions` connection string option to not return an error and
    ignore any transaction calls

#### Fixed
-   Do not attempt to use key lookup when not all shard keys are specified
    (when SK=PK)
-   Query Parameters now retain their types
-   Connection String settings can include special characters using URL-style
    encoding (e.g., `%3b` for semicolon)


### Version 7.1.8.0 - 2022-10-17

#### Added
-   Support for fast record retrieval with `KI_HINT_KEY_LOOKUP`
-   New `DisableMultiheadInsert` setting to route all inserts to the head node
-   Relocation of shaded dependencies
-   Improved reporting of `INSERT` errors
-   BLOB/CLOB support


### Version 7.1.7.7 - 2022-10-11

#### Changed
-   Updated underlying Java API, allowing for better perfomance in high-load
    environments


### Version 7.1.7.6 - 2022-10-07

#### Fixed
-   Issue with thread safety in date/time parsing


### Version 7.1.7.5 - 2022-10-03

#### Changed
-   Updated underlying Java API


### Version 7.1.7.4 - 2022-09-19

#### Changed
-   Targeted Java 8 runtime


### Version 7.1.7.3 - 2022-09-08

#### Changed
-   Default to allowing self-signed certs
-   Materialized views are no longer reported as schemas


### Version 7.1.7.2 - 2022-08-12

#### Added
-   Updated underlying Java API


### Version 7.1.7.1 - 2022-07-25

#### Changed
-   Better caching and reuse of `BulkInserter` object


### Version 7.1.7.0 - 2022-06-15

#### Added
-   KiSQL interactive mode now supports line editing, up arrow to see previous
    commands, etc.
-   Support multiple files for `DOWNLOAD FILE`

#### Changed
-   Improved insert performance
-   Improved error handling


### Version 7.1.6.1 - 2022-02-22

#### Changed
-   Swapped out Log4j dependence for SLF4J


### Version 7.1.6.0 - 2022-01-27

#### Added
-   KiSQL shows abbreviated prompt (i.e., `>`) when continuing a SQL Command, in
    interactive mode
-   Add optional `WITH OPTIONS` for `INSERT` statements to match `LOAD INTO`
    syntax
-   KiSQL commands:
    -   `\columns` (`\u`)
    -   `\indices` (`\i`)
    -   `\meta-test`
    -   `\primarykeys` (`\k`)
    -   `\procedures` (`\p`)
    -   `\schemas` (`\s`)
    -   `\t` (as a shortcut for `\tables`)

#### Changed
-   Changed `DOWNLOAD` syntax to:

        DOWNLOAD FILES <kifs-files>
        INTO <local-path>
        [WITH OPTIONS (<comma_seperated_key_value_list>)]

-   Retrun better errors and warnings from `INSERT`
-   Better handling of `\N` when it is the NULL string and `\` is the escape
    character, when reading CSV files
-   Reduce output for CSV errors in files with extremely long lines

#### Fixed
-   Filtering of Metadata is now case insensitive
-   Better handling of errors reading header line in CSV
-   Better handling of mixed-case filenames


### Version 7.1.5.0 - 2021-10-13

#### Added
-   `UPLOAD` and `DOWNLOAD` commands
-   Single-file executable versions of KiSQL, which can be run anywhere


### Version 7.1.3.0 - 2021-03-09

#### Added
-   KiSQL output of very large and very small DOUBLE and FLOAT values now uses
    scientific notation


### Version 7.1.2.1 - 2021-01-29

#### Changed
-   Java API dependency version to be 7.1.2.2 and above to take
    advantage of recent security related fixes.


### Version 7.1.0.2 - 2020-09-25

#### Added
-   Support for `DisableAutoDiscovery` and `DisableFailover` connection string
    options
-   Support for `--disableAutoDiscovery` and `--disableFailover` KiSQL
    command-line parameter


### Version 7.1.0.0 - 2020-02-03

#### Added
-   Support for `SET SCHEMA` command -- only valid when connected to a 7.1
    Kinetica instance
-   Support for `--schema` KiSQL command-line parameter
-   Support for `schema` parameter on JDBC Connection string
-   Support for `schema` parameter to ODBC


## Version 7.0

### Version 7.0.20.8 - 2023-12-06

#### Changed
-   Updated underlying API version


### Version 7.0.20.7 - 2022-11-30

#### Changed
-   Updated underlying API version


### Version 7.0.20.6 - 2022-10-04

#### Changed
-   Updated underlying API version


### Version 7.0.20.5 - 2022-07-28

#### Added
-   Improved insert performance


### Version 7.0.20.4 - 2022-07-25

#### Fixed
-   Issue with thread over-accumulation when inserting data


### Version 7.0.20.3 - 2022-06-08

#### Changed
-   Updated dependent library versions


### Version 7.0.20.2 - 2022-04-01

#### Changed
-   Better reporting of errors when inserting with prepared statements.


### Version 7.0.20.1 - 2021-01-29

#### Changed
-   Java API dependency version to be 7.0.20.3 and above to take
    advantage of recent security related fixes.


### Version 7.0.20.0 - 2020-11-16

#### Added
-   Added Procedure metadata

#### Fixed
-   Server-side inserts do not waste time parsing on the client


### Version 7.0.19.8 - 2020-10-01

#### Fixed
-   `SET USER` support for external users (with leading "@")


### Version 7.0.18 - 2020-06-05

#### Added
-   Added `META-INF/services/java.sql.Driver`


### Version 7.0.16.2 - 2020-06-02

#### Changed
-   `supportsBatchUpdates()` metadata returns true
-   Trust Store password supports empty environment variable for blank password.


### Version 7.0.16.0 - 2020-05-04

#### Added
-   Added support for multi-line SQL Procedures
-   Added `RowsPerInsertion` connection string parameter
-   Added `\metadata` and `\version` commands to KiSQL

#### Changed
-   Removed support for `password` and `truststorepwd` command-line parameters (and synonyms)
-   Use `KI_PWD` environment variable, and user is prompted if `KI_PWD` is not set
-   Changed `CSVParser` to more closely match ODBC features
-   Updated reported metadata from JDBC Driver


### Version 7.0.15.0 - 2020-05-02

#### Changed
-   Updated Java API version dependency to 7.0.15.1.


### Version 7.0.14.4 - 2020-04-01

#### Fixed
-   Fixed `SslAllowHostMismatch` by now using Java API to do this


### Version 7.0.14.2 - 2020-03-24

#### Changed
-   Added support for `TimeZoneOverride` option to JDBC Connection string
-   Added `--TimeZoneOverride` option to KiSQL to pass this to JDBC
-   Added `--listalltimezones` to KiSQL to output the valid timezones


### Version 7.0.14.0 - 2020-03-18

#### Changed
-   JDBC now returns times in UTC/GMT

#### Fixed
-   KiSQL to use the timezone parameter correctly


### Version 7.0.12.1 - 2020-03-05

#### Changed
-   Updated the Java API version dependency to 7.0.12.1


### Version 7.0.12 - 2019-12-19

#### Added
-   Having a Materialized View under construction will not cause problems when
    getting metadata about other tables

#### Fixed
-   KiSQL now returns a linux status code indicating an error, if running SQL
    from a command-line or file and there is an error

### Version 7.0.9 - 2019-10-31

#### Added
-   Support for `CREATE PROCEDURE`
-   Can now specify a URL as the first parameter on the JDBC Connection String
--  Can specify `http` or `https`, but these are optional
--  Starting with `//` is optional (when `http` or `https` are not specified)
--  May contain a path after the port, including slashes
-   Version info now includes API version and KiSQL version
-   Documented the `--version` option in the KiSQL `--help` text

#### Fixed
-   Escape characters specified in `INSERT FROM FILE` parameters
-   Inserting into a result table
-   Insert was under reporting number of added rows, when the insert took multiple batches
-   Fix for inserting NULL as blank string in CSV for DECIMAL, DATE, DATETIME, TIME, IPV4 and ULONG columns
-   Minor KISQL command-line parameter parsing fixes

### Version 7.0.8 - 2019-09-17

#### Added
-   Added support for `getPrimaryKeys()` and `getIndexInfo`
-   Enhanced support for INSERT from FILE:
--  Support globs to read from multiple files
--  Support default separator from file extension (`.psv` = `|`, `tsv` = `\t`, else `,`)
--  Support for comments in file
--  Support for specifying the string to use for NULL
--  Option to clear table before inserting
--  Option to skip the first N rows
--  Option to limit number rows to insert
--  Support for specifying the quote character
--  Support for specifying the escape character
--  Support error handling modes: Skip, Permissive and Abort
--  Syntax: INSERT INTO employee SELECT * FROM FILE.”<file-name>” {[DELIMITER=’<char>’] [COMMENT=’<string>’] [SKIP=<n>] [LIMIT=<n>] [INITIAL_CLEAR] [QUOTE=’<char>’] [BATCH_SIZE=<n>] [HEADER={Yes|No}] [ESCAPE=’<char>’] [NULL=’<string>’] [ON_ERROR={Permissive|Skip|Abort}]}*


### Version 7.0.7 - 2019-08-13

#### Added
-   Add support for `SETUSER` and related commands.

### Version 7.0.6 - 2019-07-??

#### Added
-   Support for specifying columns for both table and CSV file in INSERT command.
--   E.g., INSERT INTO t(col1, col2) SELECT col3, col4 FROM FILE."/tmp/table.csv";
-   Added DELIMITER support to CSV INSERT command.
--   E.g., INSERT INTO t SELECT * FROM FILE."/tmp/table.csv" DELIMITER='\t';

### Version 7.0.5 - 2019-06-26

#### Added
-   `CombinePrepareAndExecute` setting.

### Version 7.0.4 - 2019-05-29

#### Added
-   Support for `PrimaryURL` in KiSQL and JDBC Driver.

#### Changed
-   Always show warnings.

### Version 7.0.3.? - 2019-05-07

#### Changed
-   Updated connection error message (and others) to include more info.
-   Changed the dependent Java API to be 7.0.3
    to pick up latest changes around high availability
    failover support.

### Version 7.0.3.0 - 2019-05-02

#### Added
-   Added `LIMIT` parameter to support limiting the maximum number of rows returned by a query.
-   Added KISQL `--connectionstring` parameter.

#### Fixed
-   Support for truststore parameter.
-   INSERT and Display of BYTE columns with NULL.

#### Changed
-   JDBC now returns an error if unable to connect to GPUdb initially.
-   KISQL now supports Unix-like parameters with double hyphens.
-   KISQL now supports boolean parameters without values (default: true)
-   KISQL now formats output with CR/LF better.
-   Updated logging

### Version 7.0.2.0 - 2019-04-15

#### Added
-   Support for clearing additional tables related to the paging table.
-   Support for Multi-head INSERT.
-   Support for INSERT from CSV files.
-   Support for Java 7.
-   Support for Java 10.

### Version 7.0.0.0 - 2019-01-31
-   Version release
