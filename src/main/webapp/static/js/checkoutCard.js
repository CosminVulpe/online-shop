const checkBox = document.getElementById("fill_in");
checkBox.addEventListener("click", validation);

// const submit = document.getElementById("submit");
// submit.addEventListener("click", redirect);

const firstName = document.getElementById("first_name");
const lastName = document.getElementById("last_name");
const email = document.getElementById('email');
const telephoneNumber = document.getElementById('phone_number');
const countries = document.querySelectorAll("#countries_bill");
const countryBill = countries[0];
const countryShip = countries[1];
const cityBill = document.getElementById("city_bill");
const cityShip = document.getElementById("city_ship");
const addressBill = document.getElementById("address_bill");
const addressShip = document.getElementById("address_ship");
const zipcodeBill = document.getElementById("zipcode_bill");
const zipcodeShip = document.getElementById("zipcode_ship");
const button = document.querySelector('#submit');
button.addEventListener('click', saveDateLocal);


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


function saveDateLocal() {

    localStorage.setItem("firstName", firstName.value);
    localStorage.setItem("lastName", lastName.value);
    localStorage.setItem("email", email.value);
    localStorage.setItem("telephoneNumber", telephoneNumber.value);

    localStorage.setItem("cityShip", cityShip.value);
    localStorage.setItem("addressShip", addressShip.value);
    localStorage.setItem("zipCodeShip", zipcodeShip.value);
    localStorage.setItem("countryShip", countryShip.value);
}
