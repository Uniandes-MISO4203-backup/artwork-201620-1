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

    private Long idArtworkDTO;
    private String nameArtworkDTO;
    private String imageArtworkDTO;
    private Long priceArtworkDTO;
    private Integer widthArtworkDTO;
    private Integer heightArtworkDTO;
    private Date dateAddedArtworkDTO;
    
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
            this.idArtworkDTO = entity.getId();
            this.nameArtworkDTO = entity.getName();
            this.imageArtworkDTO = entity.getImage();
            this.priceArtworkDTO = entity.getPrice();
            this.widthArtworkDTO = entity.getWidth();
            this.heightArtworkDTO = entity.getHeight();
            this.dateAddedArtworkDTO = entity.getDateAdded();
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
        entity.setId(this.getIdArtworkDTO());
        entity.setName(this.getNameArtworkDTO());
        entity.setImage(this.getImageArtworkDTO());
        entity.setPrice(this.getPriceArtworkDTO());
        entity.setWidth(this.getWidthArtworkDTO());
        entity.setHeight(this.getHeightArtworkDTO());
        entity.setDateAdded(this.getDateAddedArtworkDTO());
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
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
   

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
   

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
   

 /**
  * @return the idArtworkDTO
  */
 public Long getIdArtworkDTO() {
  return idArtworkDTO;
 }

 /**
  * @param idArtworkDTO the idArtworkDTO to set
  */
 public void setIdArtworkDTO(Long idArtworkDTO) {
  this.idArtworkDTO = idArtworkDTO;
 }

 /**
  * @return the nameArtworkDTO
  */
 public String getNameArtworkDTO() {
  return nameArtworkDTO;
 }

 /**
  * @param nameArtworkDTO the nameArtworkDTO to set
  */
 public void setNameArtworkDTO(String nameArtworkDTO) {
  this.nameArtworkDTO = nameArtworkDTO;
 }

 /**
  * @return the imageArtworkDTO
  */
 public String getImageArtworkDTO() {
  return imageArtworkDTO;
 }

 /**
  * @param imageArtworkDTO the imageArtworkDTO to set
  */
 public void setImageArtworkDTO(String imageArtworkDTO) {
  this.imageArtworkDTO = imageArtworkDTO;
 }

 /**
  * @return the priceArtworkDTO
  */
 public Long getPriceArtworkDTO() {
  return priceArtworkDTO;
 }

 /**
  * @param priceArtworkDTO the priceArtworkDTO to set
  */
 public void setPriceArtworkDTO(Long priceArtworkDTO) {
  this.priceArtworkDTO = priceArtworkDTO;
 }

 /**
  * @return the widthArtworkDTO
  */
 public Integer getWidthArtworkDTO() {
  return widthArtworkDTO;
 }

 /**
  * @param widthArtworkDTO the widthArtworkDTO to set
  */
 public void setWidthArtworkDTO(Integer widthArtworkDTO) {
  this.widthArtworkDTO = widthArtworkDTO;
 }

 /**
  * @return the heightArtworkDTO
  */
 public Integer getHeightArtworkDTO() {
  return heightArtworkDTO;
 }

 /**
  * @param heightArtworkDTO the heightArtworkDTO to set
  */
 public void setHeightArtworkDTO(Integer heightArtworkDTO) {
  this.heightArtworkDTO = heightArtworkDTO;
 }

 /**
  * @return the dateAddedArtworkDTO
  */
 public Date getDateAddedArtworkDTO() {
  return dateAddedArtworkDTO;
 }

 /**
  * @param dateAddedArtworkDTO the dateAddedArtworkDTO to set
  */
 public void setDateAddedArtworkDTO(Date dateAddedArtworkDTO) {
  this.dateAddedArtworkDTO = dateAddedArtworkDTO;
 }

}
