/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.ejbs;

import co.edu.uniandes.csw.artwork.api.IClientLogic;
import co.edu.uniandes.csw.artwork.api.IPaymentLogic;
import co.edu.uniandes.csw.artwork.entities.ClientEntity;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.artwork.persistence.PaymentPersistence;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author s.florez10
 */
public class PaymentLogic implements IPaymentLogic {

    @Inject private PaymentPersistence persistence;

    @Inject
    private IClientLogic clientLogic;
    
    @Override
    public int countPayments() {
        return persistence.count();
    }

     /**
     * Retorna los pagos con el id dado.
     *
     * @param clientid id del cliente.
     * @return Lista de PaymentEntity del usuario dado.
     */
    @Override
    public List<PaymentEntity> getPayments(Long clientid) {
        return getPayments(null, null, clientid);
    }

     /**
     * Obtiene la lista de los registros de Payment que pertenecen a un Client indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param clientid id del Cliente.
     * @return Colección de objetos de PaymentEntity.
     */
    @Override
    public List<PaymentEntity> getPayments(Integer page, Integer maxRecords, Long clientid) {
        return persistence.findAll(page, maxRecords, clientid);
    }

    /**
     * Obtiene el Payment con id dado.
     *
     * @param paymentId id de pago.
     * @return Payment encontrado.
     */
    @Override
    public PaymentEntity getPayment(Long paymentId) {
         try {
            return persistence.find(paymentId);
        }catch(NoResultException e){
            
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Asocia el Payment dado con el cliente dado.
     *
     * @param clientid id del cliente.
     * @param entity Entidad del pago.
     * @return Payment creado.
     */
    @Override
    public PaymentEntity createPayment(Long clientid, PaymentEntity entity) {
        ClientEntity client = clientLogic.getClient(clientid);
        entity.setClient(client);
        entity.setDate(Calendar.getInstance().getTime());
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza el pago dado.
     *
     * @param clientid El id de cliente.
     * @param entity Payment a actualizar.
     * @return Payment actualizado.
     */
    @Override
    public PaymentEntity updatePayment(Long clientid, PaymentEntity entity) {
        ClientEntity client = clientLogic.getClient(clientid);
        entity.setClient(client);
        return persistence.update(entity);
    }

    /**
     * Elimina el Payment con id dado.
     *
     * @param id id del Payment a eliminar.
     * @generated
     */
    @Override
    public void deletePayment(Long id) {
        PaymentEntity old = getPayment(id);
        persistence.delete(old.getId());
    }
    
}
