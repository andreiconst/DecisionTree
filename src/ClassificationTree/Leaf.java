package ClassificationTree;

import java.util.ArrayList;
import java.util.HashMap;

public class Leaf implements TreeObject{

	private HashMap<String, Integer> classCounts;
	private ArrayList<Trainable> fruits;
	private String prediction;
	
	public Leaf(ArrayList<Trainable> fruits) {
		this.classCounts = ClassificationTreeHelper.ClassCount(fruits);
		this.fruits = fruits;
		var result = "";
		var maxSoFar = 0;
		for (String k : this.classCounts.keySet()) {
			if(maxSoFar < this.classCounts.get(k)) {
				result = k;
				maxSoFar = this.classCounts.get(k);
			}
		}
		prediction = result;
	}

	@Override
	public ArrayList<Trainable> GetFruits() {
		return this.fruits;
		
	}

	
	// should never get here
	@Override
	public Question GetQuestion() {
		return null;
	}

	@Override
	public TreeObject GetBranch() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public String GetPrediction() {
		return prediction;
	}
	
	public String Classify(Trainable fruit, TreeObject t) {
		return GetPrediction();
	}

}
