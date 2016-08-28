/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.detail;

import co.edu.uniandes.csw.artwork.dtos.minimum.ArtworkDTO;
import co.edu.uniandes.csw.artwork.dtos.minimum.ClientDTO;
import co.edu.uniandes.csw.artwork.dtos.minimum.CommentDTO;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.quintero10
 */
@XmlRootElement
public class CommentDetailDTO extends CommentDTO{
    
    @PodamExclude
    private ArtworkDTO artwork;
    @PodamExclude
    private ClientDTO client;

    
    public CommentDetailDTO() {
        super();
    }
    
    public CommentDetailDTO(CommentEntity entity) {
        super(entity);
        if (entity.getArtwork()!=null){
        this.artwork = new ArtworkDTO(entity.getArtwork());
        }
        if (entity.getClient()!=null){
        this.client = new ClientDTO(entity.getClient());
        }
    }
    
    public CommentEntity toEntity() {
        CommentEntity entity = super.toEntity();
        if (this.getArtwork()!=null){
        entity.setArtwork(this.getArtwork().toEntity());
        }
        if (this.getClient()!=null){
        entity.setClient(this.getClient().toEntity());
        }
       
        return entity;
    }
    /**
     * @return the artwork
     */
    public ArtworkDTO getArtwork() {
        return artwork;
    }

    /**
     * @param artwork the artwork to set
     */
    public void setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
    }

    /**
     * @return the client
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
