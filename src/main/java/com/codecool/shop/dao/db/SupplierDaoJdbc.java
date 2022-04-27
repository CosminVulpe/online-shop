package com.codecool.shop.dao.db;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (id, name, description) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,getMaxId()+1);
            statement.setString(2,supplier.getName());
            statement.setString(3,supplier.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM supplier WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Supplier supplier = new Supplier(resultSet.getString("name"),
                    resultSet.getString("description"));
            supplier.setId(resultSet.getInt("id"));
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supplierList  = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from supplier;";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    private int getMaxId(){
        int id;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(id) as max_id FROM supplier";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if ( !resultSet.next()){
                return 0;
            }
                id = resultSet.getInt("max_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
