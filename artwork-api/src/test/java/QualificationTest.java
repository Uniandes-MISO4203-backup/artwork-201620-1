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
package co.edu.uniandes.csw.artwork.tests.rest;

import co.edu.uniandes.csw.artwork.dtos.detail.QualificationDetailDTO;
import co.edu.uniandes.csw.artwork.dtos.minimum.ArtworkDTO;
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.dtos.minimum.QualificationDTO;
import co.edu.uniandes.csw.artwork.resources.QualificationResource;
import co.edu.uniandes.csw.artwork.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.util.log.Log;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: artworks/{qualificationsId: \\d+}/qualifications/
 */
@RunWith(Arquillian.class)
public class QualificationTest {

    private WebTarget target;
    private static final String API_PATH = Utils.API_PATH;
    private static final String USERNAME = Utils.USERNAME;
    private static final String PASSWORD = Utils.PASSWORD;
    PodamFactory factory = new PodamFactoryImpl();

    private static final int OK = Status.OK.getStatusCode();
    private static final int CREATED = Status.CREATED.getStatusCode();
    private static final int OK_WITHOUT_CONTENT = Status.NO_CONTENT.getStatusCode();

    private  static final List<QualificationEntity> oraculo = new ArrayList<>();

    private static final String ARTWORK_PATH = "artworks";
    private static final String QUALIFICATION_PATH = "qualifications";

    ArtworkEntity fatherArtworkEntity;

    @ArquillianResource
    private URL deploymentURL;
    
    @PersistenceContext(unitName = "ArtworkPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(QualificationResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(API_PATH);
    }

    

    private void clearData() {
        em.createQuery("delete from QualificationEntity").executeUpdate();
        em.createQuery("delete from ArtworkEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherArtworkEntity = factory.manufacturePojo(ArtworkEntity.class);
        fatherArtworkEntity.setId(1L);
        em.persist(fatherArtworkEntity);

        for (int i = 0; i < 3; i++) {            
            QualificationEntity qualification = factory.manufacturePojo(QualificationEntity.class);
            qualification.setId(i + 1L);
            qualification.setArtwork(fatherArtworkEntity);
            em.persist(qualification);
            oraculo.add(qualification);
        }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            Log.getLog().warn(e);
            try {
                utx.rollback();
            } catch (Exception e1) {
                Log.getLog().warn(e1);
            }
        }
        target = createWebTarget()
                .path(QUALIFICATION_PATH)
                .path(fatherArtworkEntity.getId().toString());
                
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = createWebTarget().path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == OK) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un Qualification
     *
     * @generated
     */
    @Test
    public void createQualificationTest() throws IOException {
        QualificationDetailDTO qualification = factory.manufacturePojo(QualificationDetailDTO.class);
        qualification.setArtwork(new ArtworkDTO(fatherArtworkEntity));
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(qualification, MediaType.APPLICATION_JSON));

        QualificationDetailDTO  qualificationTest = (QualificationDetailDTO) response.readEntity(QualificationDetailDTO.class);

        Assert.assertEquals(CREATED, response.getStatus());

        Assert.assertEquals(qualification.getQualification(), qualificationTest.getQualification());
        

        QualificationEntity entity = em.find(QualificationEntity.class, qualificationTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Qualification
     *
     * @generated
     */
    @Test
    public void getQualificationByIdTest() {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        QualificationDTO qualificationTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(QualificationDTO.class);
        
        Assert.assertEquals(qualificationTest.getId(), oraculo.get(0).getId());
       Assert.assertEquals(qualificationTest.getQualification(), oraculo.get(0).getQualification());
    }

    /**
     * Prueba para consultar la lista de Qualifications
     *
     * @generated
     */
    @Test
    public void listQualificationTest() throws IOException {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listQualification = response.readEntity(String.class);
        List<QualificationDTO> listQualificationTest = new ObjectMapper().readValue(listQualification, List.class);
        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(3, listQualificationTest.size());
    }

    /**
     * Prueba para actualizar un Qualification
     *
     * @generated
     */
    @Test
    public void updateQualificationTest() throws IOException {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);
        QualificationDTO qualification = new QualificationDTO(oraculo.get(0));

        QualificationDTO qualificationChanged = factory.manufacturePojo(QualificationDTO.class);

        qualification.setQualification(qualificationChanged.getQualification());
       

        Response response = target
            .path(qualification.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(qualification, MediaType.APPLICATION_JSON));

        QualificationDTO qualificationTest = (QualificationDTO) response.readEntity(QualificationDTO.class);

        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(qualification.getId(), qualificationTest.getId());
        Assert.assertEquals(qualification.getQualification(), qualificationTest.getQualification());
    }

    /**
     * Prueba para eliminar un Qualification
     *
     * @generated
     */
    @Test
    public void deleteQualificationTest() {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);
        QualificationDTO qualification = new QualificationDTO(oraculo.get(0));
        Response response = target
            .path(qualification.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OK_WITHOUT_CONTENT, response.getStatus());
    }
}
