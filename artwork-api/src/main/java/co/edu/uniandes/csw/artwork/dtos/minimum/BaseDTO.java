/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.minimum;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ed.munoz
 */
@XmlRootElement
public class BaseDTO {
    
    protected Long id;
    protected String name;
    
    public BaseDTO(){
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
}
