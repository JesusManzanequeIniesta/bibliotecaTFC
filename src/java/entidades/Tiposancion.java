/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yisus
 */
@Entity
@Table(name = "TIPOSANCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposancion.findAll", query = "SELECT t FROM Tiposancion t"),
    @NamedQuery(name = "Tiposancion.findById", query = "SELECT t FROM Tiposancion t WHERE t.id = :id"),
    @NamedQuery(name = "Tiposancion.findByDescripcion", query = "SELECT t FROM Tiposancion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tiposancion.findByDiassancion", query = "SELECT t FROM Tiposancion t WHERE t.diassancion = :diassancion")})
public class Tiposancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="TIPOSANCION_ID_GENERATOR", sequenceName="SEQ_TIPOSANCION_ID",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOSANCION_ID_GENERATOR")
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIASSANCION")
    private Integer diassancion;
    @OneToMany(mappedBy = "tiposancionId")
    private List<Prestamo> prestamoList;

    public Tiposancion() {
    }

    public Tiposancion(Integer id) {
        this.id = id;
    }

    public Tiposancion(Integer id, String descripcion, Integer diassancion) {
        this.id = id;
        this.descripcion = descripcion;
        this.diassancion = diassancion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiassancion() {
        return diassancion;
    }

    public void setDiassancion(Integer diassancion) {
        this.diassancion = diassancion;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
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
        if (!(object instanceof Tiposancion)) {
            return false;
        }
        Tiposancion other = (Tiposancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tiposancion[ id=" + id + " ]";
    }
    
}
