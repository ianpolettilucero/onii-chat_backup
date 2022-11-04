//const uri = '/main.html'
const input = document.getElementById('chat_input');

input.addEventListener("keyup", function(event) {
  console.log(event.key)
  if (event.key == "Enter") {
    //event.preventDefault();
    console.warn("sentado")
    writeMsg();
    input.value = "";
  } 
})

function changeBackground() {
    document.body.style.background = linear-gradient (getRandomColor()); 
}


window.addEventListener("load",function() {
    changeBackground(getRandomColor()) 
});

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
    const msg = {
      TextContent: "agachate y coonocelo",
      SenderID: "Marcelo",
      Date: "12-06-2022"
    };

    /*document.getElementById("chat_container").innerHTML +=
    "<div id='message_container'>"+
    "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" +
    "<div id='text_container'>" + "<h6>" + msg.TextContent + "</h6>" + "</div>" +
    "</div>"
    ;*/
    writeMsg(msg);
    
}


function writeMsg () {
  const msg = {
    TextContent: input.value,
    SenderID: "tu negro kawaii"
  }

  document.getElementById("chat_container").innerHTML +=
  "<div id='message_container'>"+
  "<div id='name_container'>" + "<h5>" + msg.SenderID    + "</h5>" + "</div>" +
  "<div id='text_container'>" + "<h6>" + msg.TextContent + "</h6>" + "</div>" +
  "</div>"
  ;
}

function bubu2(){
  //clltextbar
}

function changeBackgroundGradient2(colorA, colorB, degrees) {
    document.body.style.background = linear-gradient(degrees, colorA, colorB);
}

function changeBackgroundGradient() {

    document.body.style.background = linear-gradient(
        getRandomInt(360),
        getRandomColor(), 
        getRandomColor()
        
    );
    
}

function createHex() {
    var hexCode1 = "";
    var hexValues1 = "0123456789abcdef";
    
    for ( var i = 0; i < 6; i++ ) {
      hexCode1 += hexValues1.charAt(Math.floor(Math.random() * hexValues1.length));
    }
    return hexCode1;
  }
  
  function generate() {
    
    var deg = Math.floor(Math.random() *360);
    
    var gradient = "linear-gradient(" + deg + "deg, " + "#" + createHex() + ", " + "#" + createHex() +")";
    
    document.getElementById("output").innerHTML = gradient;
    document.getElementById("bg").style.background = gradient;
    
    console.log(hexCode1, hexCode2);
   
  }
  document.onload = generate();

const button = document.querySelector('button');
button.addEventListener('click', () => {
    const element = document.querySelector('.demo');
    element.style.backgroundColor = 'red';
});

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
  