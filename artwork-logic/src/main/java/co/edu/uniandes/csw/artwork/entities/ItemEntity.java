/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.artwork.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @generated
 */
@Entity
public class ItemEntity extends BaseEntity implements Serializable {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Basic(optional = false)
 @Column(name = "ID")
 private Long id;
 @Size(max = 255)
 @Column(name = "NAME")
 private String name;
 @Column(name = "QTY")
 private BigInteger qty;
 @Column(name = "SHOPPINGCART")
 private Short shoppingcart;
 @ManyToMany(mappedBy = "itemEntityCollection")
 private Collection<PaymentEntity> paymentEntityCollection;
 @JoinColumn(name = "ARTWORK_ID", referencedColumnName = "ID")
 @ManyToOne
 private ArtworkEntity artworkId;
 @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
 @ManyToOne
 private ClientEntity clientId;
 @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
 @ManyToOne
 private ProductEntity productId;

    private Long qty;
    
    private Boolean shoppingCart;

    @PodamExclude
    @ManyToOne
    private ArtworkEntity artwork;

    @PodamExclude
    @ManyToOne
    private ClientEntity client;

    @PodamExclude
    @ManyToOne
    private ProductEntity product;
    

    /**
     * Obtiene el atributo qty.
     *
     * @return atributo qty.
     * @generated
     */
    public Long getQty(){
        return qty;
    }

    /**
     * Establece el valor del atributo qty.
     *
     * @param qty nuevo valor del atributo
     * @generated
     */
    public void setQty(Long qty){
        this.qty = qty;
    }
    
    /**
     * Obtiene el atributo shoppingCart.
     *
     * @return atributo sohppingCart.
     * @generated
     */
    public Boolean getShoppingCart(){
        return shoppingCart;
    }

    /**
     * Establece el valor del atributo shoppingCart.
     *
     * @param shoppingCart nuevo valor del atributo
     * @generated
     */
    public void setShoppingCart(Boolean shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    /**
     * Obtiene el atributo artwork.
     *
     * @return atributo artwork.
     * @generated
     */
    public ArtworkEntity getArtwork() {
        return artwork;
    }

    /**
     * Establece el valor del atributo artwork.
     *
     * @param artwork nuevo valor del atributo
     * @generated
     */
    public void setArtwork(ArtworkEntity artwork) {
        this.artwork = artwork;
    }

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
     * Obtiene el atributo product.
     *
     * @return atributo product.
     * @generated
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Establece el valor del atributo product.
     *
     * @param product nuevo valor del atributo
     * @generated
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }    

 public ItemEntity() {
 }

 public ItemEntity(Long id) {
  this.id = id;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public BigInteger getQty() {
  return qty;
 }

 public void setQty(BigInteger qty) {
  this.qty = qty;
 }

 public Short getShoppingcart() {
  return shoppingcart;
 }

 public void setShoppingcart(Short shoppingcart) {
  this.shoppingcart = shoppingcart;
 }

 @XmlTransient
 public Collection<PaymentEntity> getPaymentEntityCollection() {
  return paymentEntityCollection;
 }

 public void setPaymentEntityCollection(Collection<PaymentEntity> paymentEntityCollection) {
  this.paymentEntityCollection = paymentEntityCollection;
 }

 public ArtworkEntity getArtworkId() {
  return artworkId;
 }

 public void setArtworkId(ArtworkEntity artworkId) {
  this.artworkId = artworkId;
 }

 public ClientEntity getClientId() {
  return clientId;
 }

 public void setClientId(ClientEntity clientId) {
  this.clientId = clientId;
 }

 public ProductEntity getProductId() {
  return productId;
 }

 public void setProductId(ProductEntity productId) {
  this.productId = productId;
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
  if (!(object instanceof ItemEntity)) {
   return false;
  }
  ItemEntity other = (ItemEntity) object;
  if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "co.edu.uniandes.csw.artwork.entities.ItemEntity[ id=" + id + " ]";
 }
}
