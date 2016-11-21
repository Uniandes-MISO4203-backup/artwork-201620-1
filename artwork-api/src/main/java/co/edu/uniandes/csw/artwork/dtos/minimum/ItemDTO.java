/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ItemDTO implements Serializable {

    private Long id;
    private String name;
    private Long qty;
    private Boolean shoppingCart;
    private String userName;
    
    public ItemDTO() {
        //Constructor clase
    }

    public ItemDTO(ItemEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.qty = entity.getQty();
            this.shoppingCart = entity.getShoppingCart();
            if(entity.getClient() != null){
                this.userName = entity.getClient().getName();
            }
        }
    }

    public ItemEntity toEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setQty(this.getQty());
        entity.setShoppingCart(this.getShoppingCart());
        return entity;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
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
     * Obtiene el atributo shoppingCart.
     *
     * @return atributo shoppingCart.
     * @generated
     */
    public Boolean getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Establece el valor del atributo shoppingCart.
     *
     * @param shoppingCart nuevo valor del atributo
     * @generated
     */
    public void setShoppingCart(Boolean shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
