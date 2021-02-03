package multithreading.resources;

import lombok.*;
import lombok.extern.java.Log;

import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Log
public class ResourceClass {
    private Map<String, Integer> commonResource = new LinkedHashMap<>();
    private int data;
    private long wastedTime;
    public void incrementData(){
        this.data++;

    }

    @Override
    public String toString() {
        return "ResourceClass: " +
                "data= " + data + ". Time spent to increment: " + wastedTime +
                " nanoseconds";
    }
}
