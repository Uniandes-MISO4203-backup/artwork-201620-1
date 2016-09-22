/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.api;

import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import java.util.List;

/**
 *
 * @author s.florez10
 */
public interface IPaymentLogic {
    public int countPayments();
    public List<PaymentEntity> getPayments(Long clientid);
    public List<PaymentEntity> getPayments(Integer page, Integer maxRecords, Long clientid);
    public PaymentEntity getPayment(Long itemid);
    public PaymentEntity createPayment(Long clientid, PaymentEntity entity);
    public PaymentEntity updatePayment(Long clientid, PaymentEntity entity);
    public void deletePayment(Long id);
}
