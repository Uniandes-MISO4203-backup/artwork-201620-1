
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.test.persistence;

import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.persistence.ArtworkPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class ArtworkPersistenceTest {
 @Inject
 private ArtworkPersistence artworkPersistence;
 
 @PersistenceContext
 private EntityManager em;
 
 @Inject
 UserTransaction utx;
 
  ArtistEntity fatherEntity;
 
 private List<ArtworkEntity> data = new ArrayList<>();
   private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.persistence.ArtworkPersistenceTest");
   
 
 @Deployment
 public static JavaArchive createDeployment() {
  return ShrinkWrap.create(JavaArchive.class)
          .addPackage(ArtworkEntity.class.getPackage())
          .addPackage(ArtworkPersistence.class.getPackage())
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
  LOGGER.log(Level.SEVERE, e.getMessage(), e);
   try {
    utx.rollback(); 
   }catch (Exception e1) {
     LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
   } 
  }
 }
 private void clearData() {
  em.createQuery("delete from ArtworkEntity").executeUpdate();
  em.createQuery("delete from ArtistEntity").executeUpdate();
 }
 private void insertData() {
  PodamFactory factory = new PodamFactoryImpl();
  fatherEntity = factory.manufacturePojo(ArtistEntity.class);
  fatherEntity.setId(1L);
  em.persist(fatherEntity);
  for (int i = 0; i < 3; i++) {
   ArtworkEntity entity = factory.manufacturePojo(ArtworkEntity.class);
   entity.setArtist(fatherEntity);
   em.persist(entity);
   data.add(entity);
  }
 }
 @Test
 public void createArtworkTest(){
  PodamFactory factory = new PodamFactoryImpl();
  ArtworkEntity newEntity = factory.manufacturePojo(ArtworkEntity.class);
  newEntity.setArtist(fatherEntity);
  ArtworkEntity result = artworkPersistence.create(newEntity);
  Assert.assertNotNull(result);
  ArtworkEntity entity = em.find(ArtworkEntity.class, result.getId());
  Assert.assertEquals(newEntity.getName(), entity.getName());
  Assert.assertEquals(newEntity.getImage(), entity.getImage());
  Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
  
 }
 @Test
 public void getArtworksTest() {
  List<ArtworkEntity> list = artworkPersistence.findAll(null, null, fatherEntity.getId());
  Assert.assertEquals(data.size(), list.size());
  for (ArtworkEntity ent : list) {
   boolean found = false;
   for (ArtworkEntity entity : data) {
    if (ent.getId().equals(entity.getId())) {
     found = true; 
    }
   }
   Assert.assertTrue(found);
  }
 }
 @Test
 public void getArtworkTest() {
  ArtworkEntity entity = data.get(0);
  ArtworkEntity newEntity = artworkPersistence.find(entity.getArtist().getId(), entity.getId());
  Assert.assertNotNull(newEntity);
  Assert.assertEquals(entity.getName(), newEntity.getName());
  Assert.assertEquals(entity.getImage(), newEntity.getImage());
  Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
 }
 
 @Test
 public void deleteArtworkTest() {
  ArtworkEntity entity = data.get(0);
  artworkPersistence.delete(entity.getId());
  ArtworkEntity deleted = em.find(ArtworkEntity.class, entity.getId());
  Assert.assertNull(deleted);
 }
 @Test
 public void updateArtworkTest() {
  ArtworkEntity entity = data.get(0);
  PodamFactory factory = new PodamFactoryImpl();
  ArtworkEntity newEntity = factory.manufacturePojo(ArtworkEntity.class);
  newEntity.setId(entity.getId());
  artworkPersistence.update(newEntity);
  ArtworkEntity resp = em.find(ArtworkEntity.class, entity.getId());
  Assert.assertEquals(newEntity.getName(), resp.getName());
  Assert.assertEquals(newEntity.getImage(), resp.getImage());
  Assert.assertEquals(newEntity.getPrice(), resp.getPrice());
 }
}
