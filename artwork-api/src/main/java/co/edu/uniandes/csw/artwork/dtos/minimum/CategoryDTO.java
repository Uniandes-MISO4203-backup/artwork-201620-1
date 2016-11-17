/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import co.edu.uniandes.csw.artwork.entities.CategoryEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class CategoryDTO implements Serializable {

    private Long idCategory;
    private String nameCategory;

    public CategoryDTO() {
        //Constructor clase
    }

    public CategoryDTO(CategoryEntity entity) {
        if (entity != null) {
            this.idCategory = entity.getId();
            this.nameCategory = entity.getName();
        }
    }

    public CategoryEntity toEntity() {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(this.getIdCategory());
        entity.setName(this.getNameCategory());
        return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getIdCategory() {
        return idCategory;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setIdCategory(Long id) {
        this.idCategory = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setNameCategory(String name) {
        this.nameCategory = name;
    }

}
