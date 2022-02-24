package es.albarregas.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "correo")
public class Correo implements Serializable{
    
    @Id
    private int id;
    private String email;
    
    @ManyToOne
    @JoinColumn(name="IdProfesor")
    private Profesor profesor;
    
    public Correo(){
        
    }
    
    public Correo(int id, String email, Profesor profesor){
        this.id = id;
        this.email = email;
        this.profesor = profesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
}
