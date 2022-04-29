package com.codecool.shop.dao.db;

import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.ds.PGSimpleDataSource;

public class ShopDbManager {
    private ProductCategoryDaoJdbc productCategoryDaoJdbc;
    private ProductDaoJdbc productDaoJdbc;
    private SupplierDaoJdbc supplierDaoJdbc;
    private UserDaoJdb userDaoJdbc;
    private UserInfoDaoJdbc userInfoDaoJdbc;

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
        this.supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
        this.productDaoJdbc = new ProductDaoJdbc(dataSource, supplierDaoJdbc, productCategoryDaoJdbc);
        this.userDaoJdbc = new UserDaoJdb(dataSource);
        this.userInfoDaoJdbc = new UserInfoDaoJdbc(dataSource);
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

    public void insertSupplier(Supplier supplier) {
        supplierDaoJdbc.add(supplier);
    }

    public void removeSupplier(int id) {
        supplierDaoJdbc.remove(id);
    }

    public List<Supplier> getSuppliers() {
        return supplierDaoJdbc.getAll();
    }

    public void insertProductCategory(ProductCategory productCategory) {
        productCategoryDaoJdbc.add(productCategory);
    }

    public void removeCategory(int id) {
        productCategoryDaoJdbc.remove(id);
    }

    public List<ProductCategory> getCategories() {
        return productCategoryDaoJdbc.getAll();
    }

    public List<Product> getAllProducts() {
        return productDaoJdbc.getAll();
    }

    public void removeProduct(int id) {
        productDaoJdbc.remove(id);
    }

    public void insertProduct(Product product) {
        productDaoJdbc.add(product);
    }

    public List<Product> getProductBySupplier(Supplier supplier) {
        return productDaoJdbc.getBy(supplier);
    }

    public List<Product> getProductByCategory(ProductCategory productCategory) {
        return productDaoJdbc.getBy(productCategory);
    }

    public List<User> getAllUsers() {
        return userDaoJdbc.getAll();
    }

    public void addUser(User user){
        userDaoJdbc.add(user);
    }

    public void addUserInfo(UserInfo userInfo){

        if (userInfoDaoJdbc.find(userInfo.getUserId()) != null ){
            userInfoDaoJdbc.update(userInfo);
        }else{
            userInfoDaoJdbc.add(userInfo);
        }
    }

    public UserInfo getUserInfo(int id){
        return  userInfoDaoJdbc.find(id);
    }

    public void updateUserInfo(UserInfo userInfo){
        userInfoDaoJdbc.update(userInfo);
    }
}
