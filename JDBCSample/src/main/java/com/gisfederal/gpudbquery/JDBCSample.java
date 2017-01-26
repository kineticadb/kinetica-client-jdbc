package com.gisfederal.gpudbquery;

//=================================================================================================
///  @file JDBCSample.java
///
///  A simple program that can be used to test JDBC connectivity. JDBCSample also serves as an 
///  example JDBC application.
///
///  Copyright (C) 2014 Simba Technologies Incorporated.
//=================================================================================================

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;


/*
 * Argument string while running from within Eclipse
 * -DriverClass com.simba.client.core.jdbc4.JDBC4Driver -URL jdbc:simba://172.30.20.11:9292;URL=http://172.30.20.29:9191;ParentSet=MASTER -PWD pwd -UID uid
 */

/**
 * This sample JDBC application provide utility to connect to a data source and
 * execute queries. It also can be used to query various of database
 * information, such as tables, columns and so on.
 * 
 * Usage:
 * 
 * JDBCSample [-DriverClass driver-class-name -URL connection-url [-PropertyName
 * property-value]...]
 * 
 * When no command line argument is provided, the Java Ultralight JDBC driver
 * provided by SimbaEngineSDK product will be used. When command line arguments
 * are used, besides the DriverClass and URL arguments, additional properties
 * used for connecting to the data source can be passed to this application.
 * 
 * Example:
 * 
 * java -cp /JavaUltraLightJDBC3.jar:./ JDBCSample \ -DriverClass
 * com.simba.ultralight.core.jdbc3.ULJDBC3Driver \ -URL jdbc:simba://localhost
 * -PWD pwd -UID uid
 * 
 * Please make sure the driver library used is in the classpath.
 */
public class JDBCSample {
	/*
	 * Static variable(s)
	 * ========================================================
	 * ==================
	 */

	/**
	 * The default driver class name.
	 */
	private static final String DEFAULT_DRIVER_CLASS = "com.simba.ultralight.core.jdbc3.ULJDBC3Driver";

	/**
	 * The default URL.
	 */
	private static final String DEFAULT_URL = "jdbc:simba://localhost;PWD=pwd;UID=uid;";

	/**
	 * The command line argument key for driver class name.
	 */
	private static final String DRIVER_CLASS_ARG_KEY = "DriverClass";

	/**
	 * The command line argument key for connection URL.
	 */
	private static final String URL_ARG_KEY = "URL";

	/**
	 * The maximum display size for any columns.
	 */
	private static final int MAX_COLUMN_WIDTH = 60;

	/**
	 * The minimum display size for any columns.
	 */
	private static final int MIN_COLUMN_WIDTH = 15;

	/*
	 * Static method(s)
	 * ==========================================================
	 * ==================
	 */

	/**
	 * Format a String to fit the display width of columns. If the passed in
	 * string is {@code null}, the result string is "NULL" restricted by the
	 * width arguments. If the String is too long to fit in the given width, the
	 * given string will be truncated, and "..." will be added at the end. the
	 * result string will be at most width character long.
	 * 
	 * @param str
	 *            The string to format.
	 * @param width
	 *            The width limit to format with. It must be greater than 4
	 *            characters.
	 * @return the formatted string.
	 */
	private static String formatCell(String str, int width) {
		assert (width > 4);
		if (str == null) {
			str = "NULL";
		}

		if (str.length() > width) {
			return str.substring(0, width - 3) + "...";
		}
		return str;
	}

	/**
	 * Connect to a data source.
	 * 
	 * @param driverClass
	 *            The driver class name to used for the connection.
	 * @param url
	 *            The url used for the connection.
	 * @param properties
	 *            The properties used for the connection.
	 * 
	 * @return the a connection achieved by this function.
	 * @throws ClassNotFoundException
	 *             if the driver class can not be loaded.
	 * @throws SQLException
	 *             if error happens while connecting to the data source.
	 */
	private static Connection getConnection(String driverClass, String url,
			Map<String, String> properties) throws ClassNotFoundException,
			SQLException {
		Class.forName(driverClass);
		Properties p = new Properties();
		for (Map.Entry<String, String> e : properties.entrySet()) {
			p.setProperty(e.getKey(), e.getValue());

		}
		return DriverManager.getConnection(url, p);
	}

