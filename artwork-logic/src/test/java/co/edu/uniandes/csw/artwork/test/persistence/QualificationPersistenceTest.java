/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.test.persistence;

/**
 *
 * @author a.quintero10
*/
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.artwork.persistence.QualificationPersistence;
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
public class QualificationPersistenceTest{
ArtworkEntity fatherEntity;
@Inject
private QualificationPersistence qualificationPersistence;

@PersistenceContext
private EntityManager em;

@Inject
UserTransaction utx;

private List<QualificationEntity> data = new ArrayList<>();
private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.persistence.QualificationPersistenceTest");


@Deployment
public static JavaArchive createDeployment(){
return ShrinkWrap.create(JavaArchive.class)
        .addPackage(QualificationEntity.class.getPackage())
        .addPackage(QualificationPersistence.class.getPackage())
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource("META-INF/beans.xml", "beans.xml");

}
@Before
public void configTest(){
try{
utx.begin();
em.joinTransaction();
clearData();
insertData();
utx.commit();
}catch (Exception e ){
 LOGGER.log(Level.SEVERE, e.getMessage(), e);
try{
utx.rollback();
}catch (Exception e1){
 LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
}
}
}
private void clearData(){
em.createQuery("delete from QualificationEntity").executeUpdate();
em.createQuery("delete from ArtworkEntity").executeUpdate();
}
private void insertData(){
PodamFactory factory = new PodamFactoryImpl();
fatherEntity = factory.manufacturePojo(ArtworkEntity.class);
fatherEntity.setId(1L);
em.persist(fatherEntity);
for(int i=0; i < 3; i++){
QualificationEntity entity = factory.manufacturePojo(QualificationEntity.class);
entity.setArtwork(fatherEntity);
em.persist(entity);
data.add(entity);
}
}
@Test
public void createItemTest(){
PodamFactory factory = new PodamFactoryImpl();
QualificationEntity newEntity = factory.manufacturePojo(QualificationEntity.class);
newEntity.setArtwork(fatherEntity);
QualificationEntity result = qualificationPersistence.create(newEntity);
Assert.assertNotNull(result);
QualificationEntity entity = em.find(QualificationEntity.class,result.getId());
Assert.assertEquals(newEntity.getName(), entity.getName());
Assert.assertEquals(newEntity.getQualification(),entity.getQualification());
}
@Test
public void getItemsTest(){
List<QualificationEntity> list = qualificationPersistence.findAll(null,null, fatherEntity.getId());
Assert.assertEquals(data.size(),list.size());
for(QualificationEntity ent: list){
boolean found = false;
for(QualificationEntity entity : data){
if(ent.getId().equals(entity.getId())){
found = true;
}
}
Assert.assertTrue(found);
}
}
@Test
public void getItemTest(){
QualificationEntity entity= data.get(0);
QualificationEntity newEntity= qualificationPersistence.find(entity.getId());
Assert.assertNotNull(newEntity);
Assert.assertEquals(entity.getName(), newEntity.getName());
Assert.assertEquals(entity.getQualification(), newEntity.getQualification());

}
@Test
public void deleteItemTest(){
QualificationEntity entity = data.get(0);
qualificationPersistence.delete(entity.getId());

}
@Test
public void updateItemTest(){
QualificationEntity entity = data.get(0);
PodamFactory factory = new PodamFactoryImpl();
QualificationEntity newEntity = factory.manufacturePojo(QualificationEntity.class);
newEntity.setId(entity.getId());
qualificationPersistence.update(newEntity);
QualificationEntity resp = em.find(QualificationEntity.class,entity.getId());
Assert.assertEquals(newEntity.getName(), resp.getName());
Assert.assertEquals(newEntity.getQualification(), resp.getQualification());
}
}
