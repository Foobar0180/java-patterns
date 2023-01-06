package com.patterns.solid;

class Document {

}

//interface Machine {
//    void print(com.patterns.solid.Document d);
//
//    void fax(com.patterns.solid.Document d);
//
//    void scan(com.patterns.solid.Document d);
//}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface Fax {
    void fax(Document d);
}

interface MultiFunctionDevice extends Printer, Scanner, Fax {

}

class FaxMachine implements Fax{

    @Override
    public void fax(Document d) {

    }
}

class PhotoCopier implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class AllInOnePrinter implements MultiFunctionDevice {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class DotMatrixPrinter implements Printer {

    @Override
    public void print(Document d) {

    }

//    @Override
//    public void fax(com.patterns.solid.Document d) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void scan(com.patterns.solid.Document d) {
//        throw new UnsupportedOperationException();
//    }
}

class ISPDemo {
}
