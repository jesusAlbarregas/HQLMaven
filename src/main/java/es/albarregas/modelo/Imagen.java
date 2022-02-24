/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jesus
 */
@Entity
@Table(name = "Imagenes")
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdImagen")
    private Integer idImagen;
    
    @Column(name = "NombreImagen", length = 50)
    private String nombreImagen;
    
    @Column(name = "NumeroAnexo", nullable = false)
    private Byte numAnexo;
    
    @Column(name = "Empresa")
    private Short idEmpresa;
    
    @Column(name = "Instituto")
    private Short idInstituto;
    
    @Column(name = "Ciclo", length = 7)
    private String idCiclo;
    
    @Column(name = "Alumno")
    private Integer idAlumno;
    
    @Column(name = "estado", nullable = false)
    private Byte estado;

    public Imagen() {
    }

//    public Imagen(Integer idImagen, String nombreImagen, byte[] imagenByte) {
    public Imagen(Integer idImagen, String nombreImagen) {
        this.idImagen = idImagen;
        this.nombreImagen = nombreImagen;
//        this.imagenByte = imagenByte;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public Byte getNumAnexo() {
        return numAnexo;
    }

    public void setNumAnexo(Byte numAnexo) {
        this.numAnexo = numAnexo;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    public Short getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Short idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Short getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Short idInstituto) {
        this.idInstituto = idInstituto;
    }

    public String getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(String idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    
}
