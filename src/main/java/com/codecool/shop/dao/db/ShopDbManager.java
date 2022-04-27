package com.codecool.shop.dao.db;

import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import org.postgresql.ds.PGSimpleDataSource;

public class ShopDbManager {
    private ProductCategoryDaoJdbc productCategoryDaoJdbc;
    private ProductDaoJdbc productDaoJdbc;
    private SupplierDaoJdbc supplierDaoJdbc;

    public void run() {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }
    }

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        this.productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
        this.productDaoJdbc = new ProductDaoJdbc(dataSource);
        this.supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties ap = new ApplicationProperties();

        dataSource.setDatabaseName(ap.readProperty("database"));
        dataSource.setUser(ap.readProperty("user"));
        dataSource.setPassword(ap.readProperty("password"));

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public List<Product> getAllProducts(){
        return productDaoJdbc.getAll();
    }

}
