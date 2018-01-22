package de.nordakademie.iaa.appointment.model;

import java.util.Date;

public class Appointment {

   private Date start;
   private Date end;
   private Boolean chosen;

   //Constructor
    public Appointment(Date start, Date end, Boolean chosen) {
        this.start = start;
        this.end = end;
        this.chosen = chosen;
    }

    //Getter

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
}
