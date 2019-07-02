package ClassificationTree;

public class Fruit implements Trainable {
	
	private String Color;
	private String Name;
	private int Diameter;
	
	public Fruit(String color, int diameter, String name) {
		this.Color = color;
		this.Name = name;
		this.Diameter = diameter;		
	}
	
	public String GetName() {
		return this.Name;
	}
	
	public String GetColor() {
		return this.Color;
	}
	
	public int GetDiameter() {
		return this.Diameter;
	}

	@Override
	public String GetTarget() {
		// TODO Auto-generated method stub
		return GetName();
	}

} 
