const checkBox = document.getElementById("fill_in");
checkBox.addEventListener("click", validation);

function validation(){
    const countries = document.querySelectorAll("#countries_bill");
    const country_bill = countries[0];
    const country_ship = countries[1];
    const city_bill = document.getElementById("city_bill");
    const city_ship = document.getElementById("city_ship");
    const address_bill = document.getElementById("address_bill");
    const address_ship = document.getElementById("address_ship");
    const zipcode_bill = document.getElementById("zipcode_bill");
    const zipcode_ship = document.getElementById("zipcode_ship");
    if ( checkBox.checked ){
        city_ship.value = city_bill.value;
        address_ship.value = address_bill.value;
        zipcode_ship.value = zipcode_bill.value;
        country_ship.value = country_bill.value;
    }else{
        city_ship.value = "";
        address_ship.value = "";
        zipcode_ship.value = "";
        country_ship.value = "";
    }
}