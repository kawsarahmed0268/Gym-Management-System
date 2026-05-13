public class Attendance {
    private int attendanceId, memberId;
    private String date, checkInTime;

    public Attendance(int attendanceId, int memberId, String date, String checkInTime) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.date = date;
        this.checkInTime = checkInTime;
    }

    public void checkIn() {
        System.out.println("Check-in successful on " + date);
    }
    public void showAttendanceHistory() {
        System.out.println("Date: " + date + " | Time: " + checkInTime);
    }
}
