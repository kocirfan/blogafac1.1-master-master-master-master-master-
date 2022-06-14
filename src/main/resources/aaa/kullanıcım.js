//giriş yapan kullanıcıyı al
async function getKullanıcı() {
    fetch("http://localhost:8080/auth/login", {
       
        headers: { "Content-Type": "application/json" ,
        "Authorization" : localStorage.getItem("tokenKey"),
    }
    .then((response) => response.json())
    .then((data) => {
        console.log("Success:", data);
        localStorage.getItem("tokenKey",data.accessToken);
        localStorage.getItem("refreshKey",data.refreshToken);
        localStorage.getItem("currentUser",data.userId);
        localStorage.getItem("username",Loginusername.value)
      })
      .catch((error) => {
        console.error("Error:", error);
      })
    })

}


 
  getKullanıcı();
//   async function renderKullanıcı() {
//     let kullanıcı = await getKullanıcı();
//     let html = "";
//      kullanıcı.forEach((user) => {
//        console.log(user);
//       let htmlSegment = ` 
     
                
//         `;
  
//       html += htmlSegment;
//     });
  
//     let container = document.querySelector("#kullanicim");
//      container.innerHTML = html;
    
//   }
//   renderKullanıcı();