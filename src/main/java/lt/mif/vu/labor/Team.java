/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.mif.vu.labor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Naglis
 */
@Entity
@Table(name = "TEAM")
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.id = :id"),
    @NamedQuery(name = "Team.findByTitle", query = "SELECT t FROM Team t WHERE t.title = :title"),
    @NamedQuery(name = "Team.findByMemberCount", query = "SELECT t FROM Team t WHERE t.memberCount = :memberCount")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "TITLE")
    private String title; // business key
    @Column(name = "MEMBER_COUNT")
    private Integer memberCount;
    @JoinTable(name = "EVENT_TEAM", joinColumns = {
        @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Event> eventList;
    @OneToMany(mappedBy = "teamId")
    private List<Participant> participantList;

    public Team() {
    }

    public Team(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "lt.mif.vu.labor.Team[ id=" + id + " ]";
    }
    
}
