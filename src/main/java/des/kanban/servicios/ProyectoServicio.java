package des.kanban.servicios;

import java.util.List;

import des.kanban.entidades.Proyecto;
public interface ProyectoServicio {

	public Proyecto obtenerProyectoPorId(Long idProyecto);
	
	public void  borrarProyecto(Long idProyecto);
	
	public Proyecto modificarProyecto(Proyecto proyecto);

	Proyecto crearProyecto(Proyecto proyecto, Long id_Usuario);
	
	List<Proyecto> cargarProyectos();
	
	List<Proyecto> buscarProyectosPorNombreODescripcion(String nombre, String descripcion);


	
}