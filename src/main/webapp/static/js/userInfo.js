const firstName = document.getElementById("first_name");
const lastName = document.getElementById("last_name");
const email = document.getElementById('email');
const phone = document.getElementById('phone_number');
const countries = document.querySelectorAll("#countries_bill");
const countryBill = countries[0];
const countryShip = countries[1];
const cityBill = document.getElementById("city_bill");
const cityShip = document.getElementById("city_ship");
const addressBill = document.getElementById("address_bill");
const addressShip = document.getElementById("address_ship");
const zipcodeBill = document.getElementById("zipcode_bill");
const zipcodeShip = document.getElementById("zipcode_ship");

const resetInfo = document.getElementById("reset-info");
resetInfo.addEventListener("click", () =>{
    firstName.value = "";
    lastName.value = "";
    email.value = "";
    phone.value = "";
});

const resetBill = document.getElementById("reset-bill");
resetBill.addEventListener("click", () =>{
    countryBill.value = "";
    cityBill.value = "";
    addressBill.value = "";
    zipcodeBill.value = "";

});

const resetShip = document.getElementById("reset-ship");
resetShip.addEventListener("click", () =>{
    countryShip.value = "";
    cityShip.value = "";
    addressShip.value = "";
    zipcodeShip.value = "";

});

const copyBill = document.getElementById("clone-bill");
copyBill.addEventListener("click", () =>{
    countryShip.value = countryBill.value ;
    cityShip.value = cityBill.value;
    addressShip.value = addressBill.value;
    zipcodeShip.value = zipcodeBill.value;
});

const copyShip = document.getElementById("clone-ship");
copyShip.addEventListener("click", () =>{
    countryBill.value = countryShip.value;
    cityBill.value = cityShip.value;
    addressBill.value = addressShip.value;
    zipcodeBill.value = zipcodeShip.value;
});