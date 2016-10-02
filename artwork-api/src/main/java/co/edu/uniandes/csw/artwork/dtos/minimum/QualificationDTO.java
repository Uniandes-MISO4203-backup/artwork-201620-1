/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class QualificationDTO implements Serializable{

private Long id;
private String userClient;
private int qualification;
private ArtworkDTO artwork;

public QualificationDTO(){
    //Constructor clase
}
public QualificationDTO(QualificationEntity entity){
if(entity!=null){
this.id=entity.getId();
this.qualification = entity.getQualification();
this.artwork = new ArtworkDTO(entity.getArtwork());
this.userClient = entity.getUserClient();
}
}
public QualificationEntity toEntity(){
QualificationEntity entity = new QualificationEntity();
entity.setId(this.getId());
entity.setName("");
entity.setQualification(qualification);
return entity;
}
public Long getId(){
return id;
}
public void setId(Long id){
this.id = id;
}
public int getQualification(){
return qualification;
}
public void setQualification(int qualification){
this.qualification = qualification;
}
public ArtworkDTO getArtwork(){
return artwork;
}
public void setArtwork(ArtworkDTO artwork){
this.artwork = artwork;
}
public String getUserClient(){
return userClient;
}
public void setUserClient(String userClient){
this.userClient = userClient;
}
}
