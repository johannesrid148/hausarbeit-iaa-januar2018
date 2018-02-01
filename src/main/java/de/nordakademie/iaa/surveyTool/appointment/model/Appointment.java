package de.nordakademie.iaa.surveyTool.appointment.model;

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


    @ElementCollection (fetch = FetchType.EAGER)
    private List<String> participants;

    public Appointment() {
    }

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
