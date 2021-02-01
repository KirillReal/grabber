package ru.job4j.grabber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;


public class PsqlStore implements Store,AutoCloseable {
    private Connection cn;

    private static final Logger LOGGER = LoggerFactory.getLogger(PsqlStore.class);

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (SQLException | ClassNotFoundException e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    public PsqlStore(Connection cn) {
        this.cn = cn;
    }

    @Override
    public boolean close(Post post) {
        boolean result = false;
        try(PreparedStatement statement = cn.prepareStatement("insert into post(name,text,link,created) values(?,?,?,?)")) {
            statement.setString(1, post.getAuthor());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, new Timestamp(post.getDate().getTime()));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post findById(String id) {
        return null;
    }
}
