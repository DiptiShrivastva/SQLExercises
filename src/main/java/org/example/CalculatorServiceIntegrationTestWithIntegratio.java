package org.example;

import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CalculatorServiceIntegrationTestWithIntegratio {
    @Test
    public void testSubtractionLogs() {
        LogginService mocklogginService = Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 1;
        int actual = calculatorService.subtraction(6, 5);

        assertEquals(expected, actual);
        verify(mocklogginService).log("6minus5equals:1");

    }

    @Test
    public void testAdditionLogs() {
        LogginService mocklogginService = Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 5;
        int actual = calculatorService.addMethod(3, 2);

        assertEquals(expected, actual);
        // loggingService.log(a+" plus "+b+" equals :"+result);
        verify(mocklogginService).log("3 plus 2 equals :5");
        verify(mocklogginService).login(5);

    }

    @Test
    public void testDivision() {
        LogginService mocklogginService = Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 1;
        int actual = calculatorService.Division(3, 2);
        assertEquals(expected, actual);
        verify(mocklogginService).log("3/2 equals: 1");
        //a+"/"+b+" equals: "+result
        verify(mocklogginService).login(1);
    }

    @Test
    public void testDivisionWithZero() {
        LogginService mocklogginService = Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 0;
        int actual = calculatorService.Division(3, 0);
        assertEquals(expected, actual);
        verify(mocklogginService).logError("infinite");

        //a+"/"+b+" equals: "+result

    }

    @Test
    public void testDivisionZero2() {
        LogginService mocklogginService = Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 0;
        int actual = calculatorService.Division2(3, 0);
        assertEquals(expected, actual);
        verify(mocklogginService).logError("infinite");

        //a+"/"+b+" equals: "+result

    }

}
