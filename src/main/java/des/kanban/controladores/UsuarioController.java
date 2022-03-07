package des.kanban.controladores;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.servicios.UsuarioServicio;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioServicio usuarioService;

//	@GetMapping("/alta")
//	public ModelAndView getAlta() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("usuario/alta");
//		return mav;
//	}
	@GetMapping("/alta")
	public String singUp() {
		return "/usuario/alta";
	}

	// POST METHODS
	@PostMapping("/usuario/alta")
	public String crearUsuario(@RequestParam String usuario, @RequestParam String password) {
		Usuario u = new Usuario();
		u.setNombre_usuario(usuario);
		u.setPassword(password);
		u.setTareasUsuario(new HashSet<Tarea>());
		usuarioService.crearUsuario(u);
		return "redirect:/index";
	}

}
