package des.kanban.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.entidades.Proyecto;
import des.kanban.servicios.ProyectoServicio;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class IndexController {
	@Autowired
	ProyectoServicio proyectoServicio;
	@Autowired
	UsuarioServicio usuarioServicio;
	
	
	@GetMapping("/index")
	public String getIndex(Model modelo) {
		List<Proyecto> ListaProyectos = (List<Proyecto>) proyectoServicio.cargarProyectos();
		modelo.addAttribute("ListaProyectos", ListaProyectos);
		modelo.addAttribute("ListaUsuarios", usuarioServicio.obtenerTodosLosUsuarios());
		return "index";
	}
	@GetMapping("/")
	public String getIndex2(Model modelo) {
		List<Proyecto> ListaProyectos = (List<Proyecto>) proyectoServicio.cargarProyectos();
		modelo.addAttribute("ListaProyectos", ListaProyectos);
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin () {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@PostMapping("/proyecto/crear")
	public String CrearProyecto(Proyecto proyecto,@RequestParam Long inputTrabajadores) {
		Long id_User = inputTrabajadores;
		proyectoServicio.crearProyecto(proyecto, id_User);
		return "redirect:/proyecto/"+proyecto.getId_proyecto();
	}
	
}
