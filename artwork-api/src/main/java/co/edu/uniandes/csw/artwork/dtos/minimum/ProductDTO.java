/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ProductEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ProductDTO extends BaseDTO implements Serializable{
 private Long price;
 
 public ProductDTO(){
    //Constructor clase
 }
public ProductDTO(ProductEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();
this.price=entity.getPrice();
}
}
public ProductEntity toEntity(){
ProductEntity entity= new ProductEntity();
entity.setId(this.getId());
entity.setName(this.getName());
entity.setPrice(this.getPrice());
return entity;
}
public Long getPrice(){
return price;
}
public void setPrice(Long price){
 this.price=price;
}
}
