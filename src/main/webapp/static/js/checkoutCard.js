const checkBox = document.getElementById("fill_in");
checkBox.addEventListener("click", validation);

// const submit = document.getElementById("submit");
// submit.addEventListener("click", redirect);

const firstName = document.getElementById("first_name")
const lastName = document.getElementById("last_name")
const countries = document.querySelectorAll("#countries_bill");
const countryBill = countries[0];
const countryShip = countries[1];
const cityBill = document.getElementById("city_bill");
const cityShip = document.getElementById("city_ship");
const addressBill = document.getElementById("address_bill");
const addressShip = document.getElementById("address_ship");
const zipcodeBill = document.getElementById("zipcode_bill");
const zipcodeShip = document.getElementById("zipcode_ship");

function validation() {

    if (checkBox.checked) {
        cityShip.value = cityBill.value;
        addressShip.value = addressBill.value;
        zipcodeShip.value = zipcodeBill.value;
        countryShip.value = countryBill.value;
    } else {
        cityShip.value = "";
        addressShip.value = "";
        zipcodeShip.value = "";
        countryShip.value = "";
    }
}


// function redirect(){
    // if ( )
    // window.location.href = "/";
// }