package co.edu.uniandes.csw.artwork.test.persistence;

import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import co.edu.uniandes.csw.artwork.persistence.CommentPersistence;
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
public class CommentPersistenceTest {
 
 ArtworkEntity fatherEntity;
 
 @Inject
 private CommentPersistence commentPersistence;
 
 @PersistenceContext
 private EntityManager em;
 
 @Inject
 UserTransaction utx;
 
 private List<CommentEntity> data = new ArrayList<>();
 
 @Deployment
 public static JavaArchive createDeployment() {
  return ShrinkWrap.create(JavaArchive.class)
          .addPackage(CommentEntity.class.getPackage())
          .addPackage(CommentPersistence.class.getPackage())
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
  em.createQuery("delete from ArtworkEntity").executeUpdate();
  em.createQuery("delete from CommentEntity").executeUpdate();
 }
 private void insertData() {
  PodamFactory factory = new PodamFactoryImpl();
  fatherEntity = factory.manufacturePojo(ArtworkEntity.class);
  fatherEntity.setId(1L);
  em.persist(fatherEntity);
  for (int i = 0; i < 3; i++) {
   CommentEntity entity = factory.manufacturePojo(CommentEntity.class);
   entity.setArtwork(fatherEntity);
   em.persist(entity);
   data.add(entity); 
  }
 }
 
 @Test
 public void createArtworkTest() {
  PodamFactory factory = new PodamFactoryImpl();
  CommentEntity newEntity = factory.manufacturePojo(CommentEntity.class);
  newEntity.setArtwork(fatherEntity);
  CommentEntity result = commentPersistence.create(newEntity);
  Assert.assertNotNull(result);
  CommentEntity entity = em.find(CommentEntity.class, result.getId());
  Assert.assertEquals(newEntity.getName(), entity.getName());
  Assert.assertEquals(newEntity.getComment(), entity.getComment());
 }
 
 @Test
 public void getArtworksTest() {
  List<CommentEntity> list = commentPersistence.findAll(fatherEntity.getId());
  Assert.assertEquals(data.size(), list.size());
  for (CommentEntity ent : list) {
   boolean found = false;
   for (CommentEntity entity : data) {
    if (ent.getId().equals(entity.getId())) {
     found = true;
    }
   }
   Assert.assertTrue(found);
  }
 }
 
}