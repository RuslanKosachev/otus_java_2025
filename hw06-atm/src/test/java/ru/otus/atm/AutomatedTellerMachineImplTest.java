package ru.otus.atm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static ru.otus.atm.CurrencyNominal.*;

import static ru.otus.atm.AutomatedTellerMachineException.CANNOT_BE_ISSUED;
import static ru.otus.atm.AutomatedTellerMachineException.NOT_BALANCE;

class AutomatedTellerMachineImplTest {

    private static AutomatedTellerMachineFactory factory;
    private AutomatedTellerMachineI atm;

    // Метод выполнится один раз, перед всеми тестами
    @BeforeAll
    public static void globalSetUp() {
        factory = new AutomatedTellerMachineFactoryImpl();
    }

    // Метод выполнится перед каждым тестом
    @BeforeEach
    public void before() {
        atm = factory.createAtm();
    }

    @Test
    @DisplayName("addMoneyTest")
    void addMoneyTest() {
        var money10 = Pair.of(CN10, 2L);
        var money50 = Pair.of(CN50, 3L);
        var money100 = Pair.of(CN100, 1L);
        var money200 = Pair.of(CN200, 1L);
        var money500 = Pair.of(CN500, 1L);
        var money1000 = Pair.of(CN1000, 1L);
        var money5000 = Pair.of(CN5000, 1L);
        var exceptedSum = sum(money10, money50, money100, money200, money500, money1000, money5000);

        var actualSum = atm.addMoney(money10.getLeft(), money10.getRight());
        actualSum += atm.addMoney(money50.getLeft(), money50.getRight());
        actualSum += atm.addMoney(money100.getLeft(), money100.getRight());
        actualSum += atm.addMoney(money200.getLeft(), money200.getRight());
        actualSum += atm.addMoney(money500.getLeft(), money500.getRight());
        actualSum += atm.addMoney(money1000.getLeft(), money1000.getRight());
        actualSum += atm.addMoney(money5000.getLeft(), money5000.getRight());

        Assertions.assertThat(actualSum).isEqualTo(exceptedSum);
    }

    @Test
    @DisplayName("balanceTest")
    void balanceTest() {
        Assertions.assertThat(atm.balance()).isEqualTo(0);

        var money10 = Pair.of(CN10, 2L);
        var money50 = Pair.of(CN50, 3L);
        var money100 = Pair.of(CN100, 1L);
        var money200 = Pair.of(CN200, 1L);
        var money500 = Pair.of(CN500, 1L);
        var money1000 = Pair.of(CN1000, 1L);
        var money5000 = Pair.of(CN5000, 1L);
        var exceptedSum = sum(money10, money50, money100, money200, money500, money1000, money5000);

        atm.addMoney(money10.getLeft(), money10.getRight());
        atm.addMoney(money50.getLeft(), money50.getRight());
        atm.addMoney(money100.getLeft(), money100.getRight());
        atm.addMoney(money200.getLeft(), money200.getRight());
        atm.addMoney(money500.getLeft(), money500.getRight());
        atm.addMoney(money1000.getLeft(), money1000.getRight());
        atm.addMoney(money5000.getLeft(), money5000.getRight());

        Assertions.assertThat(atm.balance()).isEqualTo(exceptedSum);
    }

    @Test
    @DisplayName("getMoneyTest")
    void getMoneyTest() {
        var money10 = Pair.of(CN10, 2L);
        var money50 = Pair.of(CN50, 3L);
        var money100 = Pair.of(CN100, 1L);
        var money200 = Pair.of(CN200, 1L);
        var money500 = Pair.of(CN500, 1L);
        var money1000 = Pair.of(CN1000, 1L);
        var money5000 = Pair.of(CN5000, 1L);
        var exceptedSum = sum(money10, money50, money100, money200, money500, money1000, money5000);

        atm.addMoney(money10.getLeft(), money10.getRight());
        atm.addMoney(money50.getLeft(), money50.getRight());
        atm.addMoney(money100.getLeft(), money100.getRight());
        atm.addMoney(money200.getLeft(), money200.getRight());
        atm.addMoney(money500.getLeft(), money500.getRight());
        atm.addMoney(money1000.getLeft(), money1000.getRight());
        atm.addMoney(money5000.getLeft(), money5000.getRight());

        var banknotes = atm.getMoney(exceptedSum);

        long actualSum = 0;
        for (var b : banknotes) {
            actualSum += b.apply((l, r) -> l.getNominal() * r);
        }

        Assertions.assertThat(actualSum).isEqualTo(exceptedSum);
        Assertions.assertThat(atm.balance()).isEqualTo(0);
    }

    @Test
    @DisplayName("getMoneyExceptionNotBalanceTest")
    void getMoneyExceptionNotBalanceTest() {
        var money10 = Pair.of(CN10, 2L);
        long excepted = sum(money10) + 1L;

        atm.addMoney(money10.getLeft(), money10.getRight());

        Assertions.assertThatThrownBy(() -> atm.getMoney(excepted))
                .isInstanceOf(AutomatedTellerMachineException.class)
                .hasMessageContaining(NOT_BALANCE);
    }

    @Test
    @DisplayName("getMoneyExceptionCannotBeIssuedTest")
    void getMoneyExceptionCannotBeIssuedTest() {
        var money10 = Pair.of(CN10, 2L);
        long excepted = sum(money10) - 1L;

        atm.addMoney(money10.getLeft(), money10.getRight());

        Assertions.assertThatThrownBy(() -> atm.getMoney(excepted))
                .isInstanceOf(AutomatedTellerMachineException.class)
                .hasMessageContaining(CANNOT_BE_ISSUED);
    }

    @SafeVarargs
    private Long sum(Pair<CurrencyNominal, Long>... pairs) {
        long sum = 0;
        for (var pair : pairs) {
            sum += pair.apply((l, r) -> l.getNominal() * r);
        }
        return sum;
    }
}
