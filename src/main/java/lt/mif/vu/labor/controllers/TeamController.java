/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.EXTENDED;
import static javax.persistence.SynchronizationType.UNSYNCHRONIZED;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Event;
import lt.mif.vu.labor.data.Participant;
import lt.mif.vu.labor.data.Team;
import lt.mif.vu.labor.services.EventService;
import lt.mif.vu.labor.services.ParticipantService;
import lt.mif.vu.labor.services.TeamService;

@Named
@ConversationScoped
@Stateful
public class TeamController {
    
    @PersistenceContext(type=EXTENDED,synchronization=UNSYNCHRONIZED)
    private EntityManager em;
    
    @Inject
    private Conversation conversation;
    
    @Inject
    private TeamService teamService;
    
    @Inject
    private ParticipantService participantService;
    
    @Getter @Setter private Participant participant = new Participant();
    
    @Getter @Setter private Team team = new Team();
    
    public String addTeam(){
        if(!conversation.isTransient())
            return null;
        conversation.begin();
        return "addParticipantForTeamManagement?faces-redirect=true";
    }
    
    public String addMember(){
        if(conversation.isTransient())
            return null;
        participant.setTeamId(team);
        participantService.addParticipant(participant);
        participant = new Participant();
        return "addParticipantForTeamManagement?faces-redirect=true";
    }
    
    public String addParticipant(){
        if(conversation.isTransient())
            return null;
        if(!participant.getName().isEmpty()){
            participant.setTeamId(team);
            participantService.addParticipant(participant);
        }
        teamService.addTeam(team);
        em.joinTransaction();
        conversation.end();
        return "index?faces-redirect=true";
    }
    
    public String cancel(){
        if(conversation.isTransient())
            return null;
        conversation.end();
        return "index?faces-redirect=true";
    }
}
