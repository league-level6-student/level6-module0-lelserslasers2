package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import _07_intro_to_mocking.models.Engine;
import _07_intro_to_mocking.models.GasTank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver dd;
    
    @Mock
    CellPhone cp;
    @Mock
    Car car;

    @BeforeEach
    void setUp() {
        //The following line instantiates all of the mocks in the given class.  In this case, this class.
        MockitoAnnotations.openMocks(this);

        dd = new DeliveryDriver("Joe", car, cp);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expected = true;

        //when
    	boolean actual = dd.wasteTime();

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expected = true;

        //when
    	boolean actual = dd.refuel(85);

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	String number = "555-555-5555";
    	boolean expected = true;

        //when
    	boolean actual = dd.contactCustomer(number);

        //then
    	assertEquals(expected, actual);
    }

}