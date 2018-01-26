package de.nordakademie.iaa.appointmentAssignment.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "AppointmentAssignment")
public class AppointmentAssignment {


    private long id;
   private Date start;
   private Date end;
   private Boolean chosen;

   //Constructor
    public AppointmentAssignment(long id, Date start, Date end, Boolean chosen) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.chosen = chosen;
    }

    //Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Boolean getChosen() {
        return chosen;
    }

    //Setter
    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }

    public void setId(long id) {
        this.id = id;
    }
}
