/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

/**
 *
 * @author a.quintero10
 */
import co.edu.uniandes.csw.artwork.entities.ClientEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ClientDTO implements Serializable{
private Long id;
private String name;

public ClientDTO(){
     //Constructor necesario para la creacion de dto
}
public ClientDTO(ClientEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();        
}
}
public ClientEntity toEntity(){
ClientEntity entity = new ClientEntity();
entity.setId(this.getId());
entity.setName(this.getName());
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
}
