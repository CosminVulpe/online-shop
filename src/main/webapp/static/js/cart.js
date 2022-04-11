console.log("cart JS");
const displayCart = () => {
    let items = localStorage.getItem("productsInCart");
    items = JSON.parse(items);

    let actualPrice = "";
    let currency = "";

    let productContainer = document.querySelector('.products');
    let totalCost = localStorage.getItem("totalCost");
    if (items && productContainer) {
        productContainer.innerHTML = '';

        Object.values(items).map(item => {
            let names = item.name.replace(/ /g, "_");
            for (let x of item.price) {
                if (isNaN(x) === false || x === '.') {
                    actualPrice += x;
                } else {
                    currency += x;
                }
            }
            productContainer.innerHTML += `
                <div class="cart-product">
                    <i class="fa fa-window-close" aria-hidden="true"></i>
                    <img class="img-fluid" src="/static/img/copy/${names}.jpg" alt="">
                    <div class="description">
                        <span>${item.name}</span>
                        <span>${item.price}</span>
                    </div>
                    <div class="cart-quantity">
                        <i  class="fa fa-arrow-down down-arr" aria-hidden="true"></i>
                         <input type="number" id="quantity-change" name="quantity-change" value="${item.inCart}">
                        <i class="fa fa-arrow-up up-arr" aria-hidden="true"></i>
                    </div>
                    <div class="cart-total">${(item.inCart * parseFloat(actualPrice)) + " " + currency.replace(/[^a-zA-Z0-9]/g, '')}</div>
                </div>
            `;
            currency = "";
            actualPrice = "";
        });
        productContainer.innerHTML += `
            <div class="basket-total-price">
                <h4 class="basket-title">Basket Total</h4>
                <h4 class="bascket-total">${parseFloat(totalCost) + " USD"}</h4>
            </div>
            <div class="checkout-btn">
                <button onclick="location.href='/checkout'">Checkout</button>
            </div>
        `;
    }
}

const removeItemsCart = () => {
    const removeIcon = document.querySelectorAll('.fa-window-close');
    for (let i = 0; i < removeIcon.length; i++) {
        let button = removeIcon[i];
        button.addEventListener('click', (event) => {
            let buttonClicked = event.target;
            buttonClicked.parentElement.remove();
            updateCartTotal();
        });
    }
}


const updateCartTotal = () => {
    let cartItems = document.querySelector('.products');
    let cartProducts = cartItems.getElementsByClassName('cart-product');
    let total = 0;
    for (let i = 0; i < cartProducts.length; i++) {
        let currentProduct = cartProducts[i];
        let priceProduct = parseFloat(currentProduct.querySelector('.cart-total').innerText.replace('USD', ''));
        let quantityProduct = currentProduct.querySelector('.cart-quantity input').value;
        total = total + (priceProduct * quantityProduct);
    }
    // total = Math.round(total * 100) / 100;
    document.querySelector('.bascket-total').innerText = total + " USD";
}

const updateQuantityCart = () => {
    let quantityInputs = document.querySelectorAll('.cart-quantity input');
    quantityInputs.forEach(input => {
        input.addEventListener('change', (event) => {
            let inputClicked = event.target;
            if (parseInt(inputClicked.value) < 0 || isNaN(inputClicked.value)) {
                inputClicked.value = 1;
            }
            if (parseInt(inputClicked.value) === 0) {
                input.parentElement.parentElement.remove();
            }
            updateCartTotal();
        })
    });
    let upDownArrows = document.querySelectorAll('.cart-quantity i');
    for (let i = 0; i < upDownArrows.length; i++) {
        let currentArrow = upDownArrows[i];
        currentArrow.addEventListener('click', () => {
            let inputValue = currentArrow.parentElement.querySelector('input');
            let arrowValue = currentArrow.classList.value;
            if (arrowValue === "fa fa-arrow-down down-arr" && parseInt(inputValue.value) >= 1) {
                let inputValueInt = parseInt(inputValue.value);
                inputValueInt -= 1;
                inputValue.value = inputValueInt.toString();
            } else if (arrowValue === "fa fa-arrow-up up-arr" && parseInt(inputValue.value) >= 1) {
                let inputValueInt = parseInt(inputValue.value);
                inputValueInt += 1;
                inputValue.value = inputValueInt.toString();
            }
            if (parseInt(inputValue.value) === 0) {
                inputValue.parentElement.parentElement.remove();
            }
            updateCartTotal();
        });
    }
}


const init = () => {
    displayCart();
    removeItemsCart();
    updateQuantityCart()
}

init();

