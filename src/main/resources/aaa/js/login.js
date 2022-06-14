// login



 let loginDom = document.querySelector("#loginForm");


function loginSubmit(event) {
  event.preventDefault();

  let Loginusername = document.querySelector("#exampleFirstName");
  let Loginpassword = document.querySelector("#exampleInputPassword");
  // let LoginLatName = document.querySelector('#exampleLastName')
  

  let data = {
    username: Loginusername.value,
    password: Loginpassword.value,
    // lastName: LoginLatName.value
    
  };

  fetch("http://localhost:8080/auth/login", {
    method: "POST",
    headers: {
        "Accept": "application/json, text/plain, */*",
         "Content-Type": "application/json" 
        },

    body: JSON.stringify(data),
   
    
    
  })
    .then((response) => response.json())
    .then((data) => {
        alert("başarılı giriş")
      console.log("Success:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });

    
    
}
loginDom.addEventListener("click", loginSubmit);