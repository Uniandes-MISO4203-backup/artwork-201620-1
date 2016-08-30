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
package co.edu.uniandes.csw.artwork.resources;

import co.edu.uniandes.csw.artwork.api.IArtworkLogic;
import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.artwork.api.IQualificationLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.QualificationDetailDTO;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.QualificationEntity;
import co.edu.uniandes.csw.auth.stormpath.Utils;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * URI: qualifications/
 * @generated
 */
@Path("/qualifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QualificationResource {

    private static final String CLIENT_HREF = "https://api.stormpath.com/v1/groups/8hCxfQfGQ1EvhrCX9yXsL";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/7luSBhdqfQi2FUjUZAIhp7";
    private static final String ARTIST_HREF = "https://api.stormpath.com/v1/groups/K4yTGg11sCUoGBbJe0GJ3";

    @Inject private IQualificationLogic qualificationLogic;
    @Inject private IArtworkLogic artworkLogic;
    @Context private HttpServletResponse response;
    @Context private HttpServletRequest req;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @QueryParam("qualification") private Integer qualification;
    @QueryParam("artworkId") private Long artworkId;
    @QueryParam("userClient") private String userClient;

   
    /**
     * Convierte una lista de QualificationEntity a una lista de QualificationDetailDTO.
     *
     * @param entityList Lista de QualificationEntity a convertir.
     * @return Lista de QualificationDetailDTO convertida.
     * @generated
     */
    protected static List<QualificationDetailDTO> listEntity2DTO(List<QualificationEntity> entityList){
        List<QualificationDetailDTO> list = new ArrayList<>();
        for (QualificationEntity entity : entityList) {
            list.add(new QualificationDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Qualification
     *
     * @return Colección de objetos de QualificationDetailDTO
     * @generated
     */
    @GET
    public List<QualificationDetailDTO> getQualifications() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {
                    case ADMIN_HREF:
                        if (page != null && maxRecords != null) {
                            this.response.setIntHeader("X-Total-Count", qualificationLogic.countQualifications());
                            return listEntity2DTO(qualificationLogic.getQualifications(page, maxRecords));
                        }
                        return listEntity2DTO(qualificationLogic.getQualifications());                        
                }
            }
        } 
        return null;
        
    }    

    /**
     * Obtiene los datos de una instancia de Qualification a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de QualificationDetailDTO con los datos del Qualification consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public QualificationDetailDTO getQualification(@PathParam("id") Long id) {
        return new QualificationDetailDTO(qualificationLogic.getQualification(id));
    }

    /**
     * Se encarga de crear un Qualification en la base de datos
     *
     * @param dto Objeto de QualificationDetailDTO con los datos nuevos
     * @return Objeto de QualificationDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public QualificationDetailDTO createQualification(QualificationDetailDTO dto) {
        return new QualificationDetailDTO(qualificationLogic.createQualification(dto.toEntity()));
    }
    
    @POST
    @StatusCreated
    @Path("/crear")
    public QualificationDetailDTO createQualification() {
        ArtworkEntity artwork = artworkLogic.getArtwork(artworkId);
        return new QualificationDetailDTO(qualificationLogic.createQualification(artwork, userClient, qualification));
    }

    /**
     * Actualiza la información de una instancia de Qualification
     *
     * @param id Identificador de la instancia de Qualification a modificar
     * @param dto Instancia de QualificationDetailDTO con los nuevos datos
     * @return Instancia de QualificationDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public QualificationDetailDTO updateQualification(@PathParam("id") Long id, QualificationDetailDTO dto) {
        QualificationEntity entity = dto.toEntity();
        entity.setId(id);
        QualificationEntity oldEntity = qualificationLogic.getQualification(id);
        return new QualificationDetailDTO(qualificationLogic.updateQualification(entity));
    }

    /**
     * Elimina una instancia de Qualification de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteQualification(@PathParam("id") Long id) {
        qualificationLogic.deleteQualification(id);
    }
    
    public void existsQualification(Long qualificationsId){
        QualificationDetailDTO qualification = getQualification(qualificationsId);
        if (qualification== null) {
            throw new WebApplicationException(404);
        }
    }    
    
    /**
     * Obtiene la lista de los registros de Qualification
     *
     * @param artworkid Id de la obra de arte 
     * @return Colección de objetos de QualificationDetailDTO
     * @generated
     */
    @GET
    @Path("/artwork")
    public List<QualificationDetailDTO> getQualificationsArtwork() {
                
        return QualificationResource.listEntity2DTO(artworkLogic.getQualifications(artworkId));
        
    }
    
}
