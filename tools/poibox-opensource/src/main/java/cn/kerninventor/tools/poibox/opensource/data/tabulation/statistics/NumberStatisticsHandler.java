package cn.kerninventor.tools.poibox.opensource.data.tabulation.statistics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kern
 * @date 2020/5/25 16:12
 * @description
 */
public class NumberStatisticsHandler {

    public void newStatisticsInstanceOf(NumberStatistics numberStatistics) {


    }

    public Number toStatistics(NumberStatisticsMode mode, Number... numbers) {
        switch (mode) {
            case SUM:
                return sum(numbers);
            case AVG:
                return avg(numbers);
            case VARIANCE:
                return variance(numbers);
            case STANDARD_DEVIATION:
                return standardDeviation(numbers);
            case MAX:
                return max(numbers);
            case MIN:
                return min(numbers);
            case MEDIAN:
                return median(numbers);
            default:
                return null;
        }
    }

    public double sum(Number... numbers) {
        return Arrays.stream(numbers).mapToDouble(Number::doubleValue).sum();
    }

    public double avg(Number... numbers) {
        return Arrays.stream(numbers).mapToDouble(Number::doubleValue).average().getAsDouble();
    }

    public double variance(Number... numbers) {
        double avg = avg(numbers);
        double variance = 0;
        for (Number number : numbers) {
            variance += (number.doubleValue() - avg) * (number.doubleValue() - avg) / numbers.length;
        }
        return variance;
    }

    public double standardDeviation(Number... numbers) {
        return Math.sqrt(variance(numbers));
    }

    public Number max(Number... numbers) {
        return Arrays.stream(numbers).max(numberReverseOrder()).get();
    }

    public Number min(Number... numbers) {
        return Arrays.stream(numbers).min(numberNaturalOrder()).get();
    }

    public Number median(Number... numbers) {
        List<Number> numberList = Arrays.stream(numbers).sorted(numberNaturalOrder()).collect(Collectors.toList());
        int size = numberList.size();
        if (size % 2 == 1) {
            double sum = Arrays.stream(numbers).mapToDouble(Number::doubleValue).sum();
            return sum / size;
        } else {
            double d1 = numbers[(size / 2) - 1].doubleValue();
            double d2 = numbers[size / 2].doubleValue();
            double sub = Double.sum(d1, -d2);
            return sub / 2;
        }
    }

    private Comparator<Number> numberNaturalOrder() {
        return (n1, n2) -> {
            if (n1.doubleValue() > n2.doubleValue()) {
                return 1;
            } else if (n1.doubleValue() < n2.doubleValue()) {
                return -1;
            } else {
                return 0;
            }
        };
    }

    private Comparator<Number> numberReverseOrder() {
        return (n1, n2) -> {
            if (n1.doubleValue() > n2.doubleValue()) {
                return -1;
            } else if (n1.doubleValue() < n2.doubleValue()) {
                return 1;
            } else {
                return 0;
            }
        };
    }
}
