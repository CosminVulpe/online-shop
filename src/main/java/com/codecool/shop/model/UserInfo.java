package com.codecool.shop.model;

public class UserInfo extends BaseModel{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private int zipcode;
    private String countryShip;
    private String cityShip;
    private String addressShip;
    private int zipcodeShip;
    private int userId;

    public UserInfo(String name,int zipcode, int zipcodeShip, int userId, String... args) {
        super(name);
        this.zipcode = zipcode;
        this.zipcodeShip = zipcodeShip;
        this.userId = userId;
        this.firstName = args[0];
        this.lastName = args[1];
        this.email = args[2];
        this.phoneNumber = args[3];
        this.country = args[4];
        this.city = args[5];
        this.address = args[6];
        this.countryShip = args[7];
        this.cityShip = args[8];
        this.addressShip = args[9];
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCountryShip() {
        return countryShip;
    }

    public String getCityShip() {
        return cityShip;
    }

    public String getAddressShip() {
        return addressShip;
    }

    public int getZipcodeShip() {
        return zipcodeShip;
    }

    public int getUserId() {
        return userId;
    }
}
