package itis.inf304.taskhunter.util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    public static String formatDateTime(LocalDateTime dateTime) {
        LocalDate currentDate = LocalDate.now();

        LocalDate date = dateTime.toLocalDate();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String formattedDate;
        if (date.equals(currentDate)) {
            formattedDate = "сегодня " + dateTime.format(timeFormatter);
        } else if (date.equals(currentDate.minusDays(1))) {
            formattedDate = "вчера " + dateTime.format(timeFormatter);
        } else {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM HH:mm", new Locale("ru"));
            formattedDate = dateTime.format(dateFormatter);
        }

        return formattedDate;
    }

}
