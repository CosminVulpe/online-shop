const name = document.querySelector('.font-weight-bold');
const shippingAddress = document.querySelector('#shipping-address');
const tbody = document.querySelector('#tbody');
const totals = document.querySelector('.totals');


const fillOrderDetails = () => {
    name.innerHTML = "Hello, " + localStorage.getItem("firstName") + " " + localStorage.getItem("lastName");
    shippingAddress.innerHTML = `<em> Zip Code: </em>` + localStorage.getItem("zipCodeShip")
        + " " + `<em> Address Ship: </em>`
        + localStorage.getItem("addressShip")
        + " " + `<em> City Ship: </em>`
        + localStorage.getItem("cityShip")
        + " " + `<em> Country Ship: </em>`
        + localStorage.getItem("countryShip");
    tbody.innerHTML = '';

    let items = localStorage.getItem("productsInCart");
    items = JSON.parse(items);

    Object.values(items).map(item => {
        let names = item.name.replace(/ /g, "_");
        tbody.innerHTML += `
            <tr>
                <td width="60%">
                    <img src="/static/img/copy/${names}.jpg" alt="" width="90">
                </td>
                <td width="60%">
                    <span class="font-weight-bold">${names}</span>
                    <div class="product-qty"><span class="d-block">Quantity : ${item.inCart}</span>
                     </div>
                </td>
                <td width="20%">
                    <div class="text-right"> <span class="font-weight-bold">${item.price}</span> </div>
                </td>
            </tr>
            `
    });
    totals.innerHTML += `
        <tr class="border-top border-bottom">
            <td>
                <div class="text-left"><span class="font-weight-bold">Subtotal</span></div>
            </td>
            <td>
                <div class="text-right"><span class="font-weight-bold">${localStorage.getItem("totalCost") + " USD"}</span></div>
             </td>
        </tr>
    `;
    localStorage.clear();
}

fillOrderDetails();
