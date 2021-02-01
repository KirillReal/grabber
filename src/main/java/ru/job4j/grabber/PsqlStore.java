package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cn.prepareStatement("insert into post(nameVac,description,link,created) values(?,?,?,?)")) {
            statement.setString(1, post.getNameVac());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, new Timestamp(post.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (Statement statement = cn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM post")) {
            while (resultSet.next()) {
                String nameVac = resultSet.getString(2);
                String descr = resultSet.getString(3);
                String link = resultSet.getString(4);
                Timestamp timestamp = resultSet.getTimestamp(5);
                Date date = new Date(timestamp.getTime());
                result.add(new Post(nameVac, descr, link, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Post findById(String id) {
        Post item = null;
        try (PreparedStatement statement = cn.prepareStatement("select * from post where id=?")) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = new Post(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
            }
            if (item == null) {
                throw new NoSuchElementException("Post with given ID not found");
            }
        } catch (NoSuchElementException | SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PsqlStore psqlStore = new PsqlStore(cfg);
        Post postOne = new Post("java",
                "требуются разработчики", "hh.ru#1", new java.util.Date());
        Post postSecond = new Post("java",
                "требуются тестеры", "hh.ru#2", new java.util.Date());
        Post postThird = new Post("C",
                "требуются embedded", "hh.ru#3", new java.util.Date());
        psqlStore.save(postOne);
        psqlStore.save(postSecond);
        psqlStore.save(postThird);
        List<Post> posts = psqlStore.getAll();
        System.out.println(posts.size());
        posts.forEach(System.out::println);
        System.out.println(psqlStore.findById("1"));
    }
}
