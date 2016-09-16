/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.florez10
 */
@Entity
public class PaymentEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @ManyToOne
    private ClientEntity client;
    
    @PodamExclude
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
