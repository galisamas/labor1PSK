/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Participant;
import lt.mif.vu.labor.data.Team;

@Named
@RequestScoped
@Stateful
public class ParticipantController {
    
    @Getter @Setter private Participant participant = new Participant();
    
    @Getter @Setter private List<Team> teams = new ArrayList();
    
    @Getter @Setter private boolean canRender;

    public String addParticipant(){
        return "?faces-redirect=true";
    }
    
    public String RenderTeams(){
        return "?faces-redirect=true";
    }
}