	/**
	 * Parse the argument into key value pairs. If {@code args} has not
	 * elements, the default driver class and url will be loaded into the
	 * {@code out_argMap}.
	 * 
	 * @param args
	 *            The arguments to parse.
	 * @param out_argMap
	 *            The result of parsing the arguments.
	 * @return true if parsing arguments is succeeded, false otherwise.
	 */
	private static boolean parseArguments(String[] args,
			Map<String, String> out_argMap) {
		if (args.length == 0) {
			out_argMap.put(DRIVER_CLASS_ARG_KEY, DEFAULT_DRIVER_CLASS);
			out_argMap.put(URL_ARG_KEY, DEFAULT_URL);
			return true;
		}

		if (args.length % 2 != 0) {
			return false;
		}

		for (int i = 0; i < args.length; i += 2) {
			if (args[i].charAt(0) != '-' || args[i].length() == 1) {
				return false;
			}

			out_argMap.put(args[i].substring(1), args[i + 1]);
		}

		return true;
	}

	/**
	 * Print the help message.
	 */
	private static void printHelp() {
		System.out.println("Commands: ");
		System.out.println("\tTABLES:    Fetch the Tables schema.");
		System.out.println("\tCOLUMNS:   Fetch the Columns schema.");
		System.out.println("\tDATATYPES: Fetch the DataTypes schema.");
		System.out.println();
		System.out.println("\tEXIT:      Quits this application.");
		System.out.println("\tQUIT:      Quits this application.");
		System.out.println();
		System.out
				.println("Any other string will be used as a SQL query and be executed, displaying the resulting rows.");
		System.out.println();
	}

