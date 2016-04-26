/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.mif.vu.labor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "EVENT")
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id"),
    @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findByHaveTeams", query = "SELECT e FROM Event e WHERE e.haveTeams = :haveTeams")})
public class Event implements Serializable {

    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPTLOCKVERSION")
    private int optLockVersion;

    @ManyToMany(mappedBy = "eventList")
    private List<Team> teamList;
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAVE_TEAMS")
    private Boolean haveTeams;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Event(Integer id, Boolean haveTeams) {
        this.id = id;
        this.haveTeams = haveTeams;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHaveTeams() {
        return haveTeams;
    }

    public void setHaveTeams(Boolean haveTeams) {
        this.haveTeams = haveTeams;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.mif.vu.labor.data.Event[ id=" + id + " ]";
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public int getOptLockVersion() {
        return optLockVersion;
    }

    public void setOptLockVersion(int optLockVersion) {
        this.optLockVersion = optLockVersion;
    }

}
