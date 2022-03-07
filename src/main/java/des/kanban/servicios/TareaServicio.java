package des.kanban.servicios;

import java.util.List;

import des.kanban.entidades.Tarea;

public interface TareaServicio {

	public Tarea obtenerTareaPorId(Long idTarea);
	
	public Tarea crearTarea(Long idProyecto, Long idUsuario, Tarea tarea);
	
	public Boolean  borrarTarea(Tarea tarea);
	
	public Tarea modificarTarea(Tarea tarea, Long id);
	
	List<Tarea> cargarTareas(Long idProyecto);
	
	List<Tarea> cargarTodasTareas();
	
}
