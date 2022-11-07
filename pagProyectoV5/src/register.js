const nombre = document.getElementById('nombre_anana');

function registerbubu(){
   if(nombre.value != ""){
      localStorage.setItem("register__submit",nombre.value);
      //console.log(nombre.value);
      /*console.log(localStorage.getItem("register__submit"));*/
      window.location.href = "./../public/main.html"
   }
}