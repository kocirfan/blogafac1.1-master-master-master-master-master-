//create post
// let formDom = document.querySelector("#mainform");
// formDom.addEventListener("submit", formsubmit);

// function formsubmit(event) {
//   event.preventDefault();

//   let postTitle = document.querySelector("#title");
//   let postText = document.querySelector("#text");
//   let postUser = document.querySelector("#userId");

//   let data = {
//     title: postTitle.value,
//     text: postText.value,
//     userId: postUser.value,
//   };

//   fetch("http://localhost:8080/posts", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(data),
//   })
//     .then((response) => response.json())
//     .then((data) => {
//       console.log("Success:", data);
//     })
//     .catch((error) => {
//       console.error("Error:", error);
//     });
// }