async function getSepet () {
    let url = "http://localhost:8080/auth/v1/order"
    try {
      let res = await fetch(url);
      return await res.json();
     
    } catch (error) {
      console.log(error);
    }
  }
  ////////////////////////////////////////////////////////////////////////////////
  async function getProductData() {
    let url = "http://localhost:8080/auth/v1/product/products";
    try {
      let res = await fetch(url );
      return await res.json();
    } catch (error) {
      console.log(error);
    }
}
  async function renderSepetData() {
    let items = await getSepet();
    let products = await getProductData();

    
    let html = "";
     items.forEach((item) => {
      
      let htmlSegment = ` 
      <div id="username" class="card-header">
        ${item.orderproducts}
        </div>
        <div class="card-body">
        
        <h5 id="blog_card_title" class="card-title text-center text-uppercase">${item.address}</h5>
                    
        <p id="blog_card_text" class="card-text"></p>
        </div>
                
        `;
  
      html += htmlSegment;
      let commentdom = document.querySelector("#sepet");
      commentdom.innerHTML = html;
     })

     items  = [];
     products = [];
     items = products;
    }

    
    renderSepetData();

    ///////////////////////////////////////////////////////////
