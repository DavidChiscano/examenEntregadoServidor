function creaTarea() {
	// OBTENER ID DE LA URL
	let enlace = window.location.href;
	let contenedor = enlace.split("/");
	let id = contenedor[4];
	var csrfToken = $("[name='_csrf']").attr("value");
	console.log(csrfToken);
	var jsonObj = JSON.parse($('#inputTrabajadores').val());
	fetch('/tarea/crear/' + id, {
		headers: {
			credentials: 'same-origin',
			'Content-type': 'application/json',
			'X-CSRF-TOKEN': csrfToken,
		},
		method: 'POST',
		body: JSON.stringify({ titulo: $('#titulo').val(), prioridad: $('#prioridad').val(), tareaUsuario: jsonObj })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json()
			} else {
				throw "La tarea ya existe";
			}

		})
		.then(data => {
			alert("sii");
			//anadirFilaTabla(data.nombre,data.email);
		})
		.catch(function(messsageDeError) {
			alert("errorr");
		});

}

function limpiarTablaTareas() {
	let resultados = document.getElementById("datos");
	resultados.replaceChildren();
}

document.addEventListener("DOMContentLoaded", function() {
	$("#crearTarea").click(creaTarea);
});

