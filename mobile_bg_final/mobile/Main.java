package mobile;

import mobile.search.CaseInsensitiveFilter;
import mobile.search.ExactValueFilter;
import mobile.search.Filter;
import mobile.search.RangeFilter;
import mobile.vehicles.Car;
import mobile.parser.*;
import mobile.listings.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Тази част е новото - QuerySearcher
        QueryTester tester = new QueryTester();
        QuerySearcher searcher = new QuerySearcher();
        tester.test(searcher);

    }

}
