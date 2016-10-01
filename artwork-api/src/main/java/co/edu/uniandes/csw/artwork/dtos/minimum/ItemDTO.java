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
public class ItemDTO extends BaseDTO implements Serializable{
private Long qty;

public ItemDTO(){
    //Constructor clase
}
public ItemDTO(ItemEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();
this.qty=entity.getQty();
}
}
public ItemEntity toEntity(){
ItemEntity entity = new ItemEntity();
entity.setId(this.getId());
entity.setName(this.getName());
entity.setQty(this.getQty());
return entity;
}
public Long getQty(){
return qty;
}
public void setQty(Long qty){
this.qty = qty;
}
}
/**
 *
 * @author a.quintero10
 */

