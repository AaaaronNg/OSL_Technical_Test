package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedDate {

    public FormattedDate() {
    }

    public String getDateNow(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        String formattedDate = date.format(format);
        return formattedDate;
    }
}
