package carRentalSystem.models;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public Duration getDuration(){
        return Duration.between(startTime, endTime);
    }

    public boolean overlapsWith(TimeSlot other){
        return this.startTime.isBefore(other.endTime) &&
               this.endTime.isAfter(other.startTime);
    }
}
