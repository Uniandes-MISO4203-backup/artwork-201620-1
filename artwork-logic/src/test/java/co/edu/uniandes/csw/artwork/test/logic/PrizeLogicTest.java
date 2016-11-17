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

import co.edu.uniandes.csw.artwork.api.IPrizeLogic;
import co.edu.uniandes.csw.artwork.ejbs.PrizeLogic;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.PrizeEntity;
import co.edu.uniandes.csw.artwork.persistence.PrizePersistence;
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



@RunWith(Arquillian.class)
public class PrizeLogicTest {

  
    ArtworkEntity fatherEntity;

    
    private PodamFactory factory = new PodamFactoryImpl();
    private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.logic.PrizeLogicTest");
   
    @Inject
    private IPrizeLogic prizeLogic;

    
    @PersistenceContext
    private EntityManager em;

    
    @Inject
    private UserTransaction utx;

   
    private List<PrizeEntity> data = new ArrayList<PrizeEntity>();
  
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrizeEntity.class.getPackage())
                .addPackage(PrizeLogic.class.getPackage())
                .addPackage(IPrizeLogic.class.getPackage())
                .addPackage(PrizePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    
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

   
    private void clearData() {
        em.createQuery("delete from PrizeEntity").executeUpdate();
       
        em.createQuery("delete from ArtworkEntity").executeUpdate();
    }

 
    private void insertData() {
        

        fatherEntity = factory.manufacturePojo(ArtworkEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            PrizeEntity entity = factory.manufacturePojo(PrizeEntity.class);
            entity.setArtwork(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
  
     @Test
    public void updatePrizeTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity pojoEntity = factory.manufacturePojo(PrizeEntity.class);

        pojoEntity.setId(entity.getId());

        prizeLogic.updatePrize( pojoEntity);

        PrizeEntity resp = em.find(PrizeEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getColor(), resp.getColor());
        Assert.assertEquals(pojoEntity.getTrophy(), resp.getTrophy());
       
    }
   
   
    @Test
    public void getPrizesTest() {
        List<PrizeEntity> list = prizeLogic.getPrizes();
        Assert.assertEquals(data.size(), list.size());
        for (PrizeEntity entity : list) {
            boolean found = false;
            for (PrizeEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
   
    @Test
    public void getPrizeTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity resultEntity = prizeLogic.getPrize(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(resultEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(resultEntity.getColor(), entity.getColor());
        Assert.assertEquals(resultEntity.getTrophy(), entity.getTrophy());
    }

  @Test
    public void createPrizeTest() {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        PrizeEntity result = prizeLogic.createPrize(newEntity);
        Assert.assertNotNull(result);
        PrizeEntity entity = em.find(PrizeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getTrophy(), entity.getTrophy());
        
    }
    
}
