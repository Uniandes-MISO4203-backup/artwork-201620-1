package co.edu.uniandes.csw.artwork.test.logic;

import co.edu.uniandes.csw.artwork.ejbs.ArtworkLogic;
import co.edu.uniandes.csw.artwork.api.IArtworkLogic;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.persistence.ArtworkPersistence;
import co.edu.uniandes.csw.artwork.entities.CategoryEntity;
import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import co.edu.uniandes.csw.artwork.entities.PrizeEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
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
public class ArtworkLogicTest {

    /**
     * @generated
     */
    ArtistEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();
    private static final Logger LOGGER = Logger.getLogger("co.edu.uniandes.csw.artwork.test.logic.ArtworkLogicTest");
    /**
     * @generated
     */
    @Inject
    private IArtworkLogic artworkLogic;

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
    private List<ArtworkEntity> data = new ArrayList<ArtworkEntity>();

    /**
     * @generated
     */
    private List<CategoryEntity> categoryData = new ArrayList<>();

    private List<PrizeEntity> prizeData = new ArrayList<>();
    
    private List<CommentEntity> commentData = new ArrayList<>();
    
    private List<QualificationEntity> qualificationData = new ArrayList<>();
    
    private List<ItemEntity> itemData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArtworkEntity.class.getPackage())
                .addPackage(ArtworkLogic.class.getPackage())
                .addPackage(IArtworkLogic.class.getPackage())
                .addPackage(ArtworkPersistence.class.getPackage())
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
        em.createQuery("delete from ArtworkEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
        em.createQuery("delete from ArtistEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CategoryEntity category = factory.manufacturePojo(CategoryEntity.class);
            em.persist(category);
            categoryData.add(category);
        }

        fatherEntity = factory.manufacturePojo(ArtistEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            
            ArtworkEntity entity = factory.manufacturePojo(ArtworkEntity.class);
            entity.setArtist(fatherEntity);
            
            entity.getCategory().add(categoryData.get(0));
            entity.setComments(commentData);
            entity.setItems(itemData);
            entity.setPrizes(prizeData);
            entity.setQualifications(qualificationData);
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
    public void createArtworkTest() {
        ArtworkEntity newEntity = factory.manufacturePojo(ArtworkEntity.class);
        ArtworkEntity result = artworkLogic.createArtwork(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        ArtworkEntity entity = em.find(ArtworkEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
        Assert.assertEquals(newEntity.getHeight(), entity.getHeight());
        Assert.assertEquals(newEntity.getWidth(), entity.getWidth());
        Assert.assertEquals(newEntity.getComments().size(),entity.getComments().size());
        Assert.assertEquals(newEntity.getPrizes().size(),entity.getPrizes().size());
        Assert.assertEquals(newEntity.getQualifications().size(),entity.getQualifications().size());
        Assert.assertEquals(newEntity.getItems().size(),entity.getItems().size());
    }
   
    @Test
    public void artworkCountTest(){
    Assert.assertEquals(data.size(), artworkLogic.countArtworks());
    }
    
    @Test
    public void getArtworkByArtistUserName(){
    List<ArtworkEntity> list =artworkLogic.getArtworksByUserName(fatherEntity.getUsername());
    Assert.assertEquals(data.size(), list.size());
    for (ArtworkEntity entity : list){
    boolean found = false;
    for(ArtworkEntity storedEntity : data){
    if (entity.getArtist().getUsername().equals(storedEntity.getArtist().getUsername())){
    found = true;
    }
    }
    Assert.assertTrue(found);
    }
    }
    
    @Test
    public void getArtworksPaginatedTest(){
     int page = 1;
     int maxRecords = 3;
    List<ArtworkEntity> list = artworkLogic.getArtworks(page, maxRecords, fatherEntity.getId());
    Assert.assertEquals(data.size(), list.size());
    }
    
    
    
    /**
     * Prueba para consultar la lista de Artworks
     *
     * @generated
     */
    @Test
    public void getArtworksTest() {
        List<ArtworkEntity> list = artworkLogic.getArtworks(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ArtworkEntity entity : list) {
            boolean found = false;
            for (ArtworkEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Artwork
     *
     * @generated
     */
    @Test
    public void getArtworkTest() {
        ArtworkEntity entity = data.get(0);
        ArtworkEntity resultEntity = artworkLogic.getArtwork(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
    }

    /**
     * Prueba para eliminar un Artwork
     *
     * @generated
     */
    @Test
    public void deleteArtworkTest() {
        ArtworkEntity entity = data.get(1);
        artworkLogic.deleteArtwork(entity.getId());
        ArtworkEntity deleted = em.find(ArtworkEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Artwork
     *
     * @generated
     */
    @Test
    public void updateArtworkTest() {
        ArtworkEntity entity = data.get(0);
        ArtworkEntity pojoEntity = factory.manufacturePojo(ArtworkEntity.class);

        pojoEntity.setId(entity.getId());

        artworkLogic.updateArtwork(fatherEntity.getId(), pojoEntity);

        ArtworkEntity resp = em.find(ArtworkEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
    }

    /**
     * Prueba para obtener una instancia de Category asociada a una instancia Artwork
     *
     * @generated
     */
    @Test
    public void getCategoryTest() {
        ArtworkEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(0);
        CategoryEntity response = artworkLogic.getCategory(entity.getId(), categoryEntity.getId());

        Assert.assertEquals(categoryEntity.getId(), response.getId());
        Assert.assertEquals(categoryEntity.getName(), response.getName());
    }
   @Test
   public void getArtworkByCategoryTest(){
    int page =1;
    int maxRecords =3;
    
    CategoryEntity categoryEntity= categoryData.get(0);
    List<ArtworkEntity> response = artworkLogic.getArtworkByCategory(page, maxRecords, categoryEntity.getId());
    Assert.assertEquals(data.size(), response.size());
   }

    /**
     * Prueba para obtener una colección de instancias de Category asociadas a una instancia Artwork
     *
     * @generated
     */
    @Test
    public void listCategoryTest() {
        List<CategoryEntity> list = artworkLogic.listCategory(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Category existente a un Artwork
     *
     * @generated
     */
    @Test
    public void addCategoryTest() {
        ArtworkEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(1);
        CategoryEntity response = artworkLogic.addCategory(entity.getId(), categoryEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(categoryEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Category asociadas a una instancia de Artwork
     *
     * @generated
     */
    @Test
    public void replaceCategoryTest() {
        ArtworkEntity entity = data.get(0);
        List<CategoryEntity> list = categoryData.subList(1, 3);
        artworkLogic.replaceCategory(entity.getId(), list);

        entity = artworkLogic.getArtwork(entity.getId());
        Assert.assertFalse(entity.getCategory().contains(categoryData.get(0)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(1)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(2)));
    }

    /**
     * Prueba para desasociar un Category existente de un Artwork existente
     *
     * @generated
     */
    @Test
    public void removeCategoryTest() {
        artworkLogic.removeCategory(data.get(0).getId(), categoryData.get(0).getId());
        CategoryEntity response = artworkLogic.getCategory(data.get(0).getId(), categoryData.get(0).getId());
        Assert.assertNull(response);
    }
}