package ru.otus.atm;

import java.util.Arrays;

public enum CurrencyNominal {
    CN10(10),
    CN50(50),
    CN100(100),
    CN200(200),
    CN500(500),
    CN1000(1_000),
    CN2000(2_000),
    CN5000(5_000);

    CurrencyNominal(long nominal) {
        this.nominal = nominal;
    }

    private final long nominal;

    public long getNominal() {
        return nominal;
    }

    public static CurrencyNominal getEnumByCode(long nominal) {
        return Arrays.stream(CurrencyNominal.values())
                .filter(cn ->   cn.getNominal() == nominal)
                .findAny()
                .orElseThrow(() -> new RuntimeException("номинала не существует"));
    }
}
