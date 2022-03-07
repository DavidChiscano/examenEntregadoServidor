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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tarea")
public class Tarea implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long idTarea;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id_proyecto")
	Proyecto proyecto;
	
	
	String titulo;
	String descripcion;
	String prioridad;
	String estado;
	
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="id_usuario")
	@JsonIgnore
    private Usuario tareaUsuario;

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public Usuario getTareaUsuario() {
		return tareaUsuario;
	}

	public void setTareaUsuario(Usuario tareaUsuario) {
		this.tareaUsuario = tareaUsuario;
	}

	public Tarea(String titulo, String prioridad, Usuario tareaUsuario) {
		super();
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.tareaUsuario = tareaUsuario;
	}

	public Tarea() {
		super();
	}

	public Tarea(Long id_tarea, Proyecto proyecto, String titulo, String descripcion, String prioridad, String estado) {
	super();
	this.idTarea = id_tarea;
	this.proyecto = proyecto;
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.prioridad = prioridad;
	this.estado = estado;
}

	public Long getId_tarea() {
		return idTarea;
	}

	public void setId_tarea(Long id_tarea) {
		this.idTarea = id_tarea;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

//
//
//	public void addTrabajador(Usuario usuario) {
//		this.trabajadores.add(usuario);
//		usuario.getProyectos().add(this);
//	}
//	public void eliminarTrabajador(Usuario usuario) {
//		this.trabajadores.remove(usuario);
//	}

    
    
    
}
