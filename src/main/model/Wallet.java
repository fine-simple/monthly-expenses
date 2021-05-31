package main.model;

import java.time.YearMonth;
import java.util.Map;
import java.util.TreeMap;

public class Wallet {
   public final Map<YearMonth, Float> budget;

   public Wallet() {
       budget = new TreeMap<>();
   }
}
