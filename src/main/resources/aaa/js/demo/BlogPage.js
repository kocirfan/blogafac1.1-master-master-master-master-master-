// //user
// async function getPostUser() {
//     let url = "http://localhost:8080/users";
//     try {
//       let res = await fetch(url);
//       return await res.json();
//     } catch (error) {
//       console.log(error);
//     }
//   }
//   async function renderPostUsers() {
//     let users = await getUsers();
//     let html = "";
//   users.forEach((user) => {
//     let htmlSegment = `
//         <div>${user.userName}</div>
//     `
//     html += htmlSegment;
//  });
// let container = document.querySelector("#blogUser");
// container.innerHTML = html;
// }
