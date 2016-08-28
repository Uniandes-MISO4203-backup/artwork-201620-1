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
package co.edu.uniandes.csw.artwork.ejbs;

import co.edu.uniandes.csw.artwork.api.IQualificationLogic;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.artwork.persistence.QualificationPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class QualificationLogic implements IQualificationLogic {

    @Inject private QualificationPersistence persistence;


    /**
     * Obtiene el número de registros de Qualification.
     *
     * @return Número de registros de Qualification.
     * @generated
     */
    @Override
    public int countQualifications() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Qualification.
     *
     * @return Colección de objetos de QualificationEntity.
     * @generated
     */
    @Override
    public List<QualificationEntity> getQualifications() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Qualification indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de QualificationEntity.
     * @generated
     */
    @Override
    public List<QualificationEntity> getQualifications(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Qualification a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de QualificationEntity con los datos del Qualification consultado.
     * @generated
     */
    @Override
    public QualificationEntity getQualification(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Qualification en la base de datos.
     *
     * @param entity Objeto de QualificationEntity con los datos nuevos
     * @return Objeto de QualificationEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public QualificationEntity createQualification(QualificationEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Qualification.
     *
     * @param entity Instancia de QualificationEntity con los nuevos datos.
     * @return Instancia de QualificationEntity con los datos actualizados.
     * @generated
     */
    @Override
    public QualificationEntity updateQualification(QualificationEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Qualification de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteQualification(Long id) {
        persistence.delete(id);
    }
  
}
