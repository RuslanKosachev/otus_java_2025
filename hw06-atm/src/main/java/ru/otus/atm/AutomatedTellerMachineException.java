package ru.otus.atm;

public class AutomatedTellerMachineException extends RuntimeException {
    public static final String NOT_BALANCE  = "Недостаточно средств в банкомате";
    public static final String CANNOT_BE_ISSUED  = "Данная сумма не может быть выдана";

    public AutomatedTellerMachineException(String message) {
        super(message);
    }
}
