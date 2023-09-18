package com.nghia.productservice;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

  @Test
  void contextLoads() {
  }

  public static void main(String[] args) {
    Double price = 10000D;
    System.out.print(String.format(Locale.US, "%,.0f", price));
  }
}
