/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Event;
import lt.mif.vu.labor.data.Participant;
import lt.mif.vu.labor.data.Team;
import lt.mif.vu.labor.services.EventService;
import lt.mif.vu.labor.services.ParticipantService;
import lt.mif.vu.labor.services.TeamService;

@Named
@ViewScoped
@Stateful
public class ParticipantController {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private TeamService teamService;
    
    @Inject
    private EventService eventService;
    
    @Inject
    private ParticipantService participantService;
    
    @Getter @Setter private Participant participant = new Participant();
    
    @Getter @Setter private List<String> teams = new ArrayList();
    
    @Getter @Setter private List<String> events = new ArrayList();

    @Getter @Setter private boolean canRender;

    @Getter @Setter private String selectedTeam;
    
    @Getter @Setter private String selectedEvent;


    public String addParticipant(){
        em.isOpen();
        if(participantService.addParticipant(participant)){
            if(selectedTeam != null && selectedEvent != null && !selectedTeam.isEmpty()){
                Participant newMember = participantService.getParticipantByNickname(participant.getNickname());
                Event event = eventService.getEventByTitle(selectedEvent);
                Team team = teamService.getTeamByTitle(selectedTeam);

                event.setHaveTeams(true);
                eventService.updateEvent(event);
                List<Event> eventList = team.getEventList();
                eventList.add(event);
                team.setEventList(eventList);

                List<Participant> teamMembers = team.getParticipantList();
                teamMembers.add(newMember);
                team.setParticipantList(teamMembers);
                teamService.updateTeam(team);

                newMember.setTeamId(team);
                participantService.updateParticipant(newMember);
            }

        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "failed on adding participant."));
            return null;
        }
        return "index?faces-redirect=true";
    }
    
    public void renderTE(){
        canRender = true;
    }
    
    @PostConstruct
    public void init(){
        teamService.getTeams().stream().forEach((t) -> {
            teams.add(t.getTitle());
        });
        
        eventService.getEvents().stream().forEach((e) -> {
            events.add(e.getTitle());
        });
    }
}
