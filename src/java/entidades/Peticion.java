/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yisus
 */
@Entity
@Table(name = "PETICION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peticion.findAll", query = "SELECT p FROM Peticion p"),
    @NamedQuery(name = "Peticion.findById", query = "SELECT p FROM Peticion p WHERE p.id = :id"),
    @NamedQuery(name = "Peticion.findByFechainicio", query = "SELECT p FROM Peticion p WHERE p.fechainicio = :fechainicio"),
    @NamedQuery(name = "Peticion.findByFechafin", query = "SELECT p FROM Peticion p WHERE p.fechafin = :fechafin"),
    @NamedQuery(name = "Peticion.findByCalculadoActivo", query = "SELECT p FROM Peticion p WHERE p.calculadoActivo = :calculadoActivo")})
public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="PETICION_ID_GENERATOR", sequenceName="SEQ_PETICION_ID",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PETICION_ID_GENERATOR")
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIO")
    private LocalDate fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAFIN")
    private LocalDate fechafin;
    @Column(name = "CALCULADO_ACTIVO")
    private Boolean calculadoActivo;
    @JoinColumn(name = "LIBRO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Libro libroId;
    @JoinColumn(name = "SOCIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Socio socioId;

    public Peticion() {
    }

    public Peticion(Integer id) {
        this.id = id;
    }

    public Peticion(Integer id, LocalDate fechainicio, LocalDate fechafin) {
        this.id = id;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    public LocalDate getFechafin() {
        return fechafin;
    }

    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }

    public Boolean getCalculadoActivo() {
        return calculadoActivo;
    }

    public void setCalculadoActivo(Boolean calculadoActivo) {
        this.calculadoActivo = calculadoActivo;
    }

    public Libro getLibroId() {
        return libroId;
    }

    public void setLibroId(Libro libroId) {
        this.libroId = libroId;
    }

    public Socio getSocioId() {
        return socioId;
    }

    public void setSocioId(Socio socioId) {
        this.socioId = socioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peticion)) {
            return false;
        }
        Peticion other = (Peticion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Peticion[ id=" + id + " ]";
    }
    
}
