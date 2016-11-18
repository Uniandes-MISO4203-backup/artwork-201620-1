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
import java.util.Objects;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class QualificationEntity extends BaseEntity implements Serializable {

    private int qualification;

    /**
     * Nombre de usuario del cliente en stormpath
     */
    private String userClient;

    @PodamExclude
    @ManyToOne
    private ArtworkEntity artwork;

    /**
     * Devuelve la calificación dada a la obra de arte por el usuario.
     *
     * @return the qualification
     */
    public int getQualification() {
        return qualification;
    }

    /**
     * Establece la calificación dada a la obra de arte por el usuario.
     *
     * @param qualification the qualification to set
     */
    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    /**
     * Devuelve la obra de arte asociada a esta calificación.
     *
     * @return the artwork
     */
    public ArtworkEntity getArtwork() {
        return artwork;
    }

    /**
     * Establece la obra de arte asociada a esta calificación.
     *
     * @param artwork the artwork to set
     */
    public void setArtwork(ArtworkEntity artwork) {
        this.artwork = artwork;
    }

    /**
     * @return the userClient
     */
    public String getUserClient() {
        return userClient;
    }

    /**
     * @param userClient the userClient to set
     */
    public void setUserClient(String userClient) {
        this.userClient = userClient;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.qualification;
        hash = 61 * hash + Objects.hashCode(this.userClient);
        hash = 61 * hash + Objects.hashCode(this.artwork);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QualificationEntity other = (QualificationEntity) obj;
        if (this.qualification != other.qualification) {
            return false;
        }
        if (!Objects.equals(this.userClient, other.userClient)) {
            return false;
        }
        if (!Objects.equals(this.artwork, other.artwork)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QualificationEntity{" + "qualification=" + qualification + ", userClient=" + userClient + ", artwork=" + artwork + '}';
    }

  
}
