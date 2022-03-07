package des.kanban.servicios;


import java.util.HashSet;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import des.kanban.entidades.Rol;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.RolRepository;
import des.kanban.modelo.UsuarioRepository;
@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {
	@Autowired
	UsuarioRepository usuarioJPA;
	@Autowired
	RolRepository rolJPA;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario obtenerUsuarioPorId(Long idUsuario) {
		
		return usuarioJPA.findById(idUsuario).orElse(null);
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		// TODO Auto-generated method stub
		return usuarioJPA.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		
		return usuarioJPA.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		Rol rolUser = rolJPA.getById(1L);
		usuario.setTareasUsuario(new HashSet<Tarea>());
		usuario.addRol(rolUser);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		return usuarioJPA.save(usuario);
	}

	@Override
	public Usuario obtenerIdPorNombre(String nombreUsuario) {
	
		return usuarioJPA.findByNombreUsuario(nombreUsuario);
	}


}
