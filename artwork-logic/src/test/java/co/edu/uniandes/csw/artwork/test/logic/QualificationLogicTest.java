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
package co.edu.uniandes.csw.artwork.test.logic;

import co.edu.uniandes.csw.artwork.api.IQualificationLogic;
import co.edu.uniandes.csw.artwork.ejbs.QualificationLogic;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.artwork.persistence.QualificationPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class QualificationLogicTest {

    /**
     * @generated
     */
    ArtworkEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();
     private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.logic.QualificationLogicTest");
   
    /**
     * @generated
     */
    @Inject
    private IQualificationLogic qualificationLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<QualificationEntity> data = new ArrayList<QualificationEntity>();

    /**
     * @generated

    /**
     * @generated
     */
    private List<ArtworkEntity> artworkData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QualificationEntity.class.getPackage())
                .addPackage(QualificationLogic.class.getPackage())
                .addPackage(IQualificationLogic.class.getPackage())
                .addPackage(QualificationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            try {
                utx.rollback();
            } catch (Exception e1) {
                 LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from QualificationEntity").executeUpdate();
        em.createQuery("delete from ArtworkEntity").executeUpdate();
        
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        
        fatherEntity = factory.manufacturePojo(ArtworkEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            QualificationEntity entity = factory.manufacturePojo(QualificationEntity.class);
            entity.setArtwork(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
   /**
     * Prueba para crear un Artwork
     *
     * @generated
     */
    @Test
    public void createQualificationTest() {
        QualificationEntity newEntity = factory.manufacturePojo(QualificationEntity.class);
        QualificationEntity result = qualificationLogic.createQualification(newEntity);
        Assert.assertNotNull(result);
        QualificationEntity entity = em.find(QualificationEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getQualification(), entity.getQualification());
        
    }

    /**
     * Prueba para consultar la lista de Artworks
     *
     * @generated
     */
    @Test
    public void getQualificationsTest() {
        List<QualificationEntity> list = qualificationLogic.getQualifications();
        Assert.assertEquals(data.size(), list.size());
        for (QualificationEntity entity : list) {
            boolean found = false;
            for (QualificationEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getQualificationsArtworkTest(){
        List<QualificationEntity> list = qualificationLogic.getQualificationsArtwork(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (QualificationEntity entity : list) {
            boolean found = false;
            for (QualificationEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getQualificationsPaginatedTest(){
        List<QualificationEntity> list = qualificationLogic.getQualifications(1,1000);
        Assert.assertEquals(data.size(), list.size());
    }
    
    @Test
    public void getQualificationTest(){
        QualificationEntity entity = data.get(0);
        QualificationEntity resultEntity = qualificationLogic.getQualification(data.get(0).getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getQualification(), resultEntity.getQualification());
    }
    
    @Test
    public void createQualificationAegumentsTest(){        
        String userClient = "user";
        Integer qualification = new Integer(4);
        QualificationEntity newEntity = qualificationLogic.createQualification(fatherEntity,userClient,qualification);
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getArtwork().getId(), fatherEntity.getId());
        Assert.assertEquals(newEntity.getUserClient(), userClient);
        Assert.assertEquals(newEntity.getQualification(), qualification.intValue());
    }
    
    @Test
    public void updateQualification(){
        QualificationEntity entity = data.get(0);
        QualificationEntity pojoEntity = factory.manufacturePojo(QualificationEntity.class);

        pojoEntity.setId(entity.getId());

        qualificationLogic.updateQualification(pojoEntity);

        QualificationEntity resp = em.find(QualificationEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getQualification(), resp.getQualification());
    }
    
    @Test
    public void deleteQualificationTest() {
        QualificationEntity entity = data.get(1);
        qualificationLogic.deleteQualification(entity.getId());
        QualificationEntity deleted = em.find(QualificationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
   
}

