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
import lt.mif.vu.labor.data.Team;

@Named
@Stateless
public class TeamService {
    
    @PersistenceContext
    private EntityManager em;
    
    public void addTeam(Team team){
        em.persist(team);
    }
    
    public Team getTeam(int id){
        return (Team) em.createNamedQuery("Team.findById").setParameter("id", id).getSingleResult();
    }
    
    public Team getTeamByTitle(String title){
        return (Team) em.createNamedQuery("Team.findByTitle").setParameter("title", title).getSingleResult();
    }

    public List<Team> getTeams() {
        return em.createNamedQuery("Team.findAll").getResultList();
    }

    public Team updateTeam(Team selectedTeam) {
        return em.merge(selectedTeam);
    }
    
    

}
