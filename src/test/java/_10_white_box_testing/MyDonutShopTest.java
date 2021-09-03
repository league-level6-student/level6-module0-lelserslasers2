package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop mds;
    
    @Mock
    PaymentService ps;
    @Mock
    DeliveryService ds;
    @Mock
    BakeryService bs;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	mds = new MyDonutShop(ps,ds,bs);
    	mds.openForTheDay();
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	String customerPhoneNumber = "CUSTOMER_PHONE_NUMBER";
        Order order = new Order("CUSTOMER_NAME",
                customerPhoneNumber,
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        when(ps.charge(order)).thenReturn(true);
        bs.setDonutsRemaining(50);
        when(bs.getDonutsRemaining()).thenReturn(50);
        System.out.println(bs.getDonutsRemaining());

        //when
        mds.takeOrder(order);

        //then
        verify(ps, times(1)).charge(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	String customerPhoneNumber = "CUSTOMER_PHONE_NUMBER";
        Order order = new Order("CUSTOMER_NAME",
                customerPhoneNumber,
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        when(ps.charge(order)).thenReturn(true);
        when(bs.getDonutsRemaining()).thenReturn(0);

        //when
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> mds.takeOrder(order));
        assertEquals(ex.getMessage(), "Insufficient donuts remaining");
        //then
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
    	//given
    	String customerPhoneNumber = "CUSTOMER_PHONE_NUMBER";
        Order order = new Order("CUSTOMER_NAME",
                customerPhoneNumber,
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        when(ps.charge(order)).thenReturn(true);
        when(bs.getDonutsRemaining()).thenReturn(50);
        mds.closeForTheDay();

        //when
        Throwable ex = assertThrows(IllegalStateException.class, () -> mds.takeOrder(order));
        assertEquals(ex.getMessage(), "Sorry we're currently closed");
        //then
    }

}