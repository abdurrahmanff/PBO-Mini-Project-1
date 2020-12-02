package id.ac.its.fauzan231;

public class Triangle extends Shape {
	private double a;
	private double b;
	private double c;
	
	public Triangle() {
		super ();
		this.a = 0;
		this.b = 0;
		this.c = 0;
		System.out.println("[Triangle]: Default Constructor");
	}
	
	public Triangle(double a, double b, double c) {
		super ();
		this.a = a;
		this.b = b;
		this.c = c;
		System.out.println("[Triangle]: Constructor 1");
	}
	
	public Triangle(String color, double a, double b, double c) {
		super(color);
		this.a = a;
		this.b = b;
		this.c = c;
		System.out.println("[Triangle]: Constructor 2");
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
		return (getA() * getB())/2;
	}
	
	@Override
	public double getCircumference() {
		return getA() + getB() + getC();
	}
	
}
