/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.CategoryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CategoryDTO extends BaseDTO implements Serializable{

public CategoryDTO(){
    //Constructor clase
}
public CategoryDTO(CategoryEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();
}
}
public CategoryEntity toEntity(){
CategoryEntity entity = new CategoryEntity();
entity.setId(this.getId());
entity.setName(this.getName());
return entity;
}

}
