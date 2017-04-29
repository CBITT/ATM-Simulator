/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazin.atm_simulator;

/**
 *
 * @author yazin
 */
public class ATMSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        SignUpForAccountView signUp = new SignUpForAccountView();
//        signUp.setLocationRelativeTo(null);
//        signUp.setVisible(true);
        
        ATMInsertCard atmInsertCard = new ATMInsertCard();
        atmInsertCard.setLocationRelativeTo(null);
        atmInsertCard.setVisible(true);
    }
    
}
