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

import co.edu.uniandes.csw.artwork.ejbs.PaymentLogic;
import co.edu.uniandes.csw.artwork.api.IPaymentLogic;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.artwork.persistence.PaymentPersistence;
import co.edu.uniandes.csw.artwork.entities.ClientEntity;
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
public class PaymentLogicTest {

    /**
     * @generated
     */
    ClientEntity fatherEntity;
    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();
    private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.logic.PaymentLogicTest");
    /**
     * @generated
     */
    @Inject
    private IPaymentLogic paymentLogic;

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
    private List<PaymentEntity> data = new ArrayList<PaymentEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaymentEntity.class.getPackage())
                .addPackage(PaymentLogic.class.getPackage())
                .addPackage(IPaymentLogic.class.getPackage())
                .addPackage(PaymentPersistence.class.getPackage())
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
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from PaymentEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        fatherEntity = factory.manufacturePojo(ClientEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            PaymentEntity entity = factory.manufacturePojo(PaymentEntity.class);
            entity.setClient(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Payment
     *
     * @generated
     */
    @Test
    public void createPaymentTest() {
        PaymentEntity newEntity = factory.manufacturePojo(PaymentEntity.class);
        PaymentEntity result = paymentLogic.createPayment(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        PaymentEntity entity = em.find(PaymentEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Payments
     *
     * @generated
     */
    @Test
    public void getPaymentsTest() {
        List<PaymentEntity> list = paymentLogic.getPayments(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PaymentEntity entity : list) {
            boolean found = false;
            for (PaymentEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Payment
     *
     * @generated
     */
    @Test
    public void getPaymentTest() {
        PaymentEntity entity = data.get(0);
        PaymentEntity resultEntity = paymentLogic.getPayment(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Payment
     *
     * @generated
     */
    @Test
    public void deletePaymentTest() {
        PaymentEntity entity = data.get(1);
        paymentLogic.deletePayment(entity.getId());
        PaymentEntity deleted = em.find(PaymentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Payment
     *
     * @generated
     */
    @Test
    public void updatePaymentTest() {
        PaymentEntity entity = data.get(0);
        PaymentEntity pojoEntity = factory.manufacturePojo(PaymentEntity.class);

        pojoEntity.setId(entity.getId());

        paymentLogic.updatePayment(fatherEntity.getId(), pojoEntity);

        PaymentEntity resp = em.find(PaymentEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}

