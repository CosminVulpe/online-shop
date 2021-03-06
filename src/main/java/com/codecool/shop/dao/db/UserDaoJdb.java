package com.codecool.shop.dao.db;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdb implements UserDao {
    private DataSource dataSource;

    public UserDaoJdb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO users (id, username, email, password) VALUES(?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, getMaxId() + 1);
            st.setString(2, user.getName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE users SET user_name=?, password=? WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                return null;
            User user = new User(null, rs.getString(1)
                    , rs.getString(2));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id :" + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<User> result = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"));
                user.setId(rs.getInt("id"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id :" + e);
        }
    }


    private int getMaxId() {
        int id;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(*) as max_id FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            id = resultSet.getInt("max_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
