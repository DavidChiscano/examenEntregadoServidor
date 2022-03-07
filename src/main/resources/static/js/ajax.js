function anadirTareaTabla(titulo, prioridad, estado, idTarea) {

	let tabla = document.getElementById("tabla");
	let tbody = document.createElement("TBODY");
	tbody.setAttribute("id", "resultados");

	let tr = document.createElement('tr');
	let tdTitulo = document.createElement('td');
	tdTitulo.textContent = titulo;
	
	let spanPrio = document.createElement('span')
    spanPrio.classList.add("badge", "bg-primary", "rounded-pill");
    spanPrio.textContent = prioridad;
    
	let tdPrio = document.createElement('td');



	let spanEstado = document.createElement('span')
    spanEstado.classList.add("badge", "bg-primary", "rounded-pill");
    spanEstado.textContent = estado;
    
    
	let tdEstado = document.createElement('td');


	let a = document.createElement('a');
	a.setAttribute("href", "/tarea/" + idTarea);

	let btnInfo = document.createElement("input");
	btnInfo.setAttribute("type", "button");
	btnInfo.setAttribute("class", "btn btn-success");
	btnInfo.setAttribute("id", "verTarea");
	btnInfo.setAttribute("value", "Ver Tarea");

	tr.appendChild(tdTitulo);
	tdPrio.appendChild(spanPrio)
	tdEstado.appendChild(spanEstado);
	tr.appendChild(tdPrio);
	tr.appendChild(tdEstado);
	a.appendChild(btnInfo);
	tr.appendChild(a);
	

	tbody.appendChild(tr);
	tabla.appendChild(tbody);

}


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
		body: JSON.stringify({ titulo: $('#titulo').val(), descripcion: $('#descripcion').val(), prioridad: $('#prioridad').val(), estado: ('Preparada'), tareaUsuario: jsonObj })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json()
			} else {
				throw "La tarea ya existe";
			}

		})
		.then(id => {
			console.log("sii");
			anadirTareaTabla($('#titulo').val(), $('#prioridad').val(), 'Preparada', id);

		})
		.catch(function() {
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

