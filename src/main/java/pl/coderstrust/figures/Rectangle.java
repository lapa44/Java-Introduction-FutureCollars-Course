package pl.coderstrust.figures;

public class Rectangle implements Figure {

    private double a, b;

    public Rectangle(double a, double b) {
        if (a <= 0) {
            throw new IllegalArgumentException("Rectangle side A cannot be lower than or equal to zero.");
        }
        if (b <= 0) {
            throw new IllegalArgumentException("Rectangle side B cannot be lower than or equal to zero.");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculateArea() {
        return a * b;
    }
}
