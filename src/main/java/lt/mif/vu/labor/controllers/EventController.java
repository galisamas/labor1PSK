/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.controllers;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lt.mif.vu.labor.data.Event;

@Named
@RequestScoped
@Stateful
public class EventController {

    @Getter @Setter private Event event = new Event();
    
    public String addTeam(){
        return "?faces-redirect=true";
    }
    
    public String addEvent(){
        return "?faces-redirect=true";
    }
}
