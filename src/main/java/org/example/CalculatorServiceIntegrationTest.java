package org.example;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class CalculatorServiceIntegrationTest {
    @Test
    public void testAddMethod() {
        LogginService logginService = new LogginService() {
            @Override
            public void log(String message) {

            }

            @Override
            public void login(int total) {

            }

            @Override
            public void logError(String massage) {

            }
        };
        CalculatorService calculatorService = new CalculatorService(logginService);
        int expected=11;
        int result = calculatorService.addMethod(6, 5);
        assertEquals(11, result);

    }


    @Test
    public void subtraction(){
        LogginService logginService=new LogginService() {
            @Override
            public void log(String message) {

            }

            @Override
            public void login(int total) {

            }

            @Override
            public void logError(String massage) {

            }
        };
        CalculatorService calculatorService=new CalculatorService(logginService);

        int result = calculatorService.subtraction(6, 5);
        int expected=1;
        assertEquals(expected,result);

    }
    @Test
    public void testSubtractionLogs(){
        LogginService mocklogginService= Mockito.mock(LogginService.class);
        CalculatorService calculatorService = new CalculatorService(mocklogginService);
        int expected = 1;
        int actual = calculatorService.subtraction(6,5);

        assertEquals(expected, actual);
        verify(mocklogginService).log("6minus5equals:1");
        verify(mocklogginService).login(1);
        //loggingService.log(a+"minus"+b+"equals:"+result);

    }
}







// int result = calculatorService.subtraction(6, 5);
// assertEquals(1,result);

