package com.driver;

/* Some Important keyword definations
final: The final keyword is a non-access modifier used for classes, attributes and methods, which makes them non-changeable 
(impossible to inherit or override).
this: the this keyword refers to the current object in a method/constructor.

*/

public class BankAccount {
    private final String name; //non-changeable(Fixed username)
    private double balance; //changeable(bcoz of amount adding & withdrawing)
    private final double minBalance; //non-changeable(fixed balance amt after withdrwaing/adding)

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        //ex: digits = 5, sum = 23 --> accountNo = 99500 => 23
        if (sum < 0 || sum > 9*digits) {
           throw new Exception("Account Number can not be generated");
        } else {
            //generating account num of sumsize
            StringBuilder accountNo = new StringBuilder();
            while (sum > 9) {
                accountNo.append("9");
                sum -= 9;
            }

            while (accountNo.length() < digits) {
                accountNo.append(sum);
                sum = 0;
            }
            return accountNo.toString();
        }

    }

    public void deposit(double amount) { //add amount to balance
        // We are tring to depositing amount, so we check if the amount is > 0 then we add with currentBalance
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        // here we are trying to withdrawing the amount, we have to take care minbalance also. 
        // we check if the withdrawing amount is >= minBalance
        if (balance - amount >= minBalance) {
            balance -= amount;
        } else {
            throw new Exception("Insufficient Balance");
        }
    }

    // Getter
    public String getName() {
        return name;
    }
    
    // Getter
    public double getMinBalance() {
        return minBalance;
    }
    
    // Getter
    public double getBalance() {
        return balance;
    }

}