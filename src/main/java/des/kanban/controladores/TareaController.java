package des.kanban.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class TareaController {
	@Autowired
	TareaServicio tareaServicio;
	@Autowired
	UsuarioServicio usuarioServicio;

	// OBTENER TODAS LAS OFERTAS
	@ResponseBody
	@GetMapping("/tareas")
	public List<Tarea> obtenerTodos() {
		return tareaServicio.cargarTodasTareas();
	}
	
//	@ResponseBody
//	@GetMapping("/tarea/{id}")
//	public Tarea getIdTarea(@PathVariable("id") long id) {
//		return tareaServicio.obtenerTareaPorId(id);
//	}

	@GetMapping("/tarea/{id}")
	public String getTarea(@PathVariable("id") long id, Model modelo) {
		Tarea tareas = tareaServicio.obtenerTareaPorId(id);
		modelo.addAttribute("tareas", tareas);
		return "/tarea/perfilTarea";
	}
	
	@PostMapping("/tarea/modificar/{id}")
	public String modificarTarea(@PathVariable Long id, Tarea tarea){
		Tarea tareaMOD = tareaServicio.obtenerTareaPorId(id);
		tareaMOD.setTitulo(tarea.getTitulo());
		tareaMOD.setDescripcion(tarea.getDescripcion());
		tareaMOD.setPrioridad(tarea.getPrioridad());
		tareaServicio.modificarTarea(tareaMOD, id);
		
		return "redirect:/tarea/"+id;
	}
	
	
	
	@ResponseBody
	@PostMapping("/tarea/crear/{id}")
	public ResponseEntity<Object> crearTarea(@PathVariable Long id, @RequestBody Map<String, String> json) {
		
		Tarea tarea = new Tarea();
		
		tarea.setTitulo(json.get("titulo"));
		tarea.setPrioridad(json.get("prioridad"));
		tarea.setDescripcion(json.get("descripcion"));
		tarea.setEstado(json.get("estado"));
		
		String valor = json.get("tareaUsuario");
		Usuario u = usuarioServicio.obtenerUsuarioPorId(Long.parseLong(valor));
		
		tarea.setTareaUsuario(u);
	
		Tarea tareaCreada = tareaServicio.crearTarea(id, u.getId_usuario(), tarea);
		
		return new ResponseEntity<Object>(tarea.getIdTarea(), HttpStatus.OK);
	}

}
