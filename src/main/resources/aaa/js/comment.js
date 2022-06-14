async function getCommentData() {
  let url = "http://localhost:8080/auth/v1/comment/comments";
  try {
    let res = await fetch(url);
    return await res.json();
  } catch (error) {
    console.log(error);
  }
}
async function renderCommentData() {
  let comment = await getCommentData();
  let html = "";
  comment.forEach((comm) => {
    
    console.log(comm);
    let htmlSegment = ` 
    
    <ul>
      <li>
         <a href="#post-list">${comm.id} - Kullancı ID - ${comm.user.id}</a>
         <div>${comm.text}</div>
         <div >${comm.user.username}</div>
         <button id="approvecomment" data-id="${comm.durum}" value="${comm.id}" class= "${comm.durum == 0 ? "btn btn-success btn-sm" : "btn btn-danger btn-sm" } "  >Show Comment</button>
          
      </li>
    </ul>`;

    $(document).on("click", "#approvecomment", function myFunction(e) {
      var ID = $(this).attr("value");
      var dataid = $(this).attr("data-id");


      if (dataid != 1 && ID == comm.id) {
        let data = { durum: 1 };
        
       
        $.ajax({
          method: "PUT",
          url: "http://localhost:8080/auth/v1/comment/comments/" + ID,
          data: JSON.stringify(data),
          contentType: "application/json;charset=utf-8",
         
        })
       
        
      }if(dataid == 1 && ID == comm.id){
        let data = {durum : 0};
        $.ajax({
          method: "PUT",
          url: "http://localhost:8080/auth/v1/comment/comments/" + ID,
          data: JSON.stringify(data),
          contentType: "application/json;charset=utf-8",
          
        })
      
      }
      

     e.preventDefault();
    });

    html += htmlSegment;
  });

  let container = document.querySelector("#my_comment");
  container.innerHTML = html;
}

renderCommentData();
