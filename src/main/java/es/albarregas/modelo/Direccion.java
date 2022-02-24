package es.albarregas.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDireccion")
    private int id;
    
    @Column(name = "Calle", nullable = false, length = 40)
    private String calle;
    
    @Column(name = "CodigoPostal", nullable = false, length = 5)
    private String codigoPostal;
    
    @OneToOne(mappedBy = "direccion")
    private Usuario usuario;
    
    public Direccion(){
        
    }
    
    public Direccion(String calle, String codigoPostal){
        
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
