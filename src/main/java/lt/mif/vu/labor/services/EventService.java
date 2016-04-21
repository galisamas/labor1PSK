/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lt.mif.vu.labor.data.Event;

@Named
@Stateless
public class EventService {

    @PersistenceContext
    private EntityManager em;
    
    
    public Event getEvent(int id){
        return (Event) em.createNamedQuery("Event.findById").setParameter("id", id).getSingleResult();
    }
    
    public Event getEventByTitle(String title){
        return (Event) em.createNamedQuery("Event.findByTitle").setParameter("title", title).getSingleResult();
    }

    public List<Event> getEvents() {
        return em.createNamedQuery("Event.findAll").getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Event updateEvent(Event selectedEvent) {
        return em.merge(selectedEvent);
    }
}
