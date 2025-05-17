package ru.otus.atm;

import java.util.Objects;

abstract class AutomatedTellerMachineFactory {

     abstract CashBoxI createCashBox();

     public AutomatedTellerMachineI createAtm() {
          return new AutomatedTellerMachineImpl(Objects.requireNonNull(createCashBox()));
     }
}
