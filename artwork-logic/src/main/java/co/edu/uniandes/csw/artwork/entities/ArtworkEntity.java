/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ArtworkEntity extends BaseEntity implements Serializable {

    private String image;
    private Long price;
    private Integer width;
    private Integer height;

    @Temporal(TemporalType.DATE)
    private Date dateAdded;

    @PodamExclude
    @OneToMany
    private List<CategoryEntity> category = new ArrayList<>();

    @PodamExclude
    @ManyToOne
    private ArtistEntity artist;

    @PodamExclude
    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QualificationEntity> qualifications = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "artwork")
    private List<CommentEntity> comments = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "artwork")
    private List<PrizeEntity> prizes = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "artwork")
    private List<ItemEntity> items = new ArrayList<>();

    @ElementCollection
    private List<String> images;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<QualificationEntity> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationEntity> qualifications) {
        this.qualifications = qualifications;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @return the prizes
     */
    public List<PrizeEntity> getPrizes() {
        return prizes;
    }

    /**
     * @param prizes the prizes to set
     */
    public void setPrizes(List<PrizeEntity> prizes) {
        this.prizes = prizes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.image);
        hash = 83 * hash + Objects.hashCode(this.price);
        hash = 83 * hash + Objects.hashCode(this.width);
        hash = 83 * hash + Objects.hashCode(this.height);
        hash = 83 * hash + Objects.hashCode(this.dateAdded);
        hash = 83 * hash + Objects.hashCode(this.category);
        hash = 83 * hash + Objects.hashCode(this.artist);
        hash = 83 * hash + Objects.hashCode(this.qualifications);
        hash = 83 * hash + Objects.hashCode(this.comments);
        hash = 83 * hash + Objects.hashCode(this.prizes);
        hash = 83 * hash + Objects.hashCode(this.items);
        hash = 83 * hash + Objects.hashCode(this.images);
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
        final ArtworkEntity other = (ArtworkEntity) obj;
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.width, other.width)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.qualifications, other.qualifications)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.prizes, other.prizes)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        if (!Objects.equals(this.images, other.images)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArtworkEntity{" + "image=" + image + ", price=" + price + ", width=" + width + ", height=" + height + ", dateAdded=" + dateAdded + ", category=" + category + ", artist=" + artist + ", qualifications=" + qualifications + ", comments=" + comments + ", prizes=" + prizes + ", items=" + items + ", images=" + images + '}';
    }
}
