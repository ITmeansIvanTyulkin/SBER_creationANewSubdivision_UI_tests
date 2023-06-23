package date;

import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Date {

    // Метод получения случайного количества месяцев.
    public static int amountOfMonths() {
        int randomNumber = RandomUtils.nextInt(1, 10);
        return randomNumber;
    }

    // Метод получения текущей даты.
    public static String todayDateAndTime() {
        Calendar calendar = new GregorianCalendar();
        java.util.Date date = calendar.getTime();
        return date.toString();
    }

    // Метод прибавления к текущей дате случайного количества месяцев.
    public static String dateAndTimePlusRandomAmountOfMonths() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, amountOfMonths());
        java.util.Date date = calendar.getTime();
        return date.toString();
    }

    // Метод для поля 'Период действия с...' календаря в форме регистрации нового сотрудника.
    public static String formatDateFrom() {
        String inputDate = todayDateAndTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        try {
            java.util.Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);
            return outputDate;
        }
        catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        return null;
    }

    // Метод для поля 'Период действия по...' календаря в форме регистрации нового сотрудника.
    public static String formatDateTo() {
        String inputDate = dateAndTimePlusRandomAmountOfMonths();
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        try {
            java.util.Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);
            return outputDate;
        }
        catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        return null;
    }
}