/* ------------ HERRAMIENTAS GENERALES ------------ */

/* revelaPassword muestra el valor ingresado en un campo con type="password" */
function revelaPassword() {
		let eye = document.getElementById('revelaPassword');
        let psw = document.getElementById('psw');
        if(psw.type === 'password') {
            eye.children[0].className = 'fa fa-eye';
            psw.type = 'text'
      	} else {
            eye.children[0].className = 'fa fa-eye-slash';
            psw.type = 'password'
        }
}