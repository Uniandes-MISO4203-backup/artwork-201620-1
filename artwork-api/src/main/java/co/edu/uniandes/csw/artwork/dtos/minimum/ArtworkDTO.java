/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class ArtworkDTO implements Serializable{
private Long id;
private String name;
private String image;
private Long price;
private Integer width;
private Integer height;

@PodamExclude
private List<QualificationDTO> qualifications = new ArrayList<>();

public ArtworkDTO(){
}

public ArtworkDTO(ArtworkEntity entity){
if(entity!=null){
this.id=entity.getId();
this.name=entity.getName();
this.image=entity.getImage();
this.price=entity.getPrice();
this.width=entity.getWidth();
this.height=entity.getHeight();

}
}
public ArtworkEntity toEntity(){
ArtworkEntity entity = new ArtworkEntity();
entity.setId(this.getId());
entity.setName(this.getName());
entity.setImage(this.getImage());
entity.setPrice(this.getPrice());
entity.setWidth(this.getWidth());
entity.setHeight(this.getHeight());
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
public String getImage(){
return image;
}
public void setImage(String image){
this.image= image;
}
public Long getPrice(){
return price;
}
public void setPrice(Long price){
this.price = price;

}
public Integer getWidth(){
return width;
}
public void setWidth(Integer width){
this.width = width;
}
public Integer getHeight(){
return height;
}
public void setHeight(Integer height){
this.height = height;
}
public List<QualificationDTO>getQualifications(){
return qualifications;
}
public void setQualifications(List<QualificationDTO> qualifications){
this.qualifications = qualifications;
}
}

