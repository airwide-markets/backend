package za.co.airwide.marketplace.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/*
       Column        |            Type             | Collation | Nullable | Default
---------------------+-----------------------------+-----------+----------+---------
 flight_school_id    | text                        |           | not null |
 student_id          | text                        |           | not null |
 instructor_id       | text                        |           | not null |
 aircraft_model      | text                        |           | not null |
 aircraft_reg_number | text                        |           | not null |
 uuid                | text                        |           | not null |
 start_date          | timestamp without time zone |           |          |
 end_date            | timestamp without time zone |           |          |
 description         | text                        |           |          |
 status              | text                        |           |          |
Indexes:
    "schedule_pkey" PRIMARY KEY, btree (flight_school_id, student_id, instructor_id, aircraft_model,
    aircraft_reg_number, uuid)

 */
public class ScheduleDTO implements Serializable {

    private String flightSchoolID;
    private String studentID;
    private String instructorID;
    private String aircraftModel;
    private String aircraftRegNumber;
    private String uuid;
    private Date startDate;
    private Date endDate;
    private String description;
    private String status;

    public ScheduleDTO(String flightSchoolID,
                       String studentID,
                       String instructorID,
                       String aircraftModel,
                       String aircraftRegNumber,
                       String uuid,
                       Date startDate,
                       Date endDate,
                       String description,
                       String status) {

        this.flightSchoolID = flightSchoolID;
        this.studentID = studentID;
        this.instructorID = instructorID;
        this.aircraftModel = aircraftModel;
        this.aircraftRegNumber = aircraftRegNumber;
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
    }

    public ScheduleDTO() {
    }

    public String getFlightSchoolID() {
        return flightSchoolID;
    }

    public void setFlightSchoolID(String flightSchoolID) {
        this.flightSchoolID = flightSchoolID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public String getAircraftRegNumber() {
        return aircraftRegNumber;
    }

    public void setAircraftRegNumber(String aircraftRegNumber) {
        this.aircraftRegNumber = aircraftRegNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDTO that = (ScheduleDTO) o;
        return flightSchoolID.equals(that.flightSchoolID) &&
                studentID.equals(that.studentID) &&
                instructorID.equals(that.instructorID) &&
                aircraftModel.equals(that.aircraftModel) &&
                aircraftRegNumber.equals(that.aircraftRegNumber) &&
                uuid.equals(that.uuid) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightSchoolID, studentID, instructorID, aircraftModel, aircraftRegNumber, uuid,
                startDate, endDate, description, status);
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "flightSchoolID='" + flightSchoolID + '\'' +
                ", studentID='" + studentID + '\'' +
                ", instructorID='" + instructorID + '\'' +
                ", aircraftModel='" + aircraftModel + '\'' +
                ", aircraftRegNumber='" + aircraftRegNumber + '\'' +
                ", uuid='" + uuid + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
