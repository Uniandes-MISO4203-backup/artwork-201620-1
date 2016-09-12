/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author s.florez10
 */
public class PaymentDTO implements Serializable{
    private Long id;
    private String name;
    private Date date;
    
     /**
     * @generated
     */
    public PaymentDTO() {
    }

    /**
     * Crea un objeto PaymentDTO a partir de un objeto PaymentEntity.
     *
     * @param entity Entidad PaymentEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public PaymentDTO(PaymentEntity entity) {
	if (entity!=null){
            this.id=entity.getId();
            this.name=entity.getName();
            this.date=entity.getDate();
        }
    }
    
     /**
     * Convierte un objeto PaymentDTO a PaymentEntity.
     *
     * @return Nueva objeto PaymentEntity.
     * @generated
     */
    public PaymentEntity toEntity() {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDate(this.getDate());
        return entity;
    }
    
     /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Obtiene el atributo date.
     *
     * @return atributo date.
     * @generated
     */
    public Date getDate() {
        return date;
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
