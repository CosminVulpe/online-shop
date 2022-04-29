package com.codecool.shop.dao.db;

import com.codecool.shop.dao.UserInfoDao;
import com.codecool.shop.model.UserInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserInfoDaoJdbc implements UserInfoDao {
    private final DataSource dataSource;
    private final UserInfoDaoJdbc userInfoDaoJdbc;

    public UserInfoDaoJdbc(DataSource dataSource, UserInfoDaoJdbc userInfoDaoJdbc) {
        this.dataSource = dataSource;
        this.userInfoDaoJdbc = userInfoDaoJdbc;
    }

    @Override
    public void add(UserInfo userInfo) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO user_info (id, " +
                    "first_name ," +
                    "last_name, " +
                    "email, " +
                    "phone_number, " +
                    "country, " +
                    "city, " +
                    "address, " +
                    "zipcode ," +
                    "country_ship ," +
                    "city_ship ," +
                    "address_ship ," +
                    "zipcode_ship," +
                    "user_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,getMaxId()+1);
            statement.setString(2, userInfo.getFirstName());
            statement.setString(3, userInfo.getLastName());
            statement.setString(4, userInfo.getEmail());
            statement.setString(5, userInfo.getPhoneNumber());
            statement.setString(6, userInfo.getCountry());
            statement.setString(7, userInfo.getCity());
            statement.setString(8, userInfo.getAddress());
            statement.setInt(9, userInfo.getZipcode());
            statement.setString(10, userInfo.getCountryShip());
            statement.setString(11, userInfo.getCityShip());
            statement.setString(12, userInfo.getAddressShip());
            statement.setInt(13, userInfo.getZipcodeShip());
            statement.setInt(14, userInfo.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(UserInfo userInfo) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE user_info " +
                    "set first_name = ?," +
                    "last_name = ?," +
                    "email = ?," +
                    "phone_number = ?," +
                    "country = ?," +
                    "city = ?," +
                    "address = ?," +
                    "zipcode = ?," +
                    "country_ship = ?," +
                    "city_ship = ?," +
                    "address_ship = ?," +
                    "zipcode_ship = ?" +
                    "WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userInfo.getFirstName());
            statement.setString(2, userInfo.getLastName());
            statement.setString(3, userInfo.getEmail());
            statement.setString(4, userInfo.getPhoneNumber());
            statement.setString(5, userInfo.getCountry());
            statement.setString(6, userInfo.getCity());
            statement.setString(7, userInfo.getAddress());
            statement.setInt(8, userInfo.getZipcode());
            statement.setString(9, userInfo.getCountryShip());
            statement.setString(10, userInfo.getCityShip());
            statement.setString(11, userInfo.getAddressShip());
            statement.setInt(12, userInfo.getZipcodeShip());
            statement.setInt(13, userInfo.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserInfo find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM user_info WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            UserInfo userInfo = new UserInfo("ceva",
                    resultSet.getInt("zipcode"),
                    resultSet.getInt("zipcode_ship"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("country"),
                    resultSet.getString("city"),
                    resultSet.getString("address"),
                    resultSet.getString("country_ship"),
                    resultSet.getString("city_ship"),
                    resultSet.getString("address_ship"));
            userInfo.setId(resultSet.getInt("id"));
            return userInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    private int getMaxId() {
        int id;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(*) as max_id FROM user_info";
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
