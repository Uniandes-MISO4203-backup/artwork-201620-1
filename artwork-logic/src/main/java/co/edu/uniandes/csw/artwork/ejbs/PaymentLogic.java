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

    @Override
    public List<PaymentEntity> getPayments(Long clientid) {
        return getPayments(null, null, clientid);
    }

    @Override
    public List<PaymentEntity> getPayments(Integer page, Integer maxRecords, Long clientid) {
        return persistence.findAll(page, maxRecords, clientid);
    }

    @Override
    public PaymentEntity getPayment(Long paymentId) {
         try {
            return persistence.find(paymentId);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El pago no existe");
        }
    }

    @Override
    public PaymentEntity createPayment(Long clientid, PaymentEntity entity) {
        ClientEntity client = clientLogic.getClient(clientid);
        entity.setClient(client);
        entity.setDate(Calendar.getInstance().getTime());
        entity = persistence.create(entity);
        return entity;
    }

    @Override
    public PaymentEntity updatePayment(Long clientid, PaymentEntity entity) {
        ClientEntity client = clientLogic.getClient(clientid);
        entity.setClient(client);
        return persistence.update(entity);
    }

    @Override
    public void deletePayment(Long id) {
        PaymentEntity old = getPayment(id);
        persistence.delete(old.getId());
    }
    
}
