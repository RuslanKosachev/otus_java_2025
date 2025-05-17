package ru.otus.atm;

public class AutomatedTellerMachineFactoryImpl extends AutomatedTellerMachineFactory {

    @Override
    public CashBoxImpl createCashBox() {
        return new CashBoxImpl();
    }
}
