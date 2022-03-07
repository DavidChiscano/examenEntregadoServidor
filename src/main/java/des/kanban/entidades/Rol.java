package des.kanban.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="rol")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id_rol;
	String nombre_rol;
	
	
	//List<Usuario> usuarios;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_rol", 
	joinColumns = @JoinColumn(name = "id_rol"), 
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<Usuario> usuarios = new HashSet<>();

	
	public Rol() {
		super();
	}


	public Rol(Long id_rol, String nombre_rol) {
		super();
		this.id_rol = id_rol;
		this.nombre_rol = nombre_rol;
	}


	public Long getId_rol() {
		return id_rol;
	}


	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}


	public String getNombre_rol() {
		return nombre_rol;
	}


	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		//usuario.getRoles().add(this);
	}
	public void eliminarUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
	}
	
	
}
