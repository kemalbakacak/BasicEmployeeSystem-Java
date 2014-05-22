package edu.pau.hsqldbproject;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    private String connectionString;
    private Connection connection;
    private ResultSet rs;
    private String userName;
    private String password;

    public DBHandler() {
        connectionString = "";
    }

    public DBHandler(String connectionString) throws SQLException {
        this.connectionString = connectionString;
        this.connect();
    }

    public DBHandler(String connectionString, String userName, String password) throws SQLException {
        this.connectionString = connectionString;
        this.userName=userName;
        this.password=password;
        this.connect();
    }

    /**
     * Sets the connection string
     *
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Connects the database specified in connectionString
     *
     * @throws SQLException
     */
    public final void connect() throws SQLException {
        connection = DriverManager.getConnection(connectionString, userName, password);
    }

    /**
     * Closes database connection
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Executes a SELECT query
     *
     * @param query SQL SELECT statement to query
     * @return ResultSet object containing the result set of query
     * @throws SQLException
     */
    public ArrayList<Employee> executeQuery(String query) throws SQLException {
        ArrayList<Employee> employee = new ArrayList<Employee>();
        if (connection != null) {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                Employee e = new Employee(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"), 
                      rs.getString("Email"), rs.getString("Department"), rs.getString("Title") );
                employee.add(e);
            }
        }
        return employee;
    }

    /**
     * Execute a DELETE,INSERT or an UPDATE query
     *
     * @param sql SQL Statement to execute
     * @throws SQLException
     */
    public int executeNonQuery(String sql) throws SQLException {
        if (connection != null) {
            Statement stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        }
        return -1;
    } 
            
}
