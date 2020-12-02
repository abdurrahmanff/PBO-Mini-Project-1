package id.ac.its.zydhan118;
import java.lang.Math;

public class Circle extends Shape {
	private double radius;

	public Circle() {
		super();
		System.out.println("[Circle]: Default Constructor");
		this.radius = 0.0;
	}

	public Circle(double radius) {
		super();
		System.out.println("[Circle]: Constructor2");
		this.radius = radius;
	}

	public Circle(String color, double radius) {
		super(color);
		System.out.println("[Circle]: Constructor3");
		this.radius = radius;
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}
	
	@Override
	public double getCircumference() {
		return Math.PI * 2 * getRadius();
	}
}
