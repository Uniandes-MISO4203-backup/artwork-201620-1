/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.quintero10
 */
@XmlRootElement
public class CommentDTO implements Serializable {
    private Long id;
    private String name;
    private String comment;
    
    
   public CommentDTO() {
    } 
   
    public CommentDTO(CommentEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.comment=entity.getComment();
       }
    }
    public CommentEntity toEntity() {
        CommentEntity entity = new CommentEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setComment(this.getComment());
    return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the artwork
     */
   
}
