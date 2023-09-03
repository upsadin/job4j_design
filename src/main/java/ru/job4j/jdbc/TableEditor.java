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

    public TableEditor(Connection connection) {
        this.connection = connection;
        initConnection();
    }

    public void initConnection() {
        connection = null;
    }

    private static Connection getConnection() throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver_class"));
        String url = config.getProperty("url");
        String login = config.getProperty("login");
        String password = config.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(String.format(
                    "CREATE TABLE IF NOT EXISTS %s", tableName
            ));
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(String.format(
                    "DROP TABLE %s", tableName
            ));
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type
            ));
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(String.format(
                    "ALTER TABLE %s DROP COLUMN %s", tableName, columnName
            ));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
            ));
        }
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
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                buffer.add(String.format(
                        "%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)
                ));
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
        try (Connection connection = getConnection()) {
            TableEditor tableEditor = new TableEditor(connection);
            tableEditor.createTable("testTable");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.addColumn("testTable", "Amount", "int");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.renameColumn("testTable", "Amount", "Left");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.dropColumn("testTable", "Left");
            System.out.println(tableEditor.getTableScheme("testTable"));
            tableEditor.dropTable("testTable");
            System.out.println(tableEditor.getTableScheme("testTable"));
        }
    }
}
