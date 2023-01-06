package com.patterns.gamma.behavioral;

/*
* Allows adding extra behaviours to an entire class hierarchy. In short, the Visitor patterns allows a
* component (a Visitor) to traverse the entire inheritance hierarchy by propagating (adding an intrusive method)
* a single visit() method, throughout the entire hierarchy.
* */

interface ExpressionVisitor {
    void visit(DoubleExpression expression);
    void visit(AdditionExpression expression);
}

abstract class Expression {
//    // This is intrusive! and breaks to Open Closed principle as it requires adding the print method
//    // to all inheriting classes regardless of whether they implement this method or not
//    public abstract void print(StringBuilder builder);
    public abstract void accept(ExpressionVisitor visitor);
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

//    @Override
//    public void print(StringBuilder builder) {
//        builder.append(value);
//    }
}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

//    @Override
//    public void print(StringBuilder builder) {
//        builder.append("(");
//        left.print(builder);
//        builder.append("+");
//        right.print(builder);
//        builder.append(")");
//    }
}

//class ExpressionPrinter {
//    // Using reflection to determine the type of expression
//    public static void print(Expression expression, StringBuilder builder) {
//        if (expression.getClass() == DoubleExpression.class) {
//            builder.append(((DoubleExpression)expression).value);
//        }
//        else if (expression.getClass() == AdditionExpression.class) {
//            AdditionExpression addition = (AdditionExpression) expression;
//            builder.append("(");
//            print(addition.left, builder);
//            builder.append("+");
//            print(addition.right, builder);
//            builder.append(")");
//        }
//    }
//}

class ExpressionPrinter implements ExpressionVisitor {
    private StringBuilder builder = new StringBuilder();

    @Override
    public void visit(DoubleExpression expression) {
        builder.append(expression.value);
    }

    @Override
    public void visit(AdditionExpression expression) {
        builder.append("(");
        expression.left.accept(this);
        builder.append("+");
        expression.right.accept(this);
        builder.append(")");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor {
    public double result;

    @Override
    public void visit(DoubleExpression expression) {
        result = expression.value;
    }

    @Override
    public void visit(AdditionExpression expression) {
        expression.left.accept(this);
        double a = result;
        expression.right.accept(this);
        double b = result;
        result = a + b;
    }
}

class VisitorDemo {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );
//        StringBuilder builder = new StringBuilder();
//        expression.print(builder);
//        ExpressionPrinter.print(expression, builder);

        ExpressionPrinter printer = new ExpressionPrinter();
        printer.visit(expression);
        System.out.println(printer);

        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.visit(expression);
        System.out.println(printer + " = " + calculator.result);
    }
}
