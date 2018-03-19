package yazin.atm_simulator;

import static org.junit.Assert.*;

public class ATMTest {
    @org.junit.Test
    public void getCurrentBalance() throws Exception {
        ATM atm = new ATM(12345);
        assertEquals(12345, atm.getCurrentBalance());
    }

    @org.junit.Test
    public void setCurrentBalance() throws Exception {
    }

    @org.junit.Test
    public void getDate() throws Exception {
    }

    @org.junit.Test
    public void printLog() throws Exception {
    }

    @org.junit.Test
    public void deposit() throws Exception {
    }

    @org.junit.Test
    public void withdraw() throws Exception {
    }

}