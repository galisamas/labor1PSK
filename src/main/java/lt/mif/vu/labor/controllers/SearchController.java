/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Participant;
import lt.mif.vu.labor.data.Team;
import lt.mif.vu.labor.services.ParticipantService;
import lt.mif.vu.labor.services.TeamService;
import org.primefaces.context.RequestContext;

@Named
@RequestScoped
@Stateful
public class SearchController {

    @Inject
    private TeamService teamService;
    
    @Inject
    private ParticipantService participantService;
    
    @Getter @Setter private Participant selectedParticipant = new Participant();
    
    @Getter @Setter private Team team = new Team();
    
    @Getter @Setter private List<Participant> participants = new ArrayList<>();
    
    @Getter @Setter private boolean showDialog;
    
    @Getter @Setter private String teamName;
    
    public void onRowSelect(){
        if(selectedParticipant.getTeamId() != null){
            team = selectedParticipant.getTeamId();
            teamName = team.getTitle();
        }else{
            teamName = "no team";
        }
        showDialog = true;
    }
    
    @PostConstruct
    public void init() { 
        participants = participantService.getParticipants();
    }
}
