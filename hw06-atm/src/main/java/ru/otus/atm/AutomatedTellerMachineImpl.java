package ru.otus.atm;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AutomatedTellerMachineImpl implements AutomatedTellerMachineI {
    private static final Logger logger = LoggerFactory.getLogger(AutomatedTellerMachineImpl.class);

    private final CashBoxI cashBoxes;

    public AutomatedTellerMachineImpl(CashBoxI cashBoxes) {
        this.cashBoxes = cashBoxes;
    }

    @Override
    public Long addMoney(CurrencyNominal nominal, Long banknotes) {
        return cashBoxes.addSum(nominal, banknotes);
    }

    @Override
    public Long balance() {
        return cashBoxes.balance();
    }

    public Iterable<Pair<CurrencyNominal, Long>> getMoney(Long sum) throws AutomatedTellerMachineException {
        return cashBoxes.getSum(sum);
    }
}
