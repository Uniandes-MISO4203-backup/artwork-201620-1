/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.test.persistence;

import co.edu.uniandes.csw.artwork.entities.ClientEntity;
import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import co.edu.uniandes.csw.artwork.persistence.ItemPersistence;
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
public class ItemPersistenceTest{
ClientEntity fatherEntity;

@Inject
private ItemPersistence itemPersistence;

@PersistenceContext
private EntityManager em;

@Inject
UserTransaction utx;

private List<ItemEntity> data = new ArrayList<>();

@Deployment
public static JavaArchive createDeployment(){
return ShrinkWrap.create(JavaArchive.class)
       .addPackage(ItemEntity.class.getPackage())
       .addPackage(ItemPersistence.class.getPackage())
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
} catch (Exception e){
e.printStackTrace();
try{
utx.rollback();
}catch(Exception e1){
 e1.printStackTrace();
}
}
}
private void clearData(){
em.createQuery("delete from ItemEntity").executeUpdate();
em.createQuery("delete from ClientEntity").executeUpdate();
}
private void insertData(){
PodamFactory factory = new PodamFactoryImpl();
fatherEntity = factory.manufacturePojo(ClientEntity.class);
fatherEntity.setId(1L);
em.persist(fatherEntity);
for(int i = 0; i < 3; i++){
ItemEntity entity = factory.manufacturePojo(ItemEntity.class);
entity.setShoppingCart(false);
entity.setClient(fatherEntity);
em.persist(entity);
data.add(entity);
}
}
@Test
public void createItemTest(){
PodamFactory factory =new PodamFactoryImpl();
ItemEntity newEntity = factory.manufacturePojo(ItemEntity.class);
newEntity.setClient(fatherEntity);
ItemEntity result = itemPersistence.create(newEntity);
Assert.assertNotNull(result);
ItemEntity entity = em.find(ItemEntity.class, result.getId());
Assert.assertEquals(newEntity.getName(), entity.getName());
Assert.assertEquals(newEntity.getQty(),entity.getQty());
}
@Test
public void getItemsTest(){
List<ItemEntity> list = itemPersistence.findAll(null, null, fatherEntity.getId());
Assert.assertEquals(data.size(),list.size());
for(ItemEntity ent : list){
 boolean found = false;
 for(ItemEntity entity : data){
 if(ent.getId().equals(entity.getId())){
 found = true;
 }
 }
 Assert.assertTrue(found);
}
}
@Test
public void getItemTest(){
ItemEntity entity = data.get(0);
ItemEntity newEntity = itemPersistence.find(entity.getClient().getId(), entity.getId());
Assert.assertNotNull(newEntity);
Assert.assertEquals(entity.getName(), newEntity.getName());
Assert.assertEquals(entity.getQty(), newEntity.getQty());
}

@Test
public void deleteItemTest(){
ItemEntity entity = data.get(0);
itemPersistence.delete(entity.getId());
ItemEntity deleted = em.find(ItemEntity.class,entity.getId());
Assert.assertNull(deleted);
}
@Test
public void updateItemTest(){
ItemEntity entity = data.get(0);
PodamFactory factory = new PodamFactoryImpl();
ItemEntity newEntity = factory.manufacturePojo(ItemEntity.class);
newEntity.setId(entity.getId());
itemPersistence.update(newEntity);

ItemEntity resp = em.find(ItemEntity.class, entity.getId());
Assert.assertEquals(newEntity.getName(), resp.getName());
Assert.assertEquals(newEntity.getQty(), resp.getQty());

}
}
