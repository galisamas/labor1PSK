/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.mif.vu.labor.data.Participant;

@Named
@Stateless
public class ParticipantService {

    @PersistenceContext
    private EntityManager em;
    
    public boolean addParticipant(Participant participant){
        try{
            em.persist(participant);
            em.flush();
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public List<Participant> getParticipants() {
        return em.createNamedQuery("Participant.findAll").getResultList();
    }

    public Participant getParticipantByNickname(String nickname) {
        return (Participant) em.createNamedQuery("Participant.findByNickname").setParameter("nickname", nickname).getSingleResult();
    }

    public void updateParticipant(Participant participant) {
        em.merge(participant);
    }
}
