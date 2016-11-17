/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
package co.edu.uniandes.csw.artwork.ejbs;


import co.edu.uniandes.csw.artwork.api.IArtworkLogic;
import co.edu.uniandes.csw.artwork.api.IPrizeLogic;
import co.edu.uniandes.csw.artwork.entities.ArtworkEntity;
import co.edu.uniandes.csw.artwork.entities.PrizeEntity;

import co.edu.uniandes.csw.artwork.persistence.PrizePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class PrizeLogic implements IPrizeLogic {

    @Inject private PrizePersistence persistence;
    private IArtworkLogic artworkLogic;
      

 @Override
 public int countPrizes() {
  return persistence.count(); //To change body of generated methods, choose Tools | Templates.
 }

 @Override
 public List<PrizeEntity> getPrizes() {
  return persistence.findAll(); //To change body of generated methods, choose Tools | Templates.
 }

 @Override
 public List<PrizeEntity> getPrizes(Integer page, Integer maxRecords) {
  return persistence.findAll(page, maxRecords); //To change body of generated methods, choose Tools | Templates.
 }

 @Override
 public PrizeEntity getPrize(Long prizeid) {
  return persistence.find(prizeid); //To change body of generated methods, choose Tools | Templates.
 }

 @Override
 public PrizeEntity createPrize(PrizeEntity entity) {
  
  persistence.create(entity);
  return entity; //To change body of generated methods, choose Tools | Templates.
 }

 @Override
 public PrizeEntity updatePrize(PrizeEntity entity) {
  return persistence.update(entity); //To change body of generated methods, choose Tools | Templates.
 }

    @Override
  public PrizeEntity createPrize(Long artworkid, PrizeEntity entity) {
        ArtworkEntity artwork = artworkLogic.getArtwork(artworkid);
        entity.setArtwork(artwork);
        return persistence.create(entity);
    }
 
}

