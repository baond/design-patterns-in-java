package pattern.bridge;

/**
 * Bridge pattern using to decrease dependency between abstraction and implementation
 * thus, decrease number of implementation unnecessary
 */
public class BridgeDemo {
    public static void main(String[] args) {
        Bank vietcomAccount = new VietcomBank(new CheckingAccount());
        vietcomAccount.openAccount();

        Bank tpbankAccount = new TpBank(new SavingAccount());
        tpbankAccount.openAccount();
    }
}

interface Account {
    void openAccount();
}

class CheckingAccount implements Account {

    @Override
    public void openAccount() {
        System.out.println("Checking account");
    }
}

class SavingAccount implements Account {

    @Override
    public void openAccount() {
        System.out.println("Saving account");
    }
}

abstract class Bank {
    protected Account account;

    public Bank(Account account) {
        this.account = account;
    }

    public abstract void openAccount();
}

class TpBank extends Bank {

    public TpBank(Account account) {
        super(account);
    }

    @Override
    public void openAccount() {
        System.out.print("Open TpBank account with type ");
        account.openAccount();
    }
}

class VietcomBank extends Bank {

    public VietcomBank(Account account) {
        super(account);
    }

    @Override
    public void openAccount() {
        System.out.print("Open VietcomBank account with type ");
        account.openAccount();
    }
}