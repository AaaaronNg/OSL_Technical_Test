package org.example;

import java.util.Date;

public class Timer {

    private static Long snapshotTime;
    Date now;

    public Timer() {
//        now = new Date();
//        Timer.snapshotTime = 3030000003;
    }

    public void setSnapshotTime(Long snapshotTime) {
        Timer.snapshotTime = snapshotTime;
    }

    public Long getSnapshotTime() {
        return snapshotTime;
    }

    public Integer timeAgo(long pastDate){
        Date currentDate = new Date();
        long milliSecPerMinute = 60 * 1000;

        long msExpired = currentDate.getTime() - pastDate;

        return Math.round(msExpired/1000);
    }
}
