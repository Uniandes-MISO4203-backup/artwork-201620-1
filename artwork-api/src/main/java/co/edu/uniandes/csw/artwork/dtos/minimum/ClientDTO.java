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
public class ClientDTO extends BaseDTO implements Serializable{

public ClientDTO(){
    //Constructor clase
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
}
