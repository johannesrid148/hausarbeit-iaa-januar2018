package de.nordakademie.iaa.appointment.model;

import de.nordakademie.iaa.user.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date start;
    private Date end;

    //@ManyToMany
    @ElementCollection
    private List<String> participants;

    //Constructor
    public Appointment(Date start, Date end, List<String> participants) {
        this.start = start;
        this.end = end;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
