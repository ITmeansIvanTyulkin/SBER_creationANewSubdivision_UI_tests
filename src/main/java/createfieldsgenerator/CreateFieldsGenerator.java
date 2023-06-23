package createfieldsgenerator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class CreateFieldsGenerator {

    // Генераторы данных для формы регистрации нового подразделения.

    // Инициализирую массив данных для поля 'Полное наименование'.
    static final String[] COMPANIES = {
            "ОАО Лондонский офис",            // 1.
            "ООО Токийский офис",             // 2.
            "ООО Нью-Йоркский офис",          // 3.
            "ЗАО Вильнюсовский офис",         // 4.
            "ОАО Пражский офис",              // 5.
            "ПАО Римский офис",               // 6.
            "ООО Нотрдамский офис",           // 7.
            "ОАО Сицилийский офис",           // 8.
            "ЗАО Иерусалимский офис",         // 9.
            "ПАО Тбилисский офис"             // 10.
    };

    // Инициализирую массив данных для поля 'Сокращённо'.
    static final String[] ABBREVIATED = {
            "Лондон энд санс",                  // 1.
            "Токио мув",                        // 2.
            "Нью-Йорк биз",                     // 3.
            "Вильнюс каст",                     // 4.
            "Прага офис",                       // 5.
            "Рим уффичо",                       // 6.
            "Нотрдам де Пари",                  // 7.
            "Сицилия козе",                     // 8.
            "Иерусалим хаим",                   // 9.
            "Тбилиси ген"                       // 10.
    };

    // Создаю генератор ГОСБ.
    public static String gosb() {
        int length = 4;

        boolean useLetters = false;
        boolean useNumbers = true;

        String generatedGosb = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedGosb.toString();
    }

    // Создаю генератор названия организации. К нему будут подставляться рандомно варианты имён из массива String[] COMPANIES.
    public static String companies() {
        int index = RandomUtils.nextInt(0, COMPANIES.length);
        return COMPANIES[index];
    }

    // Создаю генератор сокращённого названия организации. К нему будут подставляться рандомно варианты имён из массива String[] ABBREVIATED.
    public static String abbreviated() {
        int index = RandomUtils.nextInt(0, ABBREVIATED.length);
        return ABBREVIATED[index];
    }
}