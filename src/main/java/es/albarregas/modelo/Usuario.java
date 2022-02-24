package es.albarregas.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
@NamedQuery(name = "nombreCP", query = "SELECT u.nombre,u.direccion.codigoPostal FROM Usuario AS u WHERE u.id = :id")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario", nullable = false)
    private int id;
    
    @Column(name = "Nombre", nullable = false, length = 30)
    private String nombre;
    
    @Column(name = "UserName", nullable = false, length = 20)
    private String username;
    
    @Column(name = "Password", nullable = false, length = 100)
    private String password;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaNacimiento", nullable = false, length = 10)
    private Date fechaNacimiento;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IdDireccion", nullable = true)
    private Direccion direccion;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUsuario")
    @OrderColumn(name = "idx")
    private List<Permiso> permisos;

    public Usuario() {
    }

    public Usuario(String nombre, String username, String password, Date fechaNacimiento, Direccion direccion, List<Permiso> permisos) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.permisos = permisos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        direccion.setUsuario(this);
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
