package solid.design.principles;

/**
 * Split interface to small interface to avoid redundant implementation
 */
public class InterfaceSegregationPrincipal {

}

class Document {

}

//Violate Interface Segregation Principal
interface Machine {
    void print(Document d);
    void scan(Document d);
    void fax(Document d);
}

//It's ok cause modern printer has all function
class ModernPrinter implements Machine {

    @Override
    public void print(Document d) {
        //Do something
    }

    @Override
    public void scan(Document d) {
        //Do something
    }

    @Override
    public void fax(Document d) {
        //Do something
    }
}

//Old printer has only print function, but it has to implement all function, so scan and fax do nothing
//Or this 2 function have to throws exception, so we have to throw exception in function of interface, that's not good implement
class OldPrinter implements Machine {

    @Override
    public void print(Document d) {
        //Do something
    }

    @Override
    public void scan(Document d) {
        //Do nothing
    }

    @Override
    public void fax(Document d) {
        //Do nothing
    }
}



//ISP spit to more interface
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

class PrinterAndScaner implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}



//Decorator Design Pattern
interface MultipleFunctionMachine extends Printer, Scanner {}

class MultipleFunctionMachineImpl implements MultipleFunctionMachine {

    private Printer printer;
    private Scanner scanner;

    public MultipleFunctionMachineImpl(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}
