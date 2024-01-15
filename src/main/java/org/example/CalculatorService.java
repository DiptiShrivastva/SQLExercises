package org.example;

public class CalculatorService {
    LogginService loggingService;

    public CalculatorService(LogginService loggingService) {

        this.loggingService = loggingService;
    }

    public int addMethod(int a, int b){

        int result=a+b;

        loggingService.log(a+" plus "+b+" equals :"+result);
        loggingService.login(result);//calling log and loggin method
        return result;

    }
    public int subtraction(int a,int b){

        int result= a-b;
        loggingService.log(a+"minus"+b+"equals:"+result);

        loggingService.login(result);

        return result;


    }
    public int  Division(int a,int b) {

        try {
            if (b == 0) {
                loggingService.logError("infinite");
                return 0;
            }
            int result = (int) (a / b);

            loggingService.log(a + "/" + b + " equals: " + result);
            loggingService.login(result);
            return result;
        } catch (Exception e)
        {
            throw new ArithmeticException();
        }

    }
    public int Division2(int a,int b) {
        int result;
        try {
            result=a/b;
            loggingService.log(a + "/" + b + " equals: " + result);
            loggingService.login(result);
            return result;
        } catch (Exception e)
        {
            loggingService.logError("infinite");
        }
        return 0;
    }

}

}
