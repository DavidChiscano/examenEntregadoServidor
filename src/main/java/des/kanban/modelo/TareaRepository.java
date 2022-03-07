package des.kanban.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import des.kanban.entidades.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long>{
	@Query ("from Tarea where id_proyecto LIKE :id_proyecto")
	List<Tarea> findAllByIdTarea(Long id_proyecto);// extends PagingAndSortingRepository<Tarea, Long>{


}
