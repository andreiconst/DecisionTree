package ClassificationTree;

import java.util.ArrayList;

public interface TreeObject {
	
	public ArrayList<Trainable> GetFruits();
	
	public Question GetQuestion();
	
	public TreeObject GetBranch();
	
	public String Classify(Trainable fruit, TreeObject t);
	
}
