package ru.otus.atm;

import org.apache.commons.lang3.tuple.Pair;


public interface AutomatedTellerMachineI {

    Long addMoney(CurrencyNominal nominal, Long banknotes);

    Long balance();

    Iterable<Pair<CurrencyNominal, Long>> getMoney(Long amount) throws AutomatedTellerMachineException;
}
