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
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ArtistDTO extends BaseDTO implements Serializable{
private Long appraisal;

public ArtistDTO(){
    //Constructor clase
}

public ArtistDTO(ArtistEntity entity){
if (entity!=null){
Long avaluo=0L;
this.id=entity.getId();
this.name=entity.getName();
//Calculo del avaluo a partir del precio de las obras de arte
for(ArtworkEntity artworkEntity : entity.getArtworks()){
avaluo+=artworkEntity.getPrice();
}
this.appraisal=avaluo;
}
}
public ArtistEntity toEntity(){
ArtistEntity entity = new ArtistEntity();
entity.setId(this.getId());
entity.setName(this.getName());
return entity;
}

public Long getAppraisal(){
return this.appraisal;
}
public void setAppraisal(Long appraisal){
this.appraisal = appraisal;
}
}

