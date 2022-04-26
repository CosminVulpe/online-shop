console.log("cart JS");

const displayCart = () => {
    let items = localStorage.getItem("productsInCart");
    items = JSON.parse(items);

    let productContainer = document.querySelector('.products');
    let totalCost = localStorage.getItem("totalCost");
    productContainer.innerHTML = '';

    if (items && productContainer) {
        Object.values(items).map(item => {
            let names = item.name.replace(/ /g, "_");
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
                    <div class="cart-total">${(item.inCart * parseFloat(item.price)) + " USD"}</div>
                </div>
            `;
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
    } else {
        productContainer.innerHTML += `
        <p class="text-center">You have nothing in cart</p>
        <button onclick="location.href='/'">Go Back</button>
        `;
    }
}

const removeItemsCart = () => {
    let cartItems = localStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    const removeIcon = document.querySelectorAll('.fa-window-close');
    for (let i = 0; i < removeIcon.length; i++) {
        let button = removeIcon[i];
        button.addEventListener('click', (event) => {
            let buttonClicked = event.target;
            let productClicked = buttonClicked.parentElement;
            let productName = productClicked.querySelectorAll('.description span')[0].innerText;

            deleteFromLocalMemory(cartItems, productName)
            localStorage.setItem("cartNumber", (parseInt(localStorage.getItem("cartNumber")) - 1).toString());
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
    total = Math.round(total * 100) / 100;
    document.querySelector('.bascket-total').innerText = total + " USD";
    localStorage.setItem("totalCost", total.toString());
}

const updateQuantityCart = () => {
    let cartItems = localStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    let quantityInputs = document.querySelectorAll('.cart-quantity input');
    quantityInputs.forEach(input => {
        input.addEventListener('change', (event) => {
            let inputClicked = event.target;
            let productClicked = inputClicked.parentElement.parentElement;
            let productName = productClicked.querySelectorAll('.description span')[0].innerText;

            if (parseInt(inputClicked.value) < 0 || isNaN(inputClicked.value)) {
                inputClicked.value = 1;
                updateLocalMemory(cartItems, productName, inputClicked.value)
            }
            if (parseInt(inputClicked.value) === 0) {
                deleteFromLocalMemory(cartItems, productName);
                localStorage.setItem("cartNumber", (parseInt(localStorage.getItem("cartNumber")) - 1).toString());
                input.parentElement.parentElement.remove();

            }
            updateLocalMemory(cartItems, productName, inputClicked.value);
            updateCartTotal();
        })
    });

    let upDownArrows = document.querySelectorAll('.cart-quantity i');
    for (let i = 0; i < upDownArrows.length; i++) {
        let currentArrow = upDownArrows[i];
        currentArrow.addEventListener('click', () => {
            let inputValue = currentArrow.parentElement.querySelector('input');
            let arrowValue = currentArrow.classList.value;
            let productClicked = inputValue.parentElement.parentElement;
            let productName = productClicked.querySelectorAll('.description span')[0].innerText;

            if (arrowValue === "fa fa-arrow-down down-arr" && parseInt(inputValue.value) >= 1) {
                let inputValueInt = parseInt(inputValue.value);
                inputValueInt -= 1;
                inputValue.value = inputValueInt.toString();
                updateLocalMemory(cartItems, productName, inputValueInt)

            } else if (arrowValue === "fa fa-arrow-up up-arr" && parseInt(inputValue.value) >= 1) {
                let inputValueInt = parseInt(inputValue.value);
                inputValueInt += 1;
                inputValue.value = inputValueInt.toString();
                updateLocalMemory(cartItems, productName, inputValueInt);

            }
            if (parseInt(inputValue.value) === 0) {
                deleteFromLocalMemory(cartItems, productName);
                localStorage.setItem("cartNumber", (parseInt(localStorage.getItem("cartNumber")) - 1).toString());
                inputValue.parentElement.parentElement.remove();

            }
            updateCartTotal();
        });
    }
}


const updateLocalMemory = (cartItems, productName, inputValueInt) => {
    for (const [key, value] of Object.entries(cartItems)) {
        if (key === productName) {
            cartItems[key].inCart = parseInt(inputValueInt);
        }
    }
    localStorage.setItem("productsInCart", JSON.stringify(cartItems));
}


const deleteFromLocalMemory = (cartItems, productName) => {
    for (const [key, value] of Object.entries(cartItems)) {
        if (key === productName) {
            delete cartItems[key];
        }
    }
    localStorage.setItem("productsInCart", JSON.stringify(cartItems));
}


const init = () => {
    displayCart();
    removeItemsCart();
    updateQuantityCart()
}

init();

