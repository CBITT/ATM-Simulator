/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazin.atm_simulator;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ATM {

    private final int DEPOSIT_LIMIT = 2000;
    private final int WITHDRAW_LIMIT = 750;
    private int currentBalance;
//    private int remainingDepositLimit;
//    private int remainingWidthdrawLimit;

    protected ATM(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    protected int getCurrentBalance() {
        return currentBalance;
    }

    protected void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    private void updateBalanceLabel(JLabel label) {
        label.setText("$" + getCurrentBalance());
        updateLabelColor(getCurrentBalance(), label);
    }

    private void updateLabelColor(int value, JLabel label) {
        if (value == 0) {
            label.setForeground(Color.RED);
        } else {
            label.setForeground(Color.BLACK);
        }
    }

    private void updateRemainingLabel(JLabel label, int maximumValue, JProgressBar limitBar) {
        label.setText("(Remaining $" + (maximumValue - limitBar.getValue() + ")"));
        updateLabelColor(maximumValue - limitBar.getValue(), label);
    }

    private void appendToLogTable(Object[] row, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(row);
    }

    protected String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private int checkAmountWithinLimit(JProgressBar limitBar, int amount) {
        if (limitBar.getValue() == limitBar.getMaximum()) {
            return -1;
        } else if (limitBar.getValue() + amount > limitBar.getMaximum()) {
            return -2;
        }
        return 0;
    }

    public void printLog(JTable table) {
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int y = 0; y < table.getColumnModel().getColumnCount(); y++) {
                System.out.println(table.getModel().getValueAt(i, y).toString());
            }
        }
    }

    public void deposit(int amount, JLabel currentBalanceLabel, JLabel remainingLimitLabel, JTable table, JProgressBar limitBar) {
        
        if (amount < 1){
            JOptionPane.showMessageDialog(null, "The amount you are trying to deposit is too small. Please try again.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        switch (checkAmountWithinLimit(limitBar, amount)) {
            case 0:
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "The amount you're trying to deposit is too low.",
                            "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                } else {
                    setCurrentBalance(getCurrentBalance() + amount);
                    Object[] row = {getDate(), "Deposit", "$" + Integer.toString(amount), "$" + getCurrentBalance()};
                    appendToLogTable(row, table);
                    updateBalanceLabel(currentBalanceLabel);
                    limitBar.setValue(limitBar.getValue() + amount);
                    updateRemainingLabel(remainingLimitLabel, DEPOSIT_LIMIT, limitBar);
                }
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "You have reached the maximum amount of deposits per day. Please try again tomorrow.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(null, "The amount you are trying to deposit is greater than your remaining deposit limit.\nPlease try again using a smaller amount.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }

    public void withdraw(int amount, JLabel currentBalanceLabel, JLabel remainingLimitLabel, JTable table, JProgressBar limitBar) {

        if (amount < 1){
            JOptionPane.showMessageDialog(null, "The amount you are trying to withdraw is too small. Please try again.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (checkAmountWithinLimit(limitBar, amount)) {
            case 0:
                if (amount > getCurrentBalance()) {
                    JOptionPane.showMessageDialog(null, "The amount you are trying to withdraw is greater than your current balance.",
                            "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                } else {
                    setCurrentBalance(getCurrentBalance() - amount);
                    Object[] row = {getDate(), "Withdraw", "$" + Integer.toString(amount), "$" + getCurrentBalance()};
                    appendToLogTable(row, table);
                    updateBalanceLabel(currentBalanceLabel);
                    limitBar.setValue(limitBar.getValue() + amount);
                    updateRemainingLabel(remainingLimitLabel, WITHDRAW_LIMIT, limitBar);
                }
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "You have reached the maximum amount of withdrawals per day. Please try again tomorrow.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "The amount you are trying to withdraw is greater than your remaining withdrawal limit.\nPlease try again using a smaller amount.",
                        "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }

}
