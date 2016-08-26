/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artwork.ejbs;

import co.edu.uniandes.csw.artwork.api.ICommentLogic;
import co.edu.uniandes.csw.artwork.entities.CommentEntity;
import co.edu.uniandes.csw.artwork.persistence.CommentPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.quintero10
 */
@Stateless
public class CommentLogic implements ICommentLogic {
@Inject private CommentPersistence persistence;
   
     @Override
    public int countItems() {
        return persistence.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommentEntity> getComments(Integer page, Integer maxRecords, Long artworkid) {
        return persistence.findAll(page, maxRecords, artworkid); //To change body of generated methods, choose Tools | Templates.
    }
    
}
