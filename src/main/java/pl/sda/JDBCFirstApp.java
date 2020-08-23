package pl.sda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFirstApp {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/company?serverTimezone=UTC&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mysql";

    public static void main(String[] args) throws SQLException {

        // Connection, Statement, executeQuery, Exceptions
//        selectAll();
        // try-with-resources
        selectAllNice();
        // executeUpdate
        insertEmployee();
        updateEmployee();
        deleteEmployee();
        // SQL Injection
        queryForDepartment("HR");
        // prepareStatement
        preparedQuery();
        // transactions
        transactionalUpdate();
        System.out.println("\n--== Final table state ==--");
        selectAllNice();


    }

    private static void selectAll() throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            System.out.println("\n=====  selectAll  =====");
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");

            // 4. Process the result set
            while (myRs.next()) {
                String employee = myRs.getString("last_name")
                        + ", " + myRs.getString("first_name");
                System.out.println(employee);
            }
            System.out.println("\n=====  selectAll - OK  =====");
        } catch (Exception exc) {
//            exc.printStackTrace();
            System.out.println("Error");
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }

    private static void selectAllNice() throws SQLException {

        System.out.println("\n=====  selectAllNice  =====");
        // 1. Get a connection to database
        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            Statement myStmt = myConn.createStatement();

            // 3. Execute SQL query
            ResultSet myRs = myStmt.executeQuery("select * from employees");

            // 4. Process the result set
            display(myRs);
            System.out.println("\n=====  selectAllNice - OK  =====");
        } catch (Exception exc) {
//            exc.printStackTrace();
            System.out.println("Error");
        }
    }

    private static void insertEmployee() {
        try (Connection myCon = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            Statement myStatement = myCon.createStatement();
            System.out.println("Inserting a row to the table");

            final String insert_query = "insert into employees " +
                    "(last_name, first_name, email, department, salary) " +
                    "values " +
                    "('Szczepanski', 'Marek','marek@sda.pl', 'Software', 90000)";
            final int rows_affected = myStatement.executeUpdate(insert_query);

            System.out.println(rows_affected + " new rows inserted to the table");
        } catch (Exception throwable) {
            System.out.println("Error");
        }
    }

    private static void updateEmployee() {

    }

    private static void deleteEmployee() {

    }

    private static void queryForDepartment(String department) {

    }

    private static void preparedQuery() {

    }

    private static void transactionalUpdate() {

    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }

}
