package des.kanban.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="proyecto")
public class Proyecto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id_proyecto;
	
	String nombre;
	
	String descripcion;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proyecto", orphanRemoval = true)
	private Set<Tarea> tareas = new HashSet<>();
   
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "proyecto_usuario", 
	joinColumns = @JoinColumn(name = "id_proyecto"), 
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<Usuario> trabajadores = new HashSet<>();
	

	public Proyecto() {
		super();
	}
	public Proyecto(Long id_proyecto, String nombre, String descripcion) {
		super();
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
	
	public Long getId_proyecto() {
		return id_proyecto;
	}
	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	public Set<Usuario> getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(Set<Usuario> trabajadores) {
		this.trabajadores = trabajadores;
	}
	public void addTrabajador(Usuario usuario) {
		this.trabajadores.add(usuario);
		usuario.getProyectos().add(this);
	}
	public void eliminarTrabajador(Usuario usuario) {
		this.trabajadores.remove(usuario);
	}
	
}
