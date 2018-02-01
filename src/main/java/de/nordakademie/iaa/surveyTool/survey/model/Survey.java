package de.nordakademie.iaa.surveyTool.survey.model;

import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Appointment> appointmentOptions;

    private Boolean active;


    private String creator;

    //Constructor

    public Survey() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    public Boolean getActive() {
        return active;
    }

    public Set<Appointment> getAppointmentOptions() {
        return appointmentOptions;
    }

    public void setAppointmentOptions(Set<Appointment> appointmentOptions) {
        this.appointmentOptions = appointmentOptions;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void update(String title, String description, List<Appointment> appointmentOptions) {
    }


}
