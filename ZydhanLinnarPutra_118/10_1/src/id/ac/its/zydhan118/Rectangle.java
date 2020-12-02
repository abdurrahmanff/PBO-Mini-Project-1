package id.ac.its.zydhan118;

public class Rectangle extends Shape {
	
	// properti spesifik untuk kelas Rectangle
	protected double height;
	protected double width;
	
	public Rectangle() {
		super();
		
		System.out.println("[Rectangle]: Default Constructor");
		this.height = 0.0;
		this.width = 0.0;
	}
	
	public Rectangle(double height, double width) {
		super();
		System.out.println("[Rectangle]: Constructor2");
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(String color, double height, double width) {
		super(color);
		System.out.println("[Rectangle]: Constructor 3");
		this.height = height;
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double getArea() {
		return this.width * this.height;
	}
	
	@Override
	public double getCircumference() {
		return 2 * (getWidth() + getHeight());
	}
		
}
