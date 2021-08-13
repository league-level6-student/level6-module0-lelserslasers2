package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double wage = 15.5;
    	int hrs = 20;
    	double expected = 310;

        //when
    	double actual = payroll.calculatePaycheck(wage, hrs);

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int mi = 10;
    	double expected = 5.75;

        //when
    	double actual = payroll.calculateMileageReimbursement(mi);

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String name = "Joe";
    	double wage = 15.5;
    	String expected = "Hello Joe, We are pleased to offer you an hourly wage of 15.5";

        //when
    	String actual = payroll.createOfferLetter(name, wage);

        //then
    	assertEquals(expected, actual);
    }

}