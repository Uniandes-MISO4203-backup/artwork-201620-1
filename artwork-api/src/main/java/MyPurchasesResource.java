package co.edu.uniandes.csw.artwork.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.artwork.api.IPaymentLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.PaymentDetailDTO;
import co.edu.uniandes.csw.artwork.dtos.minimum.PaymentDTO;
import co.edu.uniandes.csw.artwork.entities.PaymentEntity;
import co.edu.uniandes.csw.auth.stormpath.Utils;
import com.stormpath.sdk.account.Account;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;


/**
 *
 * @author a.quintero10
 */
@Path("/purchases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MyPurchasesResource {
 
 @Inject IPaymentLogic paymentLogic;
 @Context private HttpServletRequest req;
 
 @GET
  public List<PaymentDetailDTO> getPurchasesByClient() {       
   
 String accountHref = req.getRemoteUser();
 
 if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            Integer id = (int) account.getCustomData().get("client_id");
           List<PaymentEntity> payments =paymentLogic.getPayments(id.longValue());
             List<PaymentDetailDTO> payDTO  = new ArrayList();                
             payments.stream().forEach((pay) -> {
              payDTO.add(new PaymentDetailDTO(pay));
  });
            return payDTO;
    } else{
 return null;
 }
 }
  
}
