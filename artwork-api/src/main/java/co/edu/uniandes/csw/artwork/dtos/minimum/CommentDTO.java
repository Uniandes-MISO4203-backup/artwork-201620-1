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
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentDTO implements Serializable{
private Long id;
private String name;
private String comment;

public CommentDTO(){
}
public CommentDTO(CommentEntity entity){
if(entity!=null){
this.id =entity.getId();
this.name=entity.getName();
this.comment=entity.getComment();
}
}
public CommentEntity toEntity(){
CommentEntity entity = new CommentEntity();
entity.setId(this.getId());
entity.setName(this.getName());
entity.setComment(this.getComment());
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
public String getComment(){
return comment;
}
public void setComment(String comment){
this.comment = comment;
}

}
