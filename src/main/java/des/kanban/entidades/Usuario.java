package des.kanban.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id_usuario;
	@Column(name = "contrasena")
	String password;
	@Column(name = "nombre_usuario")
	String nombreUsuario;
	
	
	public Set<Tarea> getTareasUsuario() {
		return tareasUsuario;
	}

	public void setTareasUsuario(Set<Tarea> tareasUsuario) {
		this.tareasUsuario = tareasUsuario;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy="trabajadores")
	private Set<Proyecto> proyectos = new HashSet<Proyecto>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tareaUsuario", orphanRemoval = true)
	@JsonIgnore
	private Set<Tarea> tareasUsuario = new HashSet<Tarea>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy="usuarios")
	private Set<Rol> roles = new HashSet<>();

	
	public Usuario() {
		super();
	}

	public Usuario(Long id_usuario, String password, String nombre_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.password = password;
		this.nombreUsuario = nombre_usuario;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre_usuario() {
		return nombreUsuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombreUsuario = nombre_usuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public boolean addRol(Rol rol) {
	    rol.addUsuario(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}
	
	public boolean addProyecto(Proyecto proyecto) {
	    proyecto.addTrabajador(this);
		return getProyectos().add(proyecto);
	}

	public void eliminarTrabajador(Proyecto proyecto) {
		this.proyectos.remove(proyecto);
		proyecto.getTrabajadores().remove(this);
	}
	
	
	public boolean addTarea(Tarea tarea) {
//	    tarea.addUsuario(this);
//		return getRoles().add(tarea);
		return true;
	}

	public void eliminarTarea(Tarea tarea) {
//		this.tareasUsuario.remove(tarea);
//		tarea.get.remove(this);
	}
	
}
