package des.kanban.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

import des.kanban.entidades.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{// extends PagingAndSortingRepository<Rol, Long> {
	
}
