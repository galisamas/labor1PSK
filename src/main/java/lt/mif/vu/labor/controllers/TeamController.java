/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Participant;
import lt.mif.vu.labor.data.Team;

@Named
@ConversationScoped
@Stateful
public class TeamController {

    @Getter @Setter private Participant participant = new Participant();
    
    @Getter @Setter private Team team = new Team();
    
    public String addMember(){
        return "?faces-redirect=true";
    }
    
    public String addParticipant(){
        return "?faces-redirect=true";
    }
    
    public String cancel(){
        return "index?faces-redirect=true";
    }
}
