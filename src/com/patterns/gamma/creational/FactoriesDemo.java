package com.patterns.gamma.creational;

class Point {
    private double x, y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

//    public static com.patterns.gamma.creational.Point newCartesianPoint(double x, double y) {
//        return new com.patterns.gamma.creational.Point(x, y);
//    }
//
//    public static com.patterns.gamma.creational.Point newPolarPoint(double rho, double theta) {
//        return new com.patterns.gamma.creational.Point(rho * Math.cos(theta),
//                rho * Math.sin(theta));
//    }

    static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta),
                    rho * Math.sin(theta));
        }
    }
}

class FactoriesDemo {
    Point point = Point.Factory.newPolarPoint(2, 3);
}
