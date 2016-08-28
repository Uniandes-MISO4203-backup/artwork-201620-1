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
package co.edu.uniandes.csw.artwork.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class QualificationEntity extends BaseEntity implements Serializable {

    private int qualification;
    
    @PodamExclude
    @ManyToOne
    private ArtworkEntity artwork;
    
    @PodamExclude
    @ManyToOne
    private ClientEntity client;

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
    public ArtworkEntity getArtwork() {
        return artwork;
    }

    /**
     * Establece la obra de arte asociada a esta calificación.
     * @param artwork the artwork to set
     */
    public void setArtwork(ArtworkEntity artwork) {
        this.artwork = artwork;
    }

    /**
     * Devuelve el cliente que da la calificación a la obra de arte.
     * @return the client
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Establece el cliente que da la calificación a la obra de arte.
     * @param client the client to set
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    

    
}
