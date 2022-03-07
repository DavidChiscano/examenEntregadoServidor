package des.kanban.modelo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import des.kanban.entidades.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{// extends PagingAndSortingRepository<Proyecto, Long>{
	List<Proyecto>findByNombreContainsOrDescripcionContains(String nombre, String descripcion);
}
