/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.quintero10
 */
@Entity

public class PrizeEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Color")
    private String color;

    @Column(name = "Trophy")
    private String trophy;
    
    @PodamExclude
    @Temporal(TemporalType.DATE)
    private Date date;

    @PodamExclude
    @ManyToOne
    private ArtworkEntity artwork;
   
    public PrizeEntity() {
     // constructor de prize
    }

    public PrizeEntity(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
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

    /**
     * @return the artworks
     */
    public ArtworkEntity getArtwork() {
        return artwork;
    }

    /**
     * @param artwork
     */
    public void setArtwork(ArtworkEntity artwork) {
        this.artwork = artwork;
    }
    
     @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrizeEntity)) {
            return false;
        }
        PrizeEntity other = (PrizeEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

 @Override
 public int hashCode() {
  int hash = 7;
  hash = 31 * hash + Objects.hashCode(this.id);
  return hash;
 }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.artwork.entities.PrizeEntity[ id=" + id + " ]";
    }
}
