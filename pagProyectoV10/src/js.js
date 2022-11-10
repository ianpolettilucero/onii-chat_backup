//const uri = '/main.html'
const input = document.getElementById('chat_input');

input.addEventListener("keyup", function(event) {
  console.log(event.key)
  if (event.key == "Enter") {
    //event.preventDefault();
    console.warn("sentado")
    if(!donotxss(input.value)){
      writeMsg();
      input.value = "";
    }else{
      input.value = "";
    }
    
  } 
})
function logoutbubu(){
  localStorage.setItem("register__submit",null)
  window.location.href = "./../public/register.html"
}

function configbubu(){
  window.location.href = "./../public/config.html"
}

function changeBackground() {
    document.body.style.background = linear-gradient (getRandomColor()); 
}



window.addEventListener("load",function() {
    changeBackground(getRandomColor()) 
});

var nombreDelLindo = localStorage.getItem("register__submit");
console.log(localStorage.getItem("register__submit"));

function bubu () {
    /*var doc = document.getElementById('chat_container');
    fetch('http://localhost:3350/main.html',{method:'GET'})
    .then( response => {
        response.json().then( value => {
          //console.log(value); 
          console.log(JSON.stringify(value));
          doc.append(JSON.stringify(value));

        })
    })*/
    //console.log(doc);
    //Agarrar Mensaje
    /*const msg = {
      TextContent: "agachate y coonocelo",
      SenderID: "Marcelo",
      Date: "12-06-2022"
    };*/

    /*document.getElementById("chat_container").innerHTML +=
    "<div id='message_container'>"+
    "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" +
    "<div id='text_container'>" + "<h6>" + msg.TextContent + "</h6>" + "</div>" +
    "</div>"
    ;*/

    const server1 = {
      Name: "the pap hous",
      A: 2
    };
    const server4 = {
      Name: "</h1> <a href='https://tenor.com/view/viralhog-fat-monkey-eating-lettuce-gif-12091517'></a> <h1>",
      A: 2
    }
    const server2 = {
      Name: "mogoltistas",
      A: 2
    };
    const server3 = {
      Name: "basta de dms",
      A: 2
    };

    /*displayServer(server1);
    displayServer(server2);
    displayServer(server3);*/
    displayServer(server4);
    
}


function writeMsg () {
  const msg = {
    TextContent: input.value,
    SenderID: nombreDelLindo
  }
  //usuario?
  document.getElementById("chat_container").innerHTML +=
  "<div id='message_filler' >" +
  "<div id='message_container' class='scroll-page'>"+
  /* "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" + */
  "<div id='text_container'>" + "<h6>" + msg.TextContent + "</h6>" + "</div>" +
  "</div>"+"</div>"
  ;
  //izquierda
  document.getElementById("chat_container").innerHTML +=
  "<div id='message_filler2' >" +
  "<div class='float-right' id='message_reciver' class='scroll-page'>"+
  /* "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" + */
  "<div id='text_container'>" + "<h6>" + msg.TextContent + "</h6>" + "</div>" +
  "</div>"+"</div>"
  ;
  document.getElementById("chat_container").innerHTML += 
  "<div id='message_filler' >" +
  "<div id='message_container' class='scroll-page'>"+
  /* "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" + */
  "<div >" + "<h6 style='height: 20px'>" + "</h6>" + "</div>" +
  "</div>"+"</div>"
  ;
  
}


function donotxss(text){
  //let text = message_reciver;
  let result = text.includes("</");
  if(result == true){
    window.alert('do not xss');
  }
  return result;
}


function displayServer(server){
  document.getElementById("list_container").innerHTML +=
  "<div id='server_container'>" +
    "<div id='server_name_container'>" + "<h1>" + server.Name + "</h1>" + "</div>" +
  "</div>";

}