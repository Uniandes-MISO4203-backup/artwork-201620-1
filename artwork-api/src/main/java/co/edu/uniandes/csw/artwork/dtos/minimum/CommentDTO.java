/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a.quintero10
 */
@XmlRootElement
public class CommentDTO extends BaseDTO implements Serializable {
    
 private String comment;
 
 
 /**
  * @generated
  */
 public CommentDTO() {
        super();
 } 
  
 /**
  * Crea un objeto CommentDTO a partir de un objeto CommentEntity.
  *
  * @param entity Entidad CommentEntity desde la cual se va a crear el nuevo objeto.
  * @generated
  */ 
 public CommentDTO(CommentEntity entity) {
	   if (entity!=null){
  this.id=entity.getId();
  this.name=entity.getName();
  this.comment=entity.getComment();
    
    }
 }
 
  /**
  * Convierte un objeto CommentDTO a CommentEntity.
  *
  * @return Nueva objeto CommentEntity.
  * @generated
  */
 public CommentEntity toEntity() {
  CommentEntity entity = new CommentEntity();
  entity.setId(this.getId());
  entity.setName(this.getName());
  entity.setComment(this.getComment());
    
 return entity;
 }


 /**
  * @return the comment
  */
 public String getComment() {
  return comment;
 }

 /**
  * @param comment the comment to set
  */
 public void setComment(String comment) {
  this.comment = comment;
 }
}
