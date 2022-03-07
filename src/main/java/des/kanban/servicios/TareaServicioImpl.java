package des.kanban.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.kanban.entidades.Proyecto;
import des.kanban.entidades.Tarea;
import des.kanban.entidades.Usuario;
import des.kanban.modelo.ProyectoRepository;
import des.kanban.modelo.TareaRepository;
import des.kanban.modelo.UsuarioRepository;

@Service
@Transactional
public class TareaServicioImpl implements TareaServicio {
	@Autowired
	TareaRepository tareaJPA;
	@Autowired
	ProyectoRepository proyectoJPA;
	@Autowired
	UsuarioRepository usuarioJPA;

	@Override
	public Tarea crearTarea(Long idProyecto, Long idUsuario, Tarea tarea) {
		Proyecto p = proyectoJPA.findById(idProyecto).orElse(null);
		Usuario u = usuarioJPA.findById(idUsuario).orElse(null);
		tarea.setTareaUsuario(u);
		tarea.setProyecto(p);
		return tareaJPA.save(tarea);
	}

	@Override
	public Boolean borrarTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tarea modificarTarea(Tarea tarea, Long id) {
		Tarea obtenerTarea = tareaJPA.findById(id).orElse(null);
		
		obtenerTarea.setTitulo(tarea.getTitulo());
		obtenerTarea.setDescripcion(tarea.getDescripcion());
		obtenerTarea.setPrioridad(tarea.getPrioridad());

		return tareaJPA.save(obtenerTarea);
	}

	@Override
	public Tarea obtenerTareaPorId(Long idTarea) {
		// TODO Auto-generated method stub
		return tareaJPA.findById(idTarea).orElse(null);
	}

	@Override
	public List<Tarea> cargarTareas(Long idProyecto) {
		return tareaJPA.findAllByIdTarea(idProyecto);
	}

	@Override
	public List<Tarea> cargarTodasTareas() {
		
		return tareaJPA.findAll();
	}

}
