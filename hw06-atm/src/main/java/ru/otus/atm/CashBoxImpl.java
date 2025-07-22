package ru.otus.atm;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static ru.otus.atm.AutomatedTellerMachineException.CANNOT_BE_ISSUED;
import static ru.otus.atm.AutomatedTellerMachineException.NOT_BALANCE;

public class CashBoxImpl implements CashBoxI {
    private static final Logger logger = LoggerFactory.getLogger(CashBoxImpl.class);

    private final NavigableMap<Long, Long> cell = new TreeMap<>();

    @Override
    public Long addSum(CurrencyNominal nominal, Long banknotes) {
        return cell.compute(nominal.getNominal(), (n, b) -> (b == null) ? banknotes : b + banknotes)
                * nominal.getNominal();
    }

    @Override
    public Long balance() {
        long balance = 0L;
        for (Map.Entry<Long, Long> entry : cell.entrySet()) {
            balance += entry.getValue() * entry.getKey();
        }

        logger.info("balance() balance: {}", balance);
        return balance;
    }

    public Iterable<Pair<CurrencyNominal, Long>> getSum(Long sum) throws AutomatedTellerMachineException {
        Set<Pair<CurrencyNominal, Long>> result = new HashSet<>();

        logger.info("get() sum:{}", sum);
        if (balance() < sum) {
            logger.info("get() balance() < sum");
            throw new AutomatedTellerMachineException(NOT_BALANCE);
        }

        //NavigableMap<Long, Long> processingCell =  new TreeMap<Long, Long>(Comparator.reverseOrder());
        //NavigableMap<Long, Long> processingCell =  new TreeMap<Long, Long>(Long::compare);
        NavigableMap<Long, Long> processingCell =  new TreeMap<>((v1, v2) -> {return v2.compareTo(v1);});
        processingCell.putAll(cell);

        var newCell =  new TreeMap<>(cell);
        logger.info("get() start... cell:{}", cell);
        logger.info("get() start... newCell:{}", newCell);
        logger.info("get() start... processingCell:{}", processingCell);

        // long count;
        long remainder = sum;
        logger.info("get() start... remainder:{}", remainder);
        for (Map.Entry<Long, Long> entry : processingCell.entrySet()) {
            if (remainder == 0) {
                logger.info("get() break remainder:0");
                break;
            }
            if (remainder < entry.getKey()) {
                logger.info("get() continue remainder:{} < Key:{}", remainder, entry.getKey());
                continue;
            }

            long count = remainder / entry.getKey();
            logger.info("get() count{} = remainder:{}/key:{}  value:{}", count, remainder, entry.getKey(), entry.getValue());

            if (count >= entry.getValue()) {
                logger.info("get() count:{} >= Value:{} key:{}", count, entry.getValue(), entry.getKey());
                count = entry.getValue();
                remainder -= count * entry.getKey();
                newCell.put(entry.getKey(), 0L);
            } else if (count < entry.getValue()) {
                logger.info("get() count:{} < Value:{} key:{}", count, entry.getValue(), entry.getKey());
                remainder -= count * entry.getKey();
                newCell.put(entry.getKey(), entry.getValue() - count);
            }
            logger.info("get() remainder:{}", remainder);

            result.add(Pair.of(CurrencyNominal.getEnumByCode(entry.getKey()), count));
        }

        logger.info("get() stop... remainder:{}", remainder);

        if (remainder > 0) {
            throw new AutomatedTellerMachineException(CANNOT_BE_ISSUED);
        }

        cell.putAll(newCell);
        logger.info("get() stop... cell:{}", cell);
        logger.info("get() stop... balance:{}", balance());
        logger.info("get() stop... result:{}", result);


        return result;
    }
}
