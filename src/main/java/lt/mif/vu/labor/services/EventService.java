/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.services;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.mif.vu.labor.data.Event;

@Named
@Stateless
public class EventService {

    @PersistenceContext
    private EntityManager em;
    
    public void addEvent(Event event){
        em.persist(event);
    }
    
    public Event getEvent(int id){
        return (Event) em.createNamedQuery("Event.findById").setParameter("id", id).getSingleResult();
    }
}
