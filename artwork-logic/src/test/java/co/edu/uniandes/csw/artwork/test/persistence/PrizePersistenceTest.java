/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.test.persistence;

import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.PrizeEntity;
import co.edu.uniandes.csw.artwork.persistence.ArtworkPersistence;
import co.edu.uniandes.csw.artwork.persistence.PrizePersistence;
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

@RunWith(Arquillian.class)
public class PrizePersistenceTest {
 @Inject
 private PrizePersistence prizePersistence;
 
 @PersistenceContext
 private EntityManager em;
 
 @Inject
 UserTransaction utx;
 
  ArtworkEntity fatherEntity;
 
 private List<PrizeEntity> data = new ArrayList<>();
 
 @Deployment
 public static JavaArchive createDeployment() {
  return ShrinkWrap.create(JavaArchive.class)
          .addPackage(PrizeEntity.class.getPackage())
          .addPackage(PrizePersistence.class.getPackage())
          .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
          .addAsManifestResource("META-INF/beans.xml", "beans.xml");
           
 }

 
 @Before
 public void configTest() {
  try {
   utx.begin();
   em.joinTransaction();
   clearData();
   insertData();
   utx.commit(); 
  }catch (Exception e) {
   e.printStackTrace();
   try {
    utx.rollback(); 
   }catch (Exception e1) {
    e1.printStackTrace();
   } 
  }
 }
 private void clearData() {
  em.createQuery("delete from PrizeEntity").executeUpdate();
  em.createQuery("delete from ArtworkEntity").executeUpdate();
 }
 private void insertData() {
  PodamFactory factory = new PodamFactoryImpl();
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
 public void createPrizeTest(){
  PodamFactory factory = new PodamFactoryImpl();
  PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
  newEntity.setArtwork(fatherEntity);
  PrizeEntity result = prizePersistence.create(newEntity);
  Assert.assertNotNull(result);
  PrizeEntity entity = em.find(PrizeEntity.class, result.getId());
  Assert.assertEquals(newEntity.getName(), entity.getName());
  Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
  Assert.assertEquals(newEntity.getColor(), entity.getColor());
  Assert.assertEquals(newEntity.getTrophy(), entity.getTrophy());
 
  
 }
 @Test
 public void getPrizesTest() {
  List<PrizeEntity> list = prizePersistence.findAll();
  Assert.assertEquals(data.size(), list.size());
  for (PrizeEntity ent : list) {
   boolean found = false;
   for (PrizeEntity entity : data) {
    if (ent.getId().equals(entity.getId())) {
     found = true; 
    }
   }
   Assert.assertTrue(found);
  }
 }
 @Test
 public void getPrizeTest() {
  PrizeEntity entity = data.get(0);
  PrizeEntity newEntity = prizePersistence.find(entity.getId());
  Assert.assertNotNull(newEntity);
  Assert.assertEquals(entity.getName(), newEntity.getName());
   Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
  Assert.assertEquals(newEntity.getColor(), entity.getColor());
  Assert.assertEquals(newEntity.getTrophy(), entity.getTrophy());
  
 }
 
 

}
