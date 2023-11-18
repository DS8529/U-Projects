package lukia2.businnesLogic;

public class Account implements operationsAccount{


    private double balance;
    private String phone_Number;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    /**
     * @return the saldo
     */


    @Override
    public void deposit(double balance) {
        // TODO Auto-generated method stub

    }


    @Override
    public void withdrawals(double balance) {
        // TODO Auto-generated method stub

    }


    @Override
    public void check_Balance(String phone_Number) {
        // TODO Auto-generated method stub

    }

}
