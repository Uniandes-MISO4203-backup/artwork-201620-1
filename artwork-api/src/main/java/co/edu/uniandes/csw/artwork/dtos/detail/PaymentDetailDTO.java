/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.dtos.detail;

import co.edu.uniandes.csw.artwork.dtos.minimum.ClientDTO;
import co.edu.uniandes.csw.artwork.dtos.minimum.PaymentDTO;
import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.florez10
 */
@XmlRootElement
public class PaymentDetailDTO extends PaymentDTO{
    @PodamExclude
    private ClientDTO client;
    
    @PodamExclude
    private List<ItemDetailDTO> items;
    
    public PaymentDetailDTO(){
        super();
    }
    
    /**
     * Crea un objeto PaymentDetailDTO a partir de un objeto PaymentEntity incluyendo los atributos de PaymentDTO.
     *
     * @param entity Entidad PaymentEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public PaymentDetailDTO(PaymentEntity entity) {
        super(entity);
        if (entity.getClient()!=null){
            this.client = new ClientDTO(entity.getClient());
        }
        if (entity.getItems()!=null){
            this.items = new ArrayList<>();
            entity.getItems().stream().forEach(item->items.add(new ItemDetailDTO(item)));
        }
    }

    /**
     * Convierte un objeto PaymentDetailDTO a PaymentEntity incluyendo los atributos de PaymentDTO.
     *
     * @return Nueva objeto PaymentEntity.
     * @generated
     */
    @Override
    public PaymentEntity toEntity() {
        PaymentEntity entity = super.toEntity();
        if (this.getClient()!=null){
            entity.setClient(this.getClient().toEntity());
        }
        if (this.getItems()!=null){
            List<ItemEntity> newItems = new ArrayList<>();
            this.getItems().stream().forEach(item->newItems.add(item.toEntity()));
            entity.setItems(newItems);
        }
        return entity;
    }
    
     /**
     * Obtiene el atributo client.
     *
     * @return atributo client.
     * @generated
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * Establece el valor del atributo client.
     *
     * @param client nuevo valor del atributo
     * @generated
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }
    
     /**
     * Obtiene el atributo items.
     *
     * @return atributo imtes.
     * @generated
     */
    public List<ItemDetailDTO> getItems() {
        return items;
    }

    /**
     * Establece el valor del atributo items.
     *
     * @param items nuevo valor del atributo
     * @generated
     */
    public void setItems(List<ItemDetailDTO> items) {
        this.items = items;
    }

}
