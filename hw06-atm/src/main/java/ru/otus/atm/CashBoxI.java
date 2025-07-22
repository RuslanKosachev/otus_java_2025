package ru.otus.atm;

import org.apache.commons.lang3.tuple.Pair;

public interface CashBoxI {
    Long addSum(CurrencyNominal nominal, Long banknotes);

    Long balance();

    Iterable<Pair<CurrencyNominal, Long>> getSum(Long amount) throws AutomatedTellerMachineException;
}
