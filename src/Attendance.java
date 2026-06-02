import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Attendance {
    private int attendanceId, memberId;
    private String date;
    private String checkInTime;

    public Attendance(int attendanceId, int memberId) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.checkInTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public void checkIn() {
        System.out.println("Check-in successful!");
        System.out.println("Member ID   : " + memberId);
        System.out.println("Date        : " + date);
        System.out.println("Time        : " + checkInTime);
    }

    public void showAttendanceHistory() {
        System.out.println("Member ID : " + memberId + " | Date: " + date + " | Time: " + checkInTime);
    }

    public int getMemberId()     { return memberId; }
    public String getDate()      { return date; }
    public String getCheckInTime() { return checkInTime; }
}
