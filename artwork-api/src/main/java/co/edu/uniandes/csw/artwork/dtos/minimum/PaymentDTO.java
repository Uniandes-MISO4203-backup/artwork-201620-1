/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentDTO implements Serializable{
private Long id;
private String name;
private Date date;

public PaymentDTO(){
     //Constructor necesario para la creacion de dto
}
public PaymentDTO(PaymentEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();
this.date=entity.getDate();
}
}
public PaymentEntity toEntity(){
PaymentEntity entity = new PaymentEntity();
entity.setId(this.getId());
entity.setName(this.getName());
entity.setDate(this.getDate());
return entity;
}
public Long getId(){
return id;
}
public void setId(Long id){
this.id = id;
}
public String getName(){
return name;
}
public void setName(String name){
this.name = name;
}
public Date getDate(){
return date;
}
public void setDate(Date date){
this.date = date;
}

}
/**
 *
 * @author a.quintero10
 */

