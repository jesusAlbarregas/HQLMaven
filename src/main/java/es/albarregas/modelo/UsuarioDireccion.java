package es.albarregas.modelo;

import java.io.Serializable;

public class UsuarioDireccion implements Serializable{
    
    private String nombre;
    private String calle;
    private String codigoPostal;
    private long numeroPermisos;

    public UsuarioDireccion(String nombre, String calle, String codigoPostal, long numeroPermisos) {
        this.nombre = nombre;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.numeroPermisos = numeroPermisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public long getNumeroPermisos() {
        return numeroPermisos;
    }

    public void setNumeroPermisos(long numeroPermisos) {
        this.numeroPermisos = numeroPermisos;
    }
    
    
}
