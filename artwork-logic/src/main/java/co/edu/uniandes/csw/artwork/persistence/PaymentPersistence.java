/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.persistence;

import co.edu.uniandes.csw.artwork.entities.ItemEntity;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.florez10
 */
@Stateless
public class PaymentPersistence extends CrudPersistence<PaymentEntity>{
    
    @PersistenceContext(unitName="ArtworkPU")
    protected EntityManager em;
    
     /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<PaymentEntity> getEntityClass() {
        return PaymentEntity.class;
    }
    
    public PaymentEntity find(Long clientid, Long paymentid) {
        TypedQuery<PaymentEntity> q = em.createQuery("select p from PaymentEntity p where (p.client.id = :clientid) and (p.id = :paymentid)", PaymentEntity.class);
        q.setParameter("clientid", clientid);
        q.setParameter("paymentid", paymentid);
        return q.getSingleResult();
    }
    
    public List<PaymentEntity> findAll(Integer page, Integer maxRecords, Long clientid) {
        TypedQuery<PaymentEntity> q = em.createQuery("select p from PaymentEntity p where (p.client.id = :clientid)", PaymentEntity.class);
        q.setParameter("clientid", clientid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
    
    /**
     * Crea un nuevo Payment en la base de datos.
     *
     * @param entity Entidad a persistir.
     * @return Payment persistido.
     * @generated
     */
    @Override
    public PaymentEntity create(PaymentEntity entity){
        super.create(entity);
        TypedQuery<ItemEntity> q = em.createQuery("update ItemEntity p set p.shoppingCart = false where (p.shoppingCart = :shoppingCart)", ItemEntity.class);
        q.setParameter("shoppingCart", true);
        q.executeUpdate();
        return entity;
    }
}
