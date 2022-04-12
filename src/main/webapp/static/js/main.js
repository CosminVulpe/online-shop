const cardContainer = document.querySelector('#products');
const buttons = document.querySelectorAll('.btn');
const cards = cardContainer.getElementsByClassName('product');
let productsInStore = {}

const filterCards = () => {
    let supplier = document.querySelector('#supplier');
    let filterSupplier = supplier.options[supplier.selectedIndex].innerText;

    let bothFiltersSelected = false;

    let category = document.querySelector('#category');
    let filterCategory = category.options[category.selectedIndex].innerText;

    const cards = cardContainer.getElementsByClassName('product');

    for (let i = 0; i < cards.length; i++) {
        let supplier = cards[i].querySelector('.card-header p.supplier-name');
        let category = cards[i].querySelector('.card-header p.category-name');

        if (supplier.innerText === filterSupplier && category.innerText === filterCategory) {
            bothFiltersSelected = true;
        }
        // console.log(bothFiltersSelected);
        // console.log(supplier.innerText + " EQUALS SUPPLIER " + filterSupplier, supplier.innerText === filterSupplier)
        // console.log(category.innerText + " EQUALS CATEGORY " + filterCategory, category.innerText === filterCategory)

        if ((supplier.innerText === filterSupplier
            || category.innerText === filterCategory) && bothFiltersSelected === false) {
            cards[i].style.display = "";
        } else if ((supplier.innerText === filterSupplier
            && category.innerText === filterCategory) && bothFiltersSelected === true) {
            cards[i].style.display = "";
        } else {
            cards[i].style.display = "none";
        }
    }
}

const addItemsToCart = (product) => {
    let productNumber = localStorage.getItem('cartNumber');
    productNumber = parseInt(productNumber);
    if (productNumber) {
        localStorage.setItem('cartNumber', productNumber + 1);
        document.querySelector('.nav-cart span').textContent = productNumber + 1;
    } else {
        localStorage.setItem('cartNumber', 1);
        document.querySelector('.nav-cart span').textContent = 1;
    }
    setItems(product);
}

const setItems = (product) => {
    let cartItems = localStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    if (cartItems !== null) {
        if (cartItems[product.name] === undefined) {
            cartItems = {
                ...cartItems,
                [product.name]: product
            }
        }
        cartItems[product.name].inCart += 1;
    } else {
        product.inCart = 1;
        cartItems = {
            [product.name]: product
        };
    }

    localStorage.setItem("productsInCart", JSON.stringify(cartItems));
}

const onLoadCartNumber = () => {
    let productNumber = localStorage.getItem('cartNumber');
    if (productNumber) {
        document.querySelector('.nav-cart span').textContent = productNumber;
    }
}


const addProducts = (key, value) => {
    productsInStore[key] = value;
}

const calculateTotalCost = (product) => {
    let cartPrice = localStorage.getItem("totalCost");

    if (cartPrice !== null) {
        cartPrice = parseInt(cartPrice);
        localStorage.setItem("totalCost", cartPrice + parseFloat(product.price));
    } else {
        localStorage.setItem("totalCost", parseInt(product.price));
    }
}


const init = () => {
    let supplier = document.querySelector('#supplier');
    supplier.addEventListener('change', filterCards);

    let category = document.querySelector('#category');
    category.addEventListener('change', filterCards);

    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener('click', () => {
            let name = cards[i].querySelector('.card-header h4.card-title');
            let supplier = cards[i].querySelector('.card-header p.supplier-name');
            let category = cards[i].querySelector('.card-header p.category-name');
            let price = cards[i].querySelector('.card-footer .card-text p.lead');

            addProducts("name", name.innerText);
            addProducts("supplier", supplier.innerText);
            addProducts("category", category.innerText);
            addProducts("price", price.innerText);
            addProducts("inCart", 0);
            addItemsToCart(productsInStore);
            calculateTotalCost(productsInStore);
        })
    }
    onLoadCartNumber();
}

init();