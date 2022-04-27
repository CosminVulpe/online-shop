package com.codecool.shop.dao.db;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO productcategory (id, name, departament, description) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,getMaxId()+1);
            statement.setString(2,category.getName());
            statement.setString(3,category.getDepartment());
            statement.setString(4,category.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM productcategory WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            ProductCategory productCategory = new ProductCategory(resultSet.getString("name"),
                    resultSet.getString("departament"),
                    resultSet.getString("description"));
            productCategory.setId(resultSet.getInt("id"));
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM productcategory WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoryList  = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from productcategory;";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("departament"),
                        resultSet.getString("description"));
                productCategory.setId(resultSet.getInt("id"));
                productCategoryList.add(productCategory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productCategoryList;
    }

    private int getMaxId(){
        int id;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(id) as max_id FROM productcategory";
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
