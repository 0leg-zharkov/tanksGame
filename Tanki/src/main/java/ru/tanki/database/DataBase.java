package ru.tanki.database;

import ru.tanki.config.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataBase {

    private static DataBase instance;

    private final DatabaseProperties properties = PropertiesFactory.getProperties();

    public DataBase() {
        init();
    }

    public synchronized static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public List<Map<String, String>> selectAll(String table, String... columnNames) {
        List<Map<String, String>> result = new ArrayList<>();

        String sql = """
                select %s
                from cursach.%s
                """;
        String names = String.join(", ", columnNames);
        String select = String.format(sql, names, table);

        try(Connection connection = connect();
            Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(select);
            while (set.next()) {
                Map<String, String> fields = new HashMap<>();
                for (String columnName : columnNames) {
                    fields.put(columnName, set.getString(columnName));
                }
                result.add(fields);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public void init() {
        createSchema();
        createTableGamer();
    }

    private void createSchema() {
        String sql = """
                create schema if not exists cursach;
                """;
        execute(sql);
    }

    public void execute(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createTableGamer() {

        String sql = """
                create table if not exists cursach.gamers (
                    nameId text not null PRIMARY KEY,
                    gameTime INTEGER not null
                )
                """;
        execute(sql);
    }

    //закоментил, пока не надо
//    public Map<String, String > selectByNameId(String nameId, String table, String... columnNames) {
//        Map<String, String> result = new HashMap<>();
//        String sql = """
//               select nameId, %s
//               from gamers.%s
//               where nameId = %s
//               """;
//        String names = Stream.of(columnNames).collect(Collectors.joining(", "));
//
//        String select = String.format(sql, names, table, nameId);
//        try (Connection connection = connect();
//             Statement statement = connection.createStatement()) {
//            ResultSet set = statement.executeQuery(select);
//
//            while(set.next()) {
//                for(String columnName : columnNames) {
//                    set.getObject(columnName);
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return result;
//    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getLogin(),
                properties.getPassword()
        );
    }
}

