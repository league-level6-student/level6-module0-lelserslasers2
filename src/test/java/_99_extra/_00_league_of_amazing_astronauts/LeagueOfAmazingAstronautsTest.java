package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import _09_intro_to_white_box_testing.models.Order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts loaa = new LeagueOfAmazingAstronauts();
    @Mock
    Rocketship rs;
    @Mock
    Astronaut a;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given

        //when
    	when(a.isTrained()).thenReturn(true);
    	loaa.prepareAstronaut(a);
    	

        //then
    	verify(a, times(1)).train();
    }

    @Test
    void itShouldLaunchRocket() {
    	//given
    	String s = "Mars";
        //when
    	when(a.isTrained()).thenReturn(true);
    	loaa.launchRocket(s);
    	

        //then
    	assertEquals(loaa.rocketship.rocketsIgnited, true);
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
    	String s = "moon";
        //when
    	when(a.isTrained()).thenReturn(true);
    	
    	
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> loaa.launchRocket(s));
        assertEquals(ex.getMessage(), "Destination is unavailable");
        //then
    }

    @Test
    void itShouldThrowNotLoaded() {
    	String s = "Mars";
        //when
    	when(a.isTrained()).thenReturn(true);
    	loaa.rocketship.astronaut = null;
    	
    	
        Throwable ex = assertThrows(IllegalStateException.class, () -> loaa.launchRocket(s));
        assertEquals(ex.getMessage(), "Rocketship is not loaded");
        //then

    }
}