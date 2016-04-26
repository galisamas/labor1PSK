/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.mif.vu.labor.data;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Naglis
 */
@Entity
@Table(name = "PARTICIPANT")
@NamedQueries({
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p"),
    @NamedQuery(name = "Participant.findById", query = "SELECT p FROM Participant p WHERE p.id = :id"),
    @NamedQuery(name = "Participant.findByName", query = "SELECT p FROM Participant p WHERE p.name = :name"),
    @NamedQuery(name = "Participant.findByNickname", query = "SELECT p FROM Participant p WHERE p.nickname = :nickname"),
    @NamedQuery(name = "Participant.findBySurname", query = "SELECT p FROM Participant p WHERE p.surname = :surname")})
public class Participant implements Serializable {

    
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPTLOCKVERSION")
    private int optLockVersion;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(min=2, max = 20, message = "Must be between 2 and 20 symbols")
    @Column(name = "NICKNAME")
    private String nickname; // business key
    @Size(min=2, max = 20, message = "Must be between 2 and 20 symbols")
    @Column(name = "NAME_P")
    private String name;
    @Size(min=0, max = 20, message = "Must be between 0 and 20 symbols")
    @Column(name = "SURNAME")
    private String surname;
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Team teamId;

    public Participant() {
    }

    public Participant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nickname);
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
        final Participant other = (Participant) obj;
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.mif.vu.labor.Participant[ id=" + id + " ]";
    }

    public int getOptLockVersion() {
        return optLockVersion;
    }

    public void setOptLockVersion(int optLockVersion) {
        this.optLockVersion = optLockVersion;
    }

    
}
