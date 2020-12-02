package id.ac.its.yusril160;

public class Square {
	protected double side;

	public Square(double side) {
		super();
		this.side = side;
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}
	
	public double getArea() {
		return this.side * this.side;
	}
	
	public double getCircumference() {
		return 4 * this.side;
	}
}
