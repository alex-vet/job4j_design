package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
            Class.forName(properties.getProperty("jdbc.connection.driver_class"));
            String url = properties.getProperty("jdbc.connection.url");
            String login = properties.getProperty("jdbc.connection.username");
            String password = properties.getProperty("jdbc.connection.password");
            connection = DriverManager.getConnection(url, login, password);
    }

    private void executeSQL(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s(%s, %s);",
                tableName,
                "id serial primary key",
                "name text"
        );
        executeSQL(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        executeSQL(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add column %s %s;",
                tableName,
                columnName,
                type
        );
        executeSQL(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName
        );
        executeSQL(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        executeSQL(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            if (in != null) {
                config.load(in);
            } else {
                throw new IOException("File jdbc.properties not found.");
            }
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("demo");
        System.out.println(getTableScheme(tableEditor.connection, "demo"));
        tableEditor.addColumn("demo", "demo_type", "integer");
        System.out.println(getTableScheme(tableEditor.connection, "demo"));
        tableEditor.renameColumn("demo", "demo_type", "demo");
        System.out.println(getTableScheme(tableEditor.connection, "demo"));
        tableEditor.dropColumn("demo", "demo");
        System.out.println(getTableScheme(tableEditor.connection, "demo"));
        tableEditor.dropTable("demo");
        System.out.println(getTableScheme(tableEditor.connection, "demo"));
        tableEditor.close();
    }
}
