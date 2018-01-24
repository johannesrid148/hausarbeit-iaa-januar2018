package de.nordakademie.iaa.appointmentSurvey.model;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "appointmentSurvey")
public class AppointmentSurvey {

    private String title;
    private String description;
    private List<Appointment> appointmentOptions;
    private Boolean active;
    private User creator;

    //Constructor

    public AppointmentSurvey(String title, String description, List<Appointment> appointmentOptions, User creator) {
        this.title = title;
        this.description = description;
        this.appointmentOptions = appointmentOptions;
        this.active = true;
        this.creator = creator;
    }

    //Getter


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Appointment> getAppointmentOptions() {
        return appointmentOptions;
    }

    public Boolean getActive() {
        return active;
    }

    public User getCreator() {
        return creator;
    }

    //setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppointmentOptions(List<Appointment> appointmentOptions) {
        this.appointmentOptions = appointmentOptions;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    //methods

    //edit Survey
    public void editSurvey (String title, String description, List<Appointment> options){
        setTitle(title);
        setDescription(description);
        for (int i = 0; i < getAppointmentOptions().size(); i++) {
            appointmentOptions.remove(i);
        }
        setAppointmentOptions(options);
    }


    //end Survey
    public Appointment getChosenAppointment() {
        Appointment chosen=null;
        for (int i = 0; i < getAppointmentOptions().size(); i++) {
            if (getAppointmentOptions().get(i).getChosen()==true){
                chosen=getAppointmentOptions().get(i);
            }
        }
        return chosen;
    }

    public Appointment endSurvey(){
        setActive(false);
        return getChosenAppointment();
    }



}

