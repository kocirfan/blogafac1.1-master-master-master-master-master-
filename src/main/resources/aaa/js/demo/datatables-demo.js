///post
async function getPostData() {
  let url = "http://localhost:8080/auth/v1/post/posts";
  try {
    let res = await fetch(url);
    return await res.json();
  } catch (error) {
    console.log(error);
  }
}
async function renderPostData() {
  let posts = await getPostData();
  let html = "";
   posts.forEach((post) => {
     console.log(post);
    let htmlSegment = ` 
    <div id="username" class="card-header">
    ${post.user.username}
  </div>
  <div class="card-body">
    
  <h5 id="blog_card_title" class="card-title text-center text-uppercase"> ${post.title}</h5>
              
  <p id="blog_card_text" class="card-text">${post.text}</p>
  </div>
              
      `;

    html += htmlSegment;
    let commentdom = document.querySelector("#post_yaz_send");
    commentdom.addEventListener("click", commentsubmit);
    
    function commentsubmit(event) {
      event.preventDefault();
    
      let commentPost = document.querySelector("#comment_post_id");
      let commentText = document.querySelector("#post_yaz");
      let commentUser = document.querySelector("#comment_user_id");
    
      let data = {
        postId: post.id,
        text: commentText.value,
        userId: localStorage.getItem('currentUser'),
      };
    
      fetch("http://localhost:8080/auth/v1/comment/comments", {
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


  });

  let container = document.querySelector("#blog_post");
   container.innerHTML = html;
  
}
renderPostData();



//////////////////////////////////////////////////////////////////////
let commentdom = document.querySelector("#post_yaz_send");
commentdom.addEventListener("click", commentsubmit);

function commentsubmit(event) {
  event.preventDefault();

  let commentPost = document.querySelector("#comment_post_id");
  let commentText = document.querySelector("#post_yaz");
  let commentUser = document.querySelector("#comment_user_id");

  let data = {
    postId: commentPost.value,
    text: commentText.value,
    userId: localStorage.getItem('currentUser'),
  };

  fetch("http://localhost:8080/auth/v1/comment/comments", {
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
//////////////////////////////////////////////////////////////
