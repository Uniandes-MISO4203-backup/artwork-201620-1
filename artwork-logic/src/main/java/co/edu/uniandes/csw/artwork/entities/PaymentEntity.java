/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.florez10
 */
@Entity
@XmlRootElement
public class PaymentEntity extends BaseEntity implements Serializable {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Basic(optional = false)
 @Column(name = "ID")
 private Long id;
 @Column(name = "DATE")
 @Temporal(TemporalType.DATE)
 private Date date;
 @Size(max = 255)
 @Column(name = "NAME")
 private String name;
 @JoinTable(name = "PAYMENTENTITY_ITEMENTITY", joinColumns = {
  @JoinColumn(name = "PAYMENTENTITY_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
  @JoinColumn(name = "ITEMS_ID", referencedColumnName = "ID")})
 @ManyToMany
 private Collection<ItemEntity> itemEntityCollection;
 @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
 @ManyToOne
 private ClientEntity clientId;
    @PodamExclude
    @ManyToOne
    private ClientEntity client;
    
    @PodamExclude
    @OneToMany
    private List<ItemEntity> items;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    /**
     * Obtiene el atributo client.
     *
     * @return atributo client.
     * @generated
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Establece el valor del atributo client.
     *
     * @param client nuevo valor del atributo
     * @generated
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }
    
    /**
     * Obtiene el atributo items.
     *
     * @return atributo items.
     * @generated
     */
 @XmlTransient
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * Establece el valor del atributo items.
     *
     * @param items nuevo valor del atributo
     * @generated
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
    
    /**
     * Obtiene el atributo date.
     *
     * @return atributo date.
     * @generated
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Establece el valor del atributo date.
     *
     * @param date nuevo valor del atributo
     * @generated
     */
    public void setDate(Date date) {
        this.date = date;
    }

 public PaymentEntity() {
 }

 public PaymentEntity(Long id) {
  this.id = id;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public Date getDate() {
  return date;
 }

 public void setDate(Date date) {
  this.date = date;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 @XmlTransient
 public Collection<ItemEntity> getItemEntityCollection() {
  return itemEntityCollection;
 }

 public void setItemEntityCollection(Collection<ItemEntity> itemEntityCollection) {
  this.itemEntityCollection = itemEntityCollection;
 }

 public ClientEntity getClientId() {
  return clientId;
 }

 public void setClientId(ClientEntity clientId) {
  this.clientId = clientId;
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
  if (!(object instanceof PaymentEntity)) {
   return false;
  }
  PaymentEntity other = (PaymentEntity) object;
  if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "co.edu.uniandes.csw.artwork.entities.PaymentEntity[ id=" + id + " ]";
 }
}
