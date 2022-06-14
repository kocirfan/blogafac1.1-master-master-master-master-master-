//////////////////////OLUŞTUR/////////////////////////////
let categorydom = document.querySelector("#kategorisubmit");
categorydom.addEventListener("click", categorysubmit);

function categorysubmit(event) {
  event.preventDefault();

  let categoryname = document.querySelector("#kategoriText");
  let categoryParentId = document.querySelector("#kategoriType");

  let data = {
    name: categoryname.value,
    parentId: categoryParentId.value,
  };

  fetch("http://localhost:8080/auth/v1/category/categories" ,{
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Success:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
///////////////////////GET///////////////////////////
async function getCategory() {
  let url = "http://localhost:8080/auth/v1/category/categories";
  try {
    let res = await fetch(url);
    return await res.json();
  } catch (error) {
    console.log(error);
  }
}
{/* <option  selected value="0">Yok</option> */}
////////////////////////KATEGORİNİ SEÇ //////////////////////////////
async function renderCategory() {
  let categories = await getCategory();
  let html = "";
 
  categories.forEach((category) => {
    let htmlSegment = ` 
      
          
           <option id="kategoriTitle" value="${category.id}">${category.name}  </option>
          
          
         `;
    html += htmlSegment;
  });

  let container = document.querySelector("#kategoriType");
  container.innerHTML = html;
}

renderCategory();
////////////////////PARENTID İLE GET//////////////////////////
///////////////////////GET///////////////////////////
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
  let html = "";
  brach = [];
  var preparedHtml = "";
  categorie.forEach((category) => {
      preparedHtml += "<li>"+category.name+"</li>"; // anabaşlık
      categories.forEach((subcategory) =>{  // alt kategorilerin hepsini çağrıyorum
        if(subcategory.parentId == category.id){  // benim ana kategorimin atındaki başlık mı?
          preparedHtml += "<ul>"+subcategory.name+"</ul>"; // evetse yaz
        }if(subcategory.parentId == subcategory.id && subcategory.id != subcategory.id){
          preparedHtml += "<ul>"+subcategory.name+"</ul>";
        }
      })
     
  });
       let container = document.querySelector("#bastır");
      container.innerHTML = preparedHtml;
     
     
}
parentIds();
