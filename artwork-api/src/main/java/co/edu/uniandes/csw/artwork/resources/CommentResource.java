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

import co.edu.uniandes.csw.artwork.api.ICommentLogic;
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
import co.edu.uniandes.csw.artwork.api.IItemLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.CommentDetailDTO;
import co.edu.uniandes.csw.artwork.dtos.detail.ItemDetailDTO;
import co.edu.uniandes.csw.artwork.ejbs.CommentLogic;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: clients/{wishListId: \\d+}/wishList/
 * @generated
 */
@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject private ICommentLogic commentLogic;
    @Context private HttpServletResponse response;
    
    @PathParam("artworksId") private Long artworksId;

   
    /**
     * Convierte una lista de ItemEntity a una lista de ItemDetailDTO
     *
     * @param entityList Lista de ItemEntity a convertir
     * @return Lista de ItemDetailDTO convertida
     * @generated
     */
    private List<CommentDetailDTO> listEntity2DTO(List<CommentEntity> entityList){
        List<CommentDetailDTO> list = new ArrayList<>();
        for (CommentEntity entity : entityList) {
            list.add(new CommentDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Item asociados a un Client
     *
     * @return Colección de objetos de ItemDetailDTO
     * @generated
     */
    @GET
   
    public List<CommentDetailDTO> getComments(){
         
            return listEntity2DTO(commentLogic.getComments(artworksId));
    }
    @POST
    @StatusCreated
    public CommentDetailDTO createComment(CommentDetailDTO dto) {
        return new CommentDetailDTO(commentLogic.createComment(dto.toEntity()));
    }
    
}
