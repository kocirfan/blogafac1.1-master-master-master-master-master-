//create user

// let userForm = document.querySelector("#registerform")
// userForm.addEventListener('submit', registersubmitsub)
// function registersubmitsub(event){
//   event.preventDefault()
  
//   let formname = document.querySelector("#exampleFirstName")
//   let formlastName = document.querySelector("#exampleLastName")
//   let formpassword = document.querySelector("#exampleInputPassword")
  
//   let Userdata = {userName : formname.value,  lastName : formlastName.value,  password : formpassword.value }
//   console.log(Userdata);
//   fetch('http://localhost:8080/users', {
//     method: 'POST', 
//     headers: {'Content-Type': 'application/json'},
//     body: JSON.stringify(Userdata),
//   })
//     .then(response => response.json())
//     .then(Userdata => {
//         console.log('Success:',  Userdata);
//        })
//    .catch((error) => {
//            console.error('Error:', error);
//      });
  
// } 


// Blog Page // kullanıcı listele
// async function getBlogUser() {
//   let url = "http://localhost:8080/users";
//   try {
//     let res = await fetch(url);
//     return await res.json();
//   } catch (error) {
//     console.log(error);
//   }
// }
// async function renderBlogUsers() {
//   let users = await getBlogUser();
//   let html = "";
//   users.forEach((user) => {
    
//     let htmlSegment = `
//          <div>
//           ${user.userName} | ${user.lastName}
//          </div> 
//     `
//     html += htmlSegment;
//   });
//   let container = document.querySelector("#blogUser");
//   container.innerHTML = html;
// }
// renderBlogUsers()

//  let showUserCard = document.querySelector("#blogUser");
//     showUserCard.addEventListener('click', showUser)
//     async function showUser(event) {
//       event.preventDefault()
      
//       fetch("http://localhost:8080/posts/" + id, { method: "GET" }).then(
//         () => (showUserCard.innerHTML = user.userName + user.lastName)
//       );
//     } 