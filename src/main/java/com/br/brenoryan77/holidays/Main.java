package com.br.brenoryan77.holidays;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

import java.time.LocalDate;
import java.util.Set;

public class Main {

    public static LocalDate ajustarDataVencimento(LocalDate dataVencimento) {
        // Crie um HolidayManager para o calendário brasileiro
        HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.BRAZIL);

        // Obtenha a lista de feriados para o ano desejado
        Set<Holiday> feriados = holidayManager.getHolidays(dataVencimento.getYear());

        LocalDate novaData = dataVencimento;
        while (isFeriado(novaData, feriados) || novaData.getDayOfWeek().getValue() > 5) {
            novaData = novaData.plusDays(1);
        }

        return novaData;
    }

    public static boolean isFeriado(LocalDate data, Set<Holiday> feriados) {
        return feriados.stream().anyMatch(feriado -> feriado.getDate().isEqual(data));
    }

    public static void main(String[] args) {
        LocalDate dataVencimento = LocalDate.of(2023, 10, 15);
        LocalDate novaData = ajustarDataVencimento(dataVencimento);
        System.out.println("Data de vencimento ajustada: " + novaData);
    }
}
