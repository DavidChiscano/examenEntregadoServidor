package des.kanban.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.UsuarioRepository;

@Service
@Transactional
public class ProyectoServicioImpl implements ProyectoServicio {
	@Autowired
	ProyectoRepository proyectoJPA;
	@Autowired
	UsuarioRepository usuarioJPA;

	@Override
	public Proyecto obtenerProyectoPorId(Long idProyecto) {
		
		return proyectoJPA.findById(idProyecto).orElse(null);
	}

	@Override
	public Proyecto crearProyecto(Proyecto proyecto, Long id_Usuario) {
		Usuario user = new Usuario();
		user = usuarioJPA.findById(id_Usuario).orElse(null);
		user.addProyecto(proyecto);
		return proyectoJPA.save(proyecto);
	}

	@Override
	public void borrarProyecto(Long id) {
		proyectoJPA.deleteById(id);
	}

	@Override
	public Proyecto modificarProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proyecto> cargarProyectos() {
		
		return proyectoJPA.findAll();
	}
	
	@Override
	public List<Proyecto> buscarProyectosPorNombreODescripcion(String nombre,String descripcion) {
		return proyectoJPA.findByNombreContainsOrDescripcionContains(nombre, descripcion);
	}

}
