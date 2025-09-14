package Test_HW;

abstract class Shape {
    public abstract double calculateArea();

    public abstract double calculatePerimeter();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}
class Main1 {
    public static void main(String[] args){
        Shape[] shapes ={
                new Circle(5.0),
                new Rectangle(4.0,5.0)
        };
        for (Shape shape :shapes){
            double area=shape.calculateArea();
            double perimeter=shape.calculatePerimeter();
            System.out.println("Площадь: " +area +", Периметр "+perimeter);
        }
    }
}