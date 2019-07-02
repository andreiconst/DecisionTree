
package ClassificationTree;
import java.util.*;


public class ClassificationTreeTester {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	System.out.println("Hello WOrld");
	ArrayList<Trainable> dataset = new ArrayList<Trainable>();
	dataset.add(new Fruit("Green", 3, "Apple"));
	dataset.add(new Fruit("Yellow", 3, "Apple"));
	dataset.add(new Fruit("Red", 1, "Grape"));
	dataset.add(new Fruit("Red", 1, "Grape"));
	dataset.add(new Fruit("Yellow", 3, "Lemon"));
	
	var Tree = new BuildTree(new String[] {"Color", "Diameter"});
	var root = Tree.BuildTreeHelper(dataset);
	
	var accuracy = Tree.Evalute(dataset);
	System.out.println(accuracy + "");
	
	ClassificationTreeHelper.PrintTree(root, null);
	
	}
	
}
