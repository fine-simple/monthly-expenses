package main.model;

import java.time.YearMonth;
import java.util.HashMap;

public class Wallet {
   public final HashMap<YearMonth, Float> budget;

   public Wallet() {
       budget = new HashMap<>();
   }
}
