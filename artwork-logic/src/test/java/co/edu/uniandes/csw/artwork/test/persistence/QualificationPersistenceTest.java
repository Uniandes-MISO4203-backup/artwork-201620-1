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
package co.edu.uniandes.csw.artwork.test.persistence;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.artwork.persistence.QualificationPersistence;
import java.util.ArrayList;
import java.util.List;

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
public class QualificationPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QualificationEntity.class.getPackage())
                .addPackage(QualificationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    ArtworkEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private QualificationPersistence qualificationPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
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
     * @generated
     */
    private List<QualificationEntity> data = new ArrayList<QualificationEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
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
     * Prueba para crear un Item.
     *
     * @generated
     */
    @Test
    public void createItemTest() {
		PodamFactory factory = new PodamFactoryImpl();
        QualificationEntity newEntity = factory.manufacturePojo(QualificationEntity.class);
        newEntity.setArtwork(fatherEntity);
        QualificationEntity result = qualificationPersistence.create(newEntity);

        Assert.assertNotNull(result);

        QualificationEntity entity = em.find(QualificationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getQualification(), entity.getQualification());
    }

    /**
     * Prueba para consultar la lista de Items.
     *
     * @generated
     */
    @Test
    public void getItemsTest() {
        List<QualificationEntity> list = qualificationPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (QualificationEntity ent : list) {
            boolean found = false;
            for (QualificationEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Item.
     *
     * @generated
     */
    @Test
    public void getItemTest() {
        QualificationEntity entity = data.get(0);
        QualificationEntity newEntity = qualificationPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getQualification(), newEntity.getQualification());
    }

    /**
     * Prueba para eliminar un Item.
     *
     * @generated
     */
    @Test
    public void deleteItemTest() {
        QualificationEntity entity = data.get(0);
        qualificationPersistence.delete(entity.getId());
        QualificationEntity deleted = em.find(QualificationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Item.
     *
     * @generated
     */
    @Test
    public void updateItemTest() {
        QualificationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        QualificationEntity newEntity = factory.manufacturePojo(QualificationEntity.class);

        newEntity.setId(entity.getId());

        qualificationPersistence.update(newEntity);

        QualificationEntity resp = em.find(QualificationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getQualification(), resp.getQualification());
    }
}
