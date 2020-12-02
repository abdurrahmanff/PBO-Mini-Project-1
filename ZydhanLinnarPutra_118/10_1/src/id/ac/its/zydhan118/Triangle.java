package id.ac.its.zydhan118;

public class Triangle extends Shape {
	private double a;
	private double b;
	private double c;

	public Triangle() {
		super();
		System.out.println("[Triangle]: Default Constructor");
		a = 0.0;
		b = 0.0;
		c = 0.0;
	}

	public Triangle(double a, double b, double c) {
		super();
		System.out.println("[Triangle]: Constructor2");
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Triangle(String color, double a, double b, double c) {
		super(color);
		System.out.println("[Triangle]: Constructor3");
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}

	@Override
	public double getArea() {
		return 0.5 * this.a * this.b;
	}
	
	@Override
	public double getCircumference() {
		return getA() + getB() + getC();
	}
	
	
}
