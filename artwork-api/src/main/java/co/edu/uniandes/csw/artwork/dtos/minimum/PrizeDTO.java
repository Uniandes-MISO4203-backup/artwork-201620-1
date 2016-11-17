/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.PrizeEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@XmlRootElement
public class PrizeDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String color;
    private String trophy;
    private Date date;
    

   

    public PrizeDTO() {
     // constructor prizeDTO  
    }

    public PrizeDTO(PrizeEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.color = entity.getColor();
            this.trophy = entity.getTrophy();
            this.date = entity.getDate();
        }
    }

    public PrizeEntity toEntity() {
        PrizeEntity entity = new PrizeEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setColor(this.getColor());
        entity.setTrophy(this.getTrophy());
        entity.setDate(this.getDate());
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
  * @return the description
  */
 public String getDescription() {
  return description;
 }

 /**
  * @param description the description to set
  */
 public void setDescription(String description) {
  this.description = description;
 }

 /**
  * @return the color
  */
 public String getColor() {
  return color;
 }

 /**
  * @param color the color to set
  */
 public void setColor(String color) {
  this.color = color;
 }

 /**
  * @return the trophy
  */
 public String getTrophy() {
  return trophy;
 }

 /**
  * @param trophy the trophy to set
  */
 public void setTrophy(String trophy) {
  this.trophy = trophy;
 }

 /**
  * @return the date
  */
 public Date getDate() {
  return date;
 }

 /**
  * @param date the date to set
  */
 public void setDate(Date date) {
  this.date = date;
 }



}
