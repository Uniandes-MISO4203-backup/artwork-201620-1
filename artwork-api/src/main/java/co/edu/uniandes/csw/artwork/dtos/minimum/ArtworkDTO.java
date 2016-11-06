/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
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
                for (CategoryEntity category : entity.getCategory()) {
                    CategoryDTO cat = new CategoryDTO(category);
                    categories.add(cat);
                }
                this.category = categories;
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
        entity.setDateAdded(this.getDateAdded());
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
    
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
