package myTest.test;

import java.math.BigDecimal;
import java.util.Date;

public class Test2 {
public static void main(String[] args) {
/*	int k=4;
	Date aDate = (k>4)? new A():new B();*/
	
	
	
	BigDecimal aaa = new  BigDecimal("331.033372").setScale(2,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(100D)).setScale(0,BigDecimal.ROUND_DOWN);
	System.out.println(aaa.intValue());
	
}
}
class A extends Date{};
class B extends Date{};
