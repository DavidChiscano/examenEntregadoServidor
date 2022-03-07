package des.kanban.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import des.kanban.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {/* extends PagingAndSortingRepository<Usuario, Long>{ */

	Usuario findByNombreUsuario(String nombre_usuario);

}
