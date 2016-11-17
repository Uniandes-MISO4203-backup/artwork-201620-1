/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.resources;

/**
 *
 * @author a.quintero10
 */
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
import co.edu.uniandes.csw.artwork.api.IArtworkLogic;
import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.artwork.api.IPrizeLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.PrizeDetailDTO;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.PrizeEntity;
import java.util.ArrayList;
import javax.ws.rs.PUT;


/**
 * 
 * @generated
 */
@Path("/prizes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrizeResource {

    @Inject private IPrizeLogic prizeLogic;
    @Inject private IArtworkLogic artworkLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
        

   
   
    private List<PrizeDetailDTO> listEntity2DTO(List<PrizeEntity> entityList){
        List<PrizeDetailDTO> list = new ArrayList<>();
        for (PrizeEntity entity : entityList) {
            list.add(new PrizeDetailDTO(entity));
        }
        return list;
    }

   
    @GET
    public List<PrizeDetailDTO> getPrizes() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", prizeLogic.countPrizes());
            return listEntity2DTO(prizeLogic.getPrizes(page, maxRecords));
        }
        return listEntity2DTO(prizeLogic.getPrizes());
    }

   
    @POST
    @StatusCreated
    public PrizeDetailDTO createPrize(PrizeDetailDTO dto) {
        return new PrizeDetailDTO(prizeLogic.createPrize(dto.toEntity()));
    }

    @PUT
    @Path("{prizeId: \\d+}")
    public PrizeDetailDTO updatePrize(@PathParam("prizeId") Long prizeId, PrizeDetailDTO dto) {
    
     PrizeEntity entity = dto.toEntity();
     ArtworkEntity aentity = dto.getArtwork().toEntity();
     aentity.setId(prizeId);
     ArtworkEntity oentity = artworkLogic.getArtwork(aentity.getId());
     oentity.setPrizes(aentity.getPrizes());
     
      artworkLogic.updateArtwork(oentity.getArtist().getId(), oentity);
      return new PrizeDetailDTO(prizeLogic.updatePrize(entity));
      
  
       
    }
    
}

