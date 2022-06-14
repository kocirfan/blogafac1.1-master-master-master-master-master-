// //! variables
const cartBtn = document.querySelector(".cart-btn");
const clearCartBtn = document.querySelector(".btn-clear");
const cartItems = document.querySelector(".cart-items");
const cartTotal = document.querySelector(".total-value");
const cartContent = document.querySelector(".cart-list");
const productsDOM = document.querySelector("#products-dom");

let cart = [];
let buttonsDOM = [];

class Products {
    async getProducts() {
        try {
            var result = []
            var result = await fetch("http://localhost:8080/auth/v1/product/products")
            var data = await result.json();
            var products = data;
            console.log(data);
                
    
            return products;
        } catch (error) {
            console.log(error);
        }
    }
}

class UI {
    displayProducts(products) {
        let ID =window.location.href.slice(-1)

        let result = "";
        
        products.forEach(item => {
            if(item.id == ID){
                result += `
            <div class="col-lg-4 col-md-6">
                <div class="product">
                    <div class="product-image">
                        <img src="${item.image}" alt="product" class="img-fluid" />
                    </div>
                    <div class="product-hover mt-3">
                        <span class="product-title">${item.name}</span>
                        <span class="product-price">$ ${item.price}</span>
                        <button class="btn-add-to-cart" data-id=${item.id}>
                            <i class="fas fa-cart-shopping"></i>
                        </button>
                    </div>
                </div>
            </div>
            `
            }
            });
        productsDOM.innerHTML = result;
    }

    getBagButtons() {
        const buttons = [...document.querySelectorAll(".btn-add-to-cart")];
        buttonsDOM = buttons;
        buttons.forEach(button => {
            let id = button.dataset.id;
            let inCart = cart.find(item => item.id === id);
            if (inCart) {
                button.setAttribute("disabled", "disabled");
                button.style.opacity = ".3";
            } else {
                button.addEventListener("click", event => {
                    event.target.disabled = true;
                    event.target.style.opacity = ".3";
                    //* get product from products
                    let cartItem = { ...Storage.getProduct(id), amount: 1 };
                    //* add procuct to the cart
                    cart = [...cart, cartItem];
                    //* save cart in local storage
                    Storage.saveCart(cart);
                    //* save cart values
                    this.saveCartValues(cart);
                    //* display cart item
                    this.addCartItem(cartItem)
                    //* show the cart
                    this.showCart();
                })
            }
        })
    }

    saveCartValues(cart) {
        let tempTotal = 0;
        let itemsTotal = 0;
        cart.map(item => {
            tempTotal += item.price * item.amount;
            itemsTotal += item.amount;
        });

        cartTotal.innerText = parseFloat(tempTotal.toFixed(2));
        cartItems.innerText = itemsTotal;
    }

    addCartItem(item) {
        const li = document.createElement("li");
        li.classList.add("cart-list-item");
        li.innerHTML = `
            <div class="cart-left">
                <div class="cart-left-image">
                    <img src="${item.image}" alt="product" class="img-fluid" />
                </div>
                <div class="cart-left-info">
                    <a class="cart-left-info-title" href="#">${item.name}</a>
                    <span class="cart-left-info-price">$ ${item.price}</span>
                </div>
            </div>
            <div class="cart-right">
                <div class="cart-right-quantity">
                    <button class="quantity-minus" data-id=${item.id}>
                        <i class="fas fa-minus"></i>
                    </button>
                    <span class="quantity">${item.amount}</span>
                    <button class="quantity-plus" data-id=${item.id}>
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <div class="cart-right-remove">
                    <button class="cart-remove-btn" data-id=${item.id}>
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
        `;
        cartContent.appendChild(li);
    }

    showCart() {
        cartBtn.click();
    }

    setupAPP() {
        cart = Storage.getCart();
        this.saveCartValues(cart);
        this.populateCart(cart);
    }

