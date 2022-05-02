package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    /** The date format. */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Convert string to date.
     *
     * @param dateString the date string
     * @return localDate the parsed data
     * 
     * @throws DateTimeParseException when there is an error parsing the date
     */
    public static LocalDate stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Converts a LocalDate object to String
     *
     * @param date the date
     * @return the string
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Gets the date format.
     *
     * @return the date format
     */
    public static String getDateFormat() {
        return DATE_FORMAT.toLowerCase();
    }
    
}
