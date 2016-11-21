/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import co.edu.uniandes.csw.artwork.entities.CategoryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class ArtworkDTO implements Serializable {

    private Long id;
    private String name;
    private String image;
    private Long price;
    private Integer width;
    private Integer height;
    private Date dateAdded;
    
    @PodamExclude
    private List<String> images;
    
    private List<ItemDTO> items = new ArrayList<>();

    @PodamExclude
    private List<QualificationDTO> qualifications = new ArrayList<>();
    
    @PodamExclude
    private List<CategoryDTO> category;

    public ArtworkDTO() {
        //Constructor clase
    }

    public ArtworkDTO(ArtworkEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.image = entity.getImage();
            this.price = entity.getPrice();
            this.width = entity.getWidth();
            this.height = entity.getHeight();
            this.dateAdded = entity.getDateAdded();
            this.images = entity.getImages();
            if(entity.getCategory() != null){
                List<CategoryDTO> categories = new ArrayList<>();
                for (CategoryEntity categoria : entity.getCategory()) {
                    CategoryDTO cat = new CategoryDTO(categoria);
                    categories.add(cat);
                }
                this.category = categories;
            }
            
            for(ItemEntity itemEntity : entity.getItems()){
                this.items.add(new ItemDTO(itemEntity));
            }
        }
    }

    public ArtworkEntity toEntity() {
        ArtworkEntity entity = new ArtworkEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setImage(this.getImage());
        entity.setPrice(this.getPrice());
        entity.setWidth(this.getWidth());
        entity.setHeight(this.getHeight());
        entity.setDateAdded(this.getDate());
        entity.setImages(this.getImages());
        if(this.getCategory() != null){
            List<CategoryEntity> categories = new ArrayList<>();
            for(CategoryDTO cat : this.getCategory()){
                categories.add(cat.toEntity());
            }
            entity.setCategory(categories);
        }
        
        return entity;
    }

   
    
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    

    

    public List<QualificationDTO> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationDTO> qualifications) {
        this.qualifications = qualifications;
    }
    
    public List<CategoryDTO> getCategory(){
        return category;
    }
    
    public void setCategory(List<CategoryDTO> category){
        this.category = category;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
    


 /**
  * @return the idArtworkDTO
  */
 public Long getId() {
  return id;
 }

 /**
  * @param idArtworkDTO the idArtworkDTO to set
  */
 public void setId(Long id) {
  this.id = id;
 }

 /**
  * @return the nameArtworkDTO
  */
 public String getName() {
  return name;
 }

 /**
  * @param nameArtworkDTO the nameArtworkDTO to set
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * @return the imageArtworkDTO
  */
 public String getImage() {
  return image;
 }

 /**
  * @param imageArtworkDTO the imageArtworkDTO to set
  */
 public void setImage(String image) {
  this.image = image;
 }

 /**
  * @return the priceArtworkDTO
  */
 public Long getPrice() {
  return price;
 }

 /**
  * @param priceArtworkDTO the priceArtworkDTO to set
  */
 public void setPrice(Long price) {
  this.price = price;
 }

 /**
  * @return the widthArtworkDTO
  */
 public Integer getWidth() {
  return width;
 }

 /**
  * @param widthArtworkDTO the widthArtworkDTO to set
  */
 public void setWidth(Integer width) {
  this.width = width;
 }

 /**
  * @return the heightArtworkDTO
  */
 public Integer getHeight() {
  return height;
 }

 /**
  * @param heightArtworkDTO the heightArtworkDTO to set
  */
 public void setHeight(Integer height) {
  this.height = height;
 }

 /**
  * @return the dateAddedArtworkDTO
  */
 public Date getDate() {
  return dateAdded;
 }

 /**
  * @param dateAddedArtworkDTO the dateAddedArtworkDTO to set
  */
 public void setDateAdded(Date dateAdded) {
  this.dateAdded = dateAdded;
 }

}