	/**
	 * Print a result set.
	 * 
	 * @param result
	 *            The result set to print.
	 * @throws SQLException
	 *             if error happens during retrieving the result.
	 */
	private static void printResultSet(ResultSet result) throws SQLException {
		ResultSetMetaData rsMeta = result.getMetaData();
		final int columnCount = rsMeta.getColumnCount();

		final int[] widths = new int[columnCount];
		final String hLine;

		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < columnCount; i++) {
				int width = Math.max(rsMeta.getColumnDisplaySize(i + 1), rsMeta
						.getColumnName(i + 1).length()) + 1;
				widths[i] = width = Math.min(MAX_COLUMN_WIDTH,
						Math.max(MIN_COLUMN_WIDTH, width));
				sb.append('+');
				for (int j = 0; j < width; j++) {
					sb.append('-');
				}
			}
			hLine = sb.append('+').toString();
		}

		// Print top of table.
		System.out.println(hLine);
		if (0 < columnCount) {
			StringBuilder line = new StringBuilder();
			for (int i = 0; i < columnCount; i++) {
				int w = widths[i];
				line.append(String.format("|%" + w + "." + w + "s",
						formatCell(rsMeta.getColumnName(i + 1), w)));
				
				//System.out.println("@@@@@@@@@@@ " + rsMeta.getColumnTypeName(i+1));
			}
			System.out.println(line.append("|"));
			System.out.println(hLine);
		}

		long numRows = 0;
		// Print the rows.
		while (result.next()) {
			numRows++;
			StringBuilder line = new StringBuilder();
			for (int i = 0; i < columnCount; i++) {
				int w = widths[i];
				
				
				//System.out.println("YYYYYYYYYYY");
				//System.out.println(" XXXXXXXXXXX " + result.getBigDecimal(i+1));
				
				
				line.append(String.format("|%" + w + "." + w + "s",
						formatCell(result.getString(i + 1), w)));
						
			}
			System.out.println(line.append("|"));
		}
		System.out.println(hLine);
		System.out.println(numRows + " rows in set.");
	}

	/**
	 * Print Usage message.
	 */
	private static void printUsuage() {
		System.out
				.println("Usage: JDBCSample [-DriverClass driver-class-name -URL connection-url [-PropertyName property-value]...]");
	}

	/**
	 * Retrieves a description of table columns available for given connection.
	 * 
	 * @param conn
	 *            The connection used to list the columns.
	 * @throws SQLException
	 *             if error happens while querying the data source.
	 */
	private static void showColumns(Connection conn) throws SQLException {
		assert (conn != null);

		DatabaseMetaData meta = conn.getMetaData();

		ResultSet result = null;
		try {
			result = meta.getColumns(null, null, null, null);
			printResultSet(result);
		} finally {
			if (result != null) {
				result.close();
			}
		}
	}

	/**
	 * Retrieves a description of all the data types supported by the data
	 * source.
	 * 
	 * @param conn
	 *            The connection used to list the data types.
	 * @throws SQLException
	 *             if error happens while querying the data source.
	 */
	private static void showDataTypes(Connection conn) throws SQLException {
		assert (conn != null);

		DatabaseMetaData meta = conn.getMetaData();

		ResultSet result = null;
		try {
			result = meta.getTypeInfo();
			printResultSet(result);
		} finally {
			if (result != null) {
				result.close();
			}
		}
	}

	/**
	 * Show the result of given query.
	 * 
	 * @param conn
	 *            The connection to use for querying the data source.
	 * @param sql
	 *            The SQL query to use for querying the data source.
	 * @throws SQLException
	 *             if error happens when executing the query.
	 */
	private static void showQueryResult(Connection conn, String sql)
			throws SQLException {
		System.out.println("Executing SQL query: " + sql);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			boolean isResultSet = stmt.execute(sql);
			boolean hasMoreResult = true;
			while (hasMoreResult) {
				if (isResultSet) {
					ResultSet rs = null;
					try {
						rs = stmt.getResultSet();
						printResultSet(rs);
					} finally {
						if (rs != null) {
							rs.close();
						}
					}
				} else {
					int rowCount = stmt.getUpdateCount();
					if (rowCount == -1) {
						hasMoreResult = false;
					} else {
						System.out.println(String.format(
								"Query OK, %d row affected.", rowCount));
					}
				}
				isResultSet = stmt.getMoreResults();
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	/**
	 * Retrieves a description of tables available for given connection.
	 * 
	 * @param conn
	 *            The connection used to list the tables.
	 * @throws SQLException
	 *             if error happens while querying the data source.
	 */
	private static void showTables(Connection conn) throws SQLException {
		assert (conn != null);

		DatabaseMetaData meta = conn.getMetaData();

		ResultSet result = null;
		try {
			result = meta.getTables(null, null, null, null);
			printResultSet(result);
		} finally {
			if (result != null) {
				result.close();
			}
		}
	}

	/*
	 * Main
	 * ======================================================================
	 * ==================
	 */
	public static void main(String[] args) {
		TreeMap<String, String> argMap = new TreeMap<String, String>(
				String.CASE_INSENSITIVE_ORDER);
		
		
		if (!parseArguments(args, argMap)) {
			System.out.println("Invalid Arguments.");
			printUsuage();
			System.exit(-1);
		}

		String driverClass = argMap.get(DRIVER_CLASS_ARG_KEY);
		argMap.remove(DRIVER_CLASS_ARG_KEY);
		String url = argMap.get(URL_ARG_KEY);
		argMap.remove(URL_ARG_KEY);

		if (driverClass == null || url == null) {
			System.out.println("Invalid Arguments.");
			printUsuage();
			System.exit(-1);
		}
		
		
		Connection conn = null;

		try {
			conn = getConnection(driverClass, url, argMap);
		} catch (ClassNotFoundException e) {
			System.err.println("Invalid driver class name: ");
		} catch (SQLException e) {
			System.err.println("Cannot connect: " + e.getLocalizedMessage());
		}

		if (conn == null) {
			System.exit(-1);
		}

		boolean quit = false;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Enter SQL Query or '?' for help: ");

			String command = in.nextLine().trim();//.toUpperCase();

			try {
				if (command.equals("?")) {
					printHelp();
				} else if (command.equals("EXIT") || command.equals("QUIT")
						|| command.equals("Q")) {
					quit = true;
				} else if (command.equals("TABLES")) {
					showTables(conn);
				} else if (command.equals("COLUMNS")) {
					showColumns(conn);
				} else if (command.equals("DATATYPES")) {
					showDataTypes(conn);
				} else {
					showQueryResult(conn, command);
				}
			} catch (SQLException e) {
				System.err.println("Error happens while executing command: "
						+ e.getLocalizedMessage());
				StackTraceElement[] stes = e.getStackTrace();
				for( StackTraceElement ste : stes ) {
					System.out.println(" STE is " + ste);
				}
			}

			System.out.println();
		} while (!quit);

		in.close();
		try {
			conn.close();
		} catch (SQLException e) {
			// Do nothing.
		}
	}
}
