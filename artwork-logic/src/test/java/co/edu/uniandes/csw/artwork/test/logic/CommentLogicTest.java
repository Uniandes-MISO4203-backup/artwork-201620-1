package co.edu.uniandes.csw.artwork.test.logic;

import co.edu.uniandes.csw.artwork.api.ICommentLogic;
import co.edu.uniandes.csw.artwork.ejbs.CommentLogic;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import co.edu.uniandes.csw.artwork.persistence.CommentPersistence;
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
public class CommentLogicTest {

    /**
     * @generated
     */
    ArtworkEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();
    private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.logic.CommentLogicTest");
    /**
     * @generated
     */
    @Inject
    private ICommentLogic commentLogic;

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
    private List<CommentEntity> data = new ArrayList<CommentEntity>();

    /**
     * @generated
    /**
     * @generated
     */
    

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CommentEntity.class.getPackage())
                .addPackage(CommentLogic.class.getPackage())
                .addPackage(ICommentLogic.class.getPackage())
                .addPackage(CommentPersistence.class.getPackage())
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
        em.createQuery("delete from CommentEntity").executeUpdate();
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
            CommentEntity entity = factory.manufacturePojo(CommentEntity.class);
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
    public void countCommentsTest(){
    Assert.assertEquals(data.size(),commentLogic.countComments());
    }
    @Test
    public void commentEqualsTest(){
      ArtworkEntity art = factory.manufacturePojo(ArtworkEntity.class);
      Object obj=new java.lang.Object();
     CommentEntity newEntity = factory.manufacturePojo(CommentEntity.class);
        CommentEntity result = commentLogic.createComment(fatherEntity.getId(), newEntity);
    Assert.assertTrue(newEntity.equals(result));
    Assert.assertFalse(newEntity.equals(null));
    Assert.assertFalse(newEntity.equals(art));
   Assert.assertFalse(newEntity.equals(obj));
    
    }
    @Test
    public void createCommentTest() {
        CommentEntity newEntity = factory.manufacturePojo(CommentEntity.class);
        CommentEntity result = commentLogic.createComment(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        CommentEntity entity = em.find(CommentEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getComment(), entity.getComment());
        Assert.assertEquals(newEntity.getArtwork().getId(), entity.getArtwork().getId());
        Assert.assertNotNull(newEntity.hashCode());
        Assert.assertNotNull(newEntity.toString());
        
    }

    /**
     * Prueba para consultar la lista de Artworks
     *
     * @generated
     */
    @Test
    public void getCommentsTest() {
        List<CommentEntity> list = commentLogic.getComments(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (CommentEntity entity : list) {
            boolean found = false;
            for (CommentEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

   
}