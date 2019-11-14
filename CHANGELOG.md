# Kinetica JDBC Connector

## Version 7.0

### Version 7.0.9 - 2019-10-30

#### Added
-   Support for CREATE PROCEDURE
-   Can now specify a URL as the first parameter on the JDBC Connection String
--  Can specify http or https, but these are optional
--  Starting with "//" is optional (when "http" or "https" are not specified)
--  May contain a path after the port, including slashes 
   
#### Fixed
-   Escape characters specified in INSERT FROM FILE parameters
-   Inserting into a result table
-   Insert was under reporting number of added rows, when the insert took multiple batches
-   Fix for inserting NULL as blank string in CSV for DECIMAL, DATE, DATETIME, TIME, IPV4 and ULONG columns
-   Minor KISQL command-line parameter parsing fixes

### Version 7.0.8 - 2019-09-17

#### Added
-   Added support for getPrimaryKeys() and getIndexInfo
-   Enhanced support for INSERT from FILE:
--  Support globs to read from multiple files
--  Support default separator from file extension (.psv = '|', tsv = '\t', else ',')
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
-   Add support for SETUSER and related commands.

### Version 7.0.6 - 2019-07-??

#### Added
-   Support for specifying columns for both table and CSV file in INSERT command.
--   E.g., INSERT INTO t(col1, col2) SELECT col3, col4 FROM FILE."/tmp/table.csv"; 
-   Added DELIMITER support to CSV INSERT command.
--   E.g., INSERT INTO t SELECT * FROM FILE."/tmp/table.csv" DELIMITER='\t'; 

### Version 7.0.5 - 2019-06-26

#### Added
-   CombinePrepareAndExecute setting. 

### Version 7.0.4 - 2019-05-29

#### Added
-   Support for PrimaryURL in KiSQL and JDBC Driver. 

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
-   Added LIMIT parameter to support limiting the maximum number of rows returned by a query.
-   Added KISQL --connectionstring parameter.

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
