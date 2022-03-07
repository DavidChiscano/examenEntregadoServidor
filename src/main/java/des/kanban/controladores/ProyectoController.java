package des.kanban.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.TareaServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class ProyectoController {
	@Autowired
	ProyectoServicio proyectoService;
	@Autowired
	TareaServicio tareaService;
	@Autowired
	UsuarioServicio usuarioService;

	@GetMapping("/perfil")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proyecto/perfilProyecto");
		return mav;
	}

	@GetMapping("/proyecto/{id}")
	public String getProyecto(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		Proyecto proyecto = proyectoService.obtenerProyectoPorId(id);
		
		List<Tarea> tareas = tareaService.cargarTareas(id);
		
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("tareas", tareas);
		model.addAttribute("ListaUsuarios", usuarioService.obtenerTodosLosUsuarios());
		
		//para ver el nombre del proyecto en el template
		String nombreSession = proyecto.getNombre();
		request.getSession().setAttribute("nombre", nombreSession );
		
		return "/proyecto/perfilProyecto";
	}
	@GetMapping("/proyecto/buscar/{nombreProyecto}")
	public String getBuscarProyecto(Model modelo, @RequestParam String nombreProyecto) {
		List<Proyecto> ListaProyectos = proyectoService.buscarProyectosPorNombreODescripcion(nombreProyecto, nombreProyecto);
		modelo.addAttribute("ListaProyectos", ListaProyectos);
		return "index";
	}
	
	@GetMapping("/proyecto/borrar/{id}")
	public String getBorrarIdProducto(@PathVariable("id") Long id) {
		proyectoService.borrarProyecto(id);
		return "redirect:/index";
	}

}
