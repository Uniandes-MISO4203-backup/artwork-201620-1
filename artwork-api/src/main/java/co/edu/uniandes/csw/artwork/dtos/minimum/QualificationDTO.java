/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.ClientEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class QualificationDTO implements Serializable{

    private Long id;
    private String name;
    private int qualification;
    private ArtworkDTO artwork;
    private ClientDTO client;

    /**
     * @generated
     */
    public QualificationDTO() {
    }

    /**
     * Crea un objeto QualificationDTO a partir de un objeto QualificationEntity.
     *
     * @param entity Entidad QualificationEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public QualificationDTO(QualificationEntity entity) {
	   if (entity!=null){
            this.id=entity.getId();
            this.name=entity.getName();
            this.qualification = entity.getQualification();            
       }
    }

    /**
     * Convierte un objeto QualificationDTO a QualificationEntity.
     *
     * @return Nueva objeto QualificationEntity.
     * @generated
     */
    public QualificationEntity toEntity() {
        QualificationEntity entity = new QualificationEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setQualification(qualification);
        return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }
    
     /**
     * Devuelve la calificación dada a la obra de arte por el usuario.
     * @return the qualification
     */
    public int getQualification() {
        return qualification;
    }

    /**
     * Establece la calificación dada a la obra de arte por el usuario.
     * @param qualification the qualification to set
     */
    public void setQualification(int qualification) {
        this.qualification = qualification;
    }
    
    /**
     * Devuelve la obra de arte asociada a esta calificación.
     * @return the artwork
     */
    public ArtworkDTO getArtwork() {
        return artwork;
    }

    /**
     * Establece la obra de arte asociada a esta calificación.
     * @param artwork the artwork to set
     */
    public void setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
    }

    /**
     * Devuelve el cliente que da la calificación a la obra de arte.
     * @return the client
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * Establece el cliente que da la calificación a la obra de arte.
     * @param client the client to set
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }

}
