package ClassificationTree;
import java.lang.reflect.Field;

public class Question {
	private String Column;
	private Object Value;
	
	public Question(String column, Object value) {
		this.Column = column;
		this.Value = value;
	}
	
	public String PrintQuestion() {
		
		return this.Column + ">=" + this.Value;
	}
	
	public String GetColumn() {
		return this.Column;
	}
	
	public Object GetValue(){
		return this.Value;
	}
	
	public Boolean match(Trainable fruit) throws Exception {
		
	Field field = fruit.getClass().getDeclaredField(this.Column);    
	field.setAccessible(true);
	Object value = field.get(fruit);

	if (value instanceof  String) {
		return value == this.Value;
	}
	
	else if (value instanceof Integer){
		return (Integer) value >= (Integer)this.Value;
	}
	
	else {
		throw new Exception();
	}
	
	}
}
