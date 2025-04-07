package edu.phystech.hw2;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class ToUpperCaseOperator implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        return s.toUpperCase();
    }
}

// Возвращает модуль максимума из двух модулей чисел
class AbsMaxOperator implements BinaryOperator<Integer> {

    @Override
    public Integer apply(Integer integer, Integer integer2) {
        int integerAbs = (integer < 0) ? -integer : integer;
        int integer2Abs = (integer2 < 0) ? -integer2 : integer2;
        return Math.max(integer2Abs,integerAbs);
    }
}

class StringLengthMoreThan5 implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.length() > 5;
    }
}


// Проверяет, является ли число квадратом
class IsNumberASquareOfAnotherNumber implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        if (integer < 0) return false;
        int sqrtInteger = (int) Math.sqrt(integer);
        return integer == sqrtInteger * sqrtInteger;
    }
}

// Возвращает четные числа, начиная с from включительно, если в from нечетное число, то начиная с первого четного с from
class EvenNumberSupplier implements Supplier<Integer> {
    private int current;

    public EvenNumberSupplier(int from) {
        this.current = (from % 2 == 0) ? from : from + 1;
    }

    @Override
    public Integer get() {
        int result = current;
        current +=2;
        return result;
    }
}

public class FunctionalInterfacesTest {

    @Test
    public void unaryOperatorTest() {
        var result = new ArrayList<>(List.of("abC", "edf"));
        result.replaceAll(new ToUpperCaseOperator());
        Assertions.assertEquals(List.of("ABC", "EDF"), result);
    }

    @Test
    public void binaryOperatorTest() {
        var result = Stream.of(2, 3, 1, -10).reduce(4, new AbsMaxOperator());
        Assertions.assertEquals(10, result);
    }

    @Test
    public void predicateTest() {
        Assertions.assertEquals(
                Stream.of("a", "bb", "ccc", "1234567", "aaaaaaaaa").filter(new StringLengthMoreThan5()).toList(),
                List.of("1234567", "aaaaaaaaa")
        );

        Assertions.assertEquals(
                Stream.of(1, 4, 5, 10, 16, 25).filter(new IsNumberASquareOfAnotherNumber()).toList(),
                List.of(1, 4, 16, 25)
        );
    }

    @Test
    public void supplierTest() {
        var evenNumberSupplier = new EvenNumberSupplier(0);
        Stream.of(0, 2, 4, 6, 8, 10).forEach(number -> Assertions.assertEquals(number, evenNumberSupplier.get()));

        var anotherSupplier = new EvenNumberSupplier(11);
        Stream.of(12, 14, 16, 18, 20, 22).forEach(number -> Assertions.assertEquals(number, anotherSupplier.get()));
    }

}
