package com.codecool.shop.dao.db;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
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

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> productsList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * from product;";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier_id"));
                ProductCategory productCategory = productCategoryDaoJdbc.find(resultSet.getInt("category_id"));
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier);
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
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