    populateCart(cart) {
        cart.forEach(item => this.addCartItem(item));
    }

    cartLogic() {
        clearCartBtn.addEventListener("click", () => {
            this.clearCart();
        })

        cartContent.addEventListener("click", event => {
            if (event.target.classList.contains("cart-remove-btn")) {
                let removeItem = event.target;
                let id = removeItem.dataset.id;
                removeItem.parentElement.parentElement.parentElement.remove();
                this.removeItem(id);
            } else if (event.target.classList.contains("quantity-minus")) {
                let lowerAmount = event.target;
                let id = lowerAmount.dataset.id;
                let tempItem = cart.find(item => item.id === id);
                tempItem.amount = tempItem.amount - 1;
                if (tempItem.amount > 0) {
                    Storage.saveCart(cart);
                    this.saveCartValues(cart);
                    lowerAmount.nextElementSibling.innerText = tempItem.amount;
                } else {
                    lowerAmount.parentElement.parentElement.parentElement.remove();
                    this.removeItem(id);
                }
            } else if (event.target.classList.contains("quantity-plus")) {
                let addAmount = event.target;
                let id = addAmount.dataset.id;
                let tempItem = cart.find(item => item.id === id);
                tempItem.amount = tempItem.amount + 1;
                Storage.saveCart(cart);
                this.saveCartValues(cart);
                addAmount.previousElementSibling.innerText = tempItem.amount;
            }
        })
    }


    clearCart() {
        let cartItems = cart.map(item => item.id);
        cartItems.forEach(id => this.removeItem(id));
        while (cartContent.children.length > 0) {
            cartContent.removeChild(cartContent.children[0])
        }
    }

    removeItem(id) {
        cart = cart.filter(item => item.id !== id);
        this.saveCartValues(cart);
        Storage.saveCart(cart);
        let button = this.getSinleButton(id);
        button.disabled = false;
        button.style.opacity = "1";
    }

    getSinleButton(id) {
        return buttonsDOM.find(button => button.dataset.id === id);
    }
}

class Storage {
    static saveProducts(products) {
        localStorage.setItem("products", JSON.stringify(products));
    }

    static getProduct(id) {
        let products = JSON.parse(localStorage.getItem("products"));
        return products.find(product => product.id === id);
    }

    static saveCart(cart) {
        localStorage.setItem("cart", JSON.stringify(cart));
    }

    static getCart() {
        return localStorage.getItem("cart") ? JSON.parse(localStorage.getItem("cart")) : [];
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const ui = new UI();
    const products = new Products();

    ui.setupAPP();

    products.getProducts().then(products => {
        ui.displayProducts(products);
        Storage.saveProducts(products);
    }).then(() => {
        ui.getBagButtons();
        ui.cartLogic();
    })
});

////////////
class Orders {
    async getOrders() {
        
        try {
            var result =[];
           
            var result = await fetch("http://localhost:8080/auth/v1/order")
            var data = await result.json();
            var orders = data
            console.log(orders)
            return orders;
   
        } catch (error) {
            console.log(error);
        }
    }

  
    
}



document.addEventListener("DOMContentLoaded", () => {
   
     const orders = new Orders();
    //const postOrder = new OrderPost();


      orders.getOrders().then(orders => {
       
       orders.address = "Talas"
       orders.status = true;
       var simdi = orders.orderProducts = localStorage.getItem('cart');
       orders.orderProducts.quantity = localStorage.getItem('cart').length;
        orders.numberOfProducts = 2
        orders.user = localStorage.getItem('username')
        
        var ab = [orders, [simdi]]
        console.log(ab)
        let data = ab
        console.log(data)

        
    
         
         fetch("http://localhost:8080/auth/v1/order", [{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
          }])
            .then((response) => response.json())
            .then((data) => {
              console.log("Success:", data);
            })
            .catch((error) => {
              console.error("Error:", error);
            })

          
    })

})


        
    
