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

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import co.edu.uniandes.csw.artwork.dtos.minimum.ArtistDTO;
import co.edu.uniandes.csw.artwork.resources.ArtistResource;
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
 * Testing URI: artists/
 */
@RunWith(Arquillian.class)
public class ArtistTest {

    private WebTarget target;
    private static final String API_PATH = Utils.apiPath;
    private static final String USERNAME = Utils.username;
    private static final String PASSWORD = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private static final int OK = Status.OK.getStatusCode();
    private static final int CREATED = Status.CREATED.getStatusCode();
    private static final int OK_WITHOUT_CONTENT = Status.NO_CONTENT.getStatusCode();

    private static final List<ArtistEntity> oraculo = new ArrayList<>();

    private static final String ARTIST_PATH = "artists";
    
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
                .addPackage(ArtistResource.class.getPackage())
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
        em.createQuery("delete from ArtistEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {            
            ArtistEntity artist = factory.manufacturePojo(ArtistEntity.class);
            artist.setId(i + 1L);
            em.persist(artist);
            oraculo.add(artist);
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
                .path(ARTIST_PATH);
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
     * Prueba para crear un Artist
     *
     * @generated
     */
    @Test
    public void createArtistTest() throws IOException {
        ArtistDTO artist = factory.manufacturePojo(ArtistDTO.class);
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(artist, MediaType.APPLICATION_JSON));

        ArtistDTO  artistTest = (ArtistDTO) response.readEntity(ArtistDTO.class);

        Assert.assertEquals(CREATED, response.getStatus());

        Assert.assertEquals(artist.getName(), artistTest.getName());

        ArtistEntity entity = em.find(ArtistEntity.class, artistTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Artist
     *
     * @generated
     */
    @Test
    public void getArtistByIdTest() {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        ArtistDTO artistTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(ArtistDTO.class);
        
        Assert.assertEquals(artistTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(artistTest.getName(), oraculo.get(0).getName());
    }

    /**
     * Prueba para consultar la lista de Artists
     *
     * @generated
     */
    @Test
    public void listArtistTest() throws IOException {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listArtist = response.readEntity(String.class);
        List<ArtistDTO> listArtistTest = new ObjectMapper().readValue(listArtist, List.class);
        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(3, listArtistTest.size());
    }

    /**
     * Prueba para actualizar un Artist
     *
     * @generated
     */
    @Test
    public void updateArtistTest() throws IOException {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);
        ArtistDTO artist = new ArtistDTO(oraculo.get(0));

        ArtistDTO artistChanged = factory.manufacturePojo(ArtistDTO.class);

        artist.setName(artistChanged.getName());

        Response response = target
            .path(artist.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(artist, MediaType.APPLICATION_JSON));

        ArtistDTO artistTest = (ArtistDTO) response.readEntity(ArtistDTO.class);

        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(artist.getName(), artistTest.getName());
    }

    /**
     * Prueba para eliminar un Artist
     *
     * @generated
     */
    @Test
    public void deleteArtistTest() {
        Cookie cookieSessionId = login(USERNAME, PASSWORD);
        ArtistDTO artist = new ArtistDTO(oraculo.get(0));
        Response response = target
            .path(artist.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OK_WITHOUT_CONTENT, response.getStatus());
    }
}
