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

        if (filterSupplier !== "Select supplier"
            && filterCategory !== "Select category") {
            bothFiltersSelected = true;
        }

        if (bothFiltersSelected === false) {
            if (supplier.innerText === filterSupplier
                || category.innerText === filterCategory) {
                cards[i].style.display = "";
            } else {
                cards[i].style.display = "none";
            }
        } else {
            if (supplier.innerText === filterSupplier
                && category.innerText === filterCategory) {
                cards[i].style.display = "";
            } else {
                cards[i].style.display = "none";
            }
        }
    }

    if (checkAllCardsHaveDisplayNone() === true) {
        const p = document.createElement('p');
        p.innerText = "No such item(s)";
        p.setAttribute('class', 'text-center msg');
        document.querySelector('#products').append(p);
    } else {
        const message = document.querySelector('#products .msg');
        if (message !== null) {
            message.style.display = "none";
        }
    }

}

const checkAllCardsHaveDisplayNone = () => {
    let cards = cardContainer.getElementsByClassName('product');
    let counter = 0;
    for (let i = 0; i < cards.length; i++) {
        if (cards[i].style.display === "none") {
            counter++;
        }
    }
    return (counter === 4);
}


const addItemsToCart = (product) => {
    setItems(product);
    let products = localStorage.getItem("productsInCart");
    products = JSON.parse(products);
    const map = new Map();
    for (const item of Object.keys(products)) {
        if (!map.has(item)) {
            map.set(item, product.name);
            localStorage.setItem('cartNumber', map.size);
            document.querySelector('.nav-cart span').textContent = map.size;
        }
    }
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

const searchBar = () => {
    const searchIconParent = document.querySelector('.fa-search').parentElement;
    searchIconParent.innerHTML = `
            <label for="search-products">Search products:</label>
            <input type="search" id="search-products" name="search-products">
        `;
    const searchInput = document.querySelector('#search-products');
    searchInput.addEventListener('input', () => {
        let valueInput = searchInput.value.toUpperCase();

        const cards = cardContainer.getElementsByClassName('product');
        for (let i = 0; i < cards.length; i++) {
            let name = cards[i].querySelector('.card-header h4.card-title');
            if (name.innerText.toUpperCase().indexOf(valueInput) > -1) {
                cards[i].style.display = "";
            } else {
                cards[i].style.display = "none";
            }
        }
    });
}


const toggleModal = (modalTitle, processOption) => {

    $('#register-login-modal').modal('show');
    const submitButton = document.querySelector('.modal-body>form>button');
    submitButton.addEventListener('click', processOption === 'register' ? addUser : loginUser);
}

const addUser = async () => {
    const password = document.querySelector('[name="form-password"]').value;
    const user = document.querySelector('[name="form-username"]').value;

    let dataPosted = {
        username: user,
        userPassword: password
    };

    let serverResponse = await fetch("/registration", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'accept': 'application/json'
        },
        body: JSON.stringify(dataPosted),
    });
}


const loginUser = async () => {
    console.log("login USER")
    const password = document.querySelector('[name="form-password"]').value;
    const user = document.querySelector('[name="form-username"]').value;

    let dataPosted = {
        username: user,
        userPassword: password
    };
    let serverResponse = await fetch("/api/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'accept': 'application/json'
        },
        body: JSON.stringify(dataPosted),
    });

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
    const searchIcon = document.querySelector('.fa-search');
    searchIcon.addEventListener('click', searchBar);
}

init();