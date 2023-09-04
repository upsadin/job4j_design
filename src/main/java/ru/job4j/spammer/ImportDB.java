package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(u -> {
                String[] split = u.split(";");
                if (split.length < 2) {
                    throw new IllegalArgumentException("Some argument missed");
                }
                if (split[0].length() == 0 || split[1].length() == 0) {
                    throw new IllegalArgumentException("Some argument is empty");
                }
                users.add(new User(split[0], split[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("username"),
                cfg.getProperty("password")
        )) {
            try (PreparedStatement ct = cnt.prepareStatement("CREATE TABLE users(NAME text, EMAIL text)")) {
                ct.execute();
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users(NAME, EMAIL) VALUES(?, ?)"
                )) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}