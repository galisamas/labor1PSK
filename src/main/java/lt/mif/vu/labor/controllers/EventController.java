/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.EXTENDED;
import static javax.persistence.SynchronizationType.UNSYNCHRONIZED;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Event;
import lt.mif.vu.labor.services.EventService;

@Named
@RequestScoped
@Stateful
public class EventController {

    @PersistenceContext//(type=EXTENDED,synchronization=UNSYNCHRONIZED)
    private EntityManager em;
    
    @Inject
    private EventService eventService;
    
    @Getter @Setter private Event event = new Event();
    
//    public String addTeam(){ //??????????????
//        em.isOpen();
//        return "addTeamManagement?faces-redirect=true";
//    }
    
    public String addEvent(){
        eventService.addEvent(event);
        return "index?faces-redirect=true";
    }
    
}
