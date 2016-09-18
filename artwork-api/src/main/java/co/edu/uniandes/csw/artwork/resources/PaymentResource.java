/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.resources;

import co.edu.uniandes.csw.artwork.api.IPaymentLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.PaymentDetailDTO;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author s.florez10
 */
@Path("/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {
    
    @Inject private IPaymentLogic paymentLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @QueryParam("clientsId") private Long clientsId;
    
     /**
     * Convierte una lista de PaymentEntity a una lista de PaymentDTO
     *
     * @param entityList Lista de PaymentEntity a convertir
     * @return Lista de PaymentDTO convertida
     * @generated
     */
    private List<PaymentDetailDTO> listEntity2DTO(List<PaymentEntity> entityList){
        List<PaymentDetailDTO> list = new ArrayList<>();
        for (PaymentEntity entity : entityList) {
            list.add(new PaymentDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Payment asociados a un Client
     *
     * @return Colección de objetos de PaymentDTO
     * @generated
     */
    @GET
    public List<PaymentDetailDTO> getPayments() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", paymentLogic.countPayments());
            return listEntity2DTO(paymentLogic.getPayments(page, maxRecords, clientsId));
        }
        return listEntity2DTO(paymentLogic.getPayments(clientsId));
    }

    /**
     * Obtiene los datos de una instancia de Payment a partir de su ID asociado a un Client
     *
     * @param paymentId Identificador de la instancia a consultar
     * @return Instancia de PaymentDTO con los datos del Payment consultado
     * @generated
     */
    @GET
    @Path("{paymentId: \\d+}")
    public PaymentDetailDTO getPayment(@PathParam("paymentId") Long paymentId) {
        PaymentEntity entity = paymentLogic.getPayment(paymentId);
        if (entity.getClient() != null && !clientsId.equals(entity.getClient().getId())) {
            throw new WebApplicationException(404);
        }
        return new PaymentDetailDTO(entity);
    }

    /**
     * Asocia un payment existente a un Client
     *
     * @param dto Objeto de PaymentDTO con los datos nuevos
     * @return Objeto de PaymentDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public PaymentDetailDTO createPayment(PaymentDetailDTO dto) {
        return new PaymentDetailDTO(paymentLogic.createPayment(clientsId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Payment.
     *
     * @param paymentId Identificador de la instancia de Payment a modificar
     * @param dto Instancia de PaymentDTO con los nuevos datos.
     * @return Instancia de PaymentDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{paymentId: \\d+}")
    public PaymentDetailDTO updatePayment(@PathParam("paymentId") Long paymentId, PaymentDetailDTO dto) {
        PaymentEntity entity = dto.toEntity();
        entity.setId(paymentId);
        return new PaymentDetailDTO(paymentLogic.updatePayment(clientsId, entity));
    }

    /**
     * Elimina una instancia de Payment de la base de datos.
     *
     * @param paymentId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{paymentId: \\d+}")
    public void deletePayment(@PathParam("paymentId") Long paymentId) {
        paymentLogic.deletePayment(paymentId);
    }
}
