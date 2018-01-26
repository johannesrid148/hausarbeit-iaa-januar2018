package de.nordakademie.iaa.appointmentAssignment.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "AppointmentAssignment")
public class AppointmentAssignment {

    private int assignmentID;

    public AppointmentAssignment(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    //Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }
}
