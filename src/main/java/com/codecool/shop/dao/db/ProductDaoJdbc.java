package com.codecool.shop.dao.db;

import com.codecool.shop.dao.ProductDao;
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

public class ProductDaoJdbc implements ProductDao {
    private final DataSource dataSource;
    private final SupplierDaoJdbc supplierDaoJdbc;
    private final ProductCategoryDaoJdbc productCategoryDaoJdbc;

    public ProductDaoJdbc(DataSource dataSource, SupplierDaoJdbc supplierDaoJdbc, ProductCategoryDaoJdbc productCategoryDaoJdbc) {
        this.dataSource = dataSource;
        this.supplierDaoJdbc = supplierDaoJdbc;
        this.productCategoryDaoJdbc = productCategoryDaoJdbc;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (id, name, description, price, currency,supplier_id, category_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, getMaxId() + 1);
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getDefaultPrice());
            statement.setString(5, product.getDefaultCurrency().toString());
            statement.setInt(6, product.getSupplier().getId());
            statement.setInt(7, product.getProductCategory().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Product product = new Product(resultSet.getString("name"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getString("currency"),
                    resultSet.getString("description"),
                    productCategoryDaoJdbc.find(resultSet.getInt("category_id")),
                    supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
            product.setId(resultSet.getInt("id"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        List<Product> productsList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from product;";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoJdbc.find(resultSet.getInt("category_id")),
                        supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                product.setId(resultSet.getInt("id"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productsList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from product WHERE supplier_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoJdbc.find(resultSet.getInt("category_id")),
                        supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                product.setId(resultSet.getInt("id"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> productsList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from product WHERE category_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoJdbc.find(resultSet.getInt("category_id")),
                        supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                product.setId(resultSet.getInt("id"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }

    private int getMaxId() {
        int id;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(id) as max_id FROM product";
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
