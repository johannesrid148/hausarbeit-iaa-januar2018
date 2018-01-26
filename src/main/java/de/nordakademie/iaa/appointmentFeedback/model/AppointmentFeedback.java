package de.nordakademie.iaa.appointmentFeedback.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "AppointmentFeedback")
public class AppointmentFeedback {

    private int feedbackID;

    public AppointmentFeedback(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    //Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }
}
