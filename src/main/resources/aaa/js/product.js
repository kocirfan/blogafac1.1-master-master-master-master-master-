async function getParentCategory() {
  let url = "http://localhost:8080/auth/v1/category/categories/parent";
  try {
    let res = await fetch(url);
    return await res.json();
  } catch (error) {
    console.log(error);
  }
}

async function parentIds() {
  var categorie = await getParentCategory(); // hepsi
  var categories = await getCategory();
  brach = [];
  var preparedHtml = "";
  categorie.forEach((category) => {
      preparedHtml += "<li><a href='#'>"+category.name+"</a></li>"; // anabaşlık
      categories.forEach((subcategory) =>{  // alt kategorilerin hepsini çağrıyorum
        if(subcategory.parentId == category.id){  // benim ana kategorimin atındaki başlık mı?
          preparedHtml += `
            <a href='${"http://localhost:8080/auth/v1/product/products/" + subcategory.id}'><ul>${subcategory.name}</ul></a>
          ` ; // evetse yaz
        }
      })
     
  });
       let container = document.querySelector("#bastır1");
      container.innerHTML = preparedHtml;
     
     
}
parentIds();




async function getProductData() {
    let url = "http://localhost:8080/auth/v1/product/products";
    try {
      let res = await fetch(url );
      return await res.json();
    } catch (error) {
      console.log(error);
    }
}

async function renderProduct(event) {
    let products = await getProductData();
    
    // products.map(getsimdiID);
    // function getsimdiID(simdiA){
    //   IDA = simdiA.id;
    let html = "";
    
    products.forEach((product) => {
     
      console.log(product);
      let htmlSegment = ` 
      
      <div class="row">
        <div class="col-12"> 
            <div class="mb-3 bg-info text-warning"></div>
            </div>
        
            <div class="col-3  mb-2 mt-2">
               <img class="img-thumbnail" id="productImage" src="${product.image}" alt="productImage" />
            </div>
            
            <div class="col-6 ">
              <div class="col-12 bg-dark text-white mb-3"><a class=" text-white stretched-link"  href="#">${product} </a></div>
              <li><a id="basac"href=${"productDetail.html?id=" +  product.id}> ${product.name}</a></li>
              <li>${product.price}</li>
            
             <ul>
                <li>
                  ${product.description}
                </li>
             </ul>

             <div class="mt-5"><button class="btn btn-success btn-sm"><a class="text-white"href="${"cards.html?id=" +  product.id}">SEPETE EKLE</a> </button></div>
          </div>  
          </div>
        </div> 
     </div>  
        `;
      
    html += htmlSegment;

   
    
    });


    let container = document.querySelector("#product");
    container.innerHTML = html;
    
    //event.preventDefaul()
 
}

renderProduct();
