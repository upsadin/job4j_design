package ru.job4j.jdbc;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public void initConnection() throws Exception {
        Properties config = properties;
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver_class"));
        String url = config.getProperty("url");
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        this.connection = DriverManager.getConnection(url, username, password);
    }

    private void executeUpdate(String statement) throws SQLException {
        try (var st = connection.createStatement()) {
            st.executeUpdate(statement);
        }
    }

    public void createTable(String tableName) throws Exception {
        String statement = String.format(
                "CREATE TABLE IF NOT EXISTS %s ()", tableName
        );
        executeUpdate(statement);
    }

    public void dropTable(String tableName) throws Exception {
        String statement = String.format(
                    "DROP TABLE %s", tableName
            );
        executeUpdate(statement);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String statement = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type
        );
        executeUpdate(statement);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String statement = String.format(
                    "ALTER TABLE %s DROP COLUMN %s", tableName, columnName
        );
        executeUpdate(statement);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String statement = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
        );
        executeUpdate(statement);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.createTable("testTable");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.addColumn("testTable", "Name", "text");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.renameColumn("testTable", "Name", "FULL_NAME");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.dropColumn("testTable", "FULL_NAME");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.dropTable("testTable");
    }
}
