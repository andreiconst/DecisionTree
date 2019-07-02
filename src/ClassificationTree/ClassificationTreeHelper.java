package ClassificationTree;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassificationTreeHelper {
	
	private ClassificationTreeHelper() {

}
	
	
	static HashMap<String, Integer> ClassCount(ArrayList<Trainable> fruits){
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		for (Trainable fruit : fruits) {
			int count = dictionary.containsKey(fruit.GetTarget()) ? dictionary.get(fruit.GetTarget()) : 0;
			dictionary.put(fruit.GetTarget(), count + 1);
		}
		return dictionary;
	}
	
	public static float Gini(ArrayList<Trainable> fruits) {
		// Reminder : Gini impurity score
		// 1 - sum_i p_i ^2
		float impurity = (float) 1.0;
		HashMap<String, Integer> classCounts = ClassCount(fruits);
		
		for (String key : classCounts.keySet()) {
//			System.out.println(key + " : " + classCounts.get(key));
			float p = (float) classCounts.get(key) / fruits.size();

			impurity -= Math.pow(p, 2);
		
		}
		
		
		return impurity;
	}
	
	public static float InformationGain(ArrayList<Trainable> left, ArrayList<Trainable> right, float currentUncertainty) {
		float weightLeft = (float) left.size() / (right.size() + left.size());
		float weightRight = (float) 1 - weightLeft;
		return currentUncertainty - weightLeft * Gini(left) - weightRight * Gini(right);
	}
	
	
	public static ArrayList<ArrayList<Trainable>> Partition(Question q, ArrayList<Trainable> fruits){
		ArrayList<ArrayList<Trainable>> result = new ArrayList<ArrayList<Trainable>>();
		// true examples will be stored in the 0 index
		result.add(new ArrayList<Trainable>());	
		
		// false examples will be stored in the 1 index
		result.add(new ArrayList<Trainable>());

		for (Trainable fruit : fruits) 
		{
			try {
				if(q.match(fruit)) {
					result.get(0).add(fruit);
				}
				else {
					result.get(1).add(fruit);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void PrintTree(TreeObject t, String spacing) {
		if(spacing == null) {
			spacing = "";
		}
		
		// base case : we have reached a leaf
		if(t instanceof Leaf) {
			
			var leaf = (Leaf) t;
			System.out.println(spacing + "Predict " + leaf.GetPrediction());
			for (Trainable fruit : t.GetFruits()) {
				System.out.print(fruit.GetTarget());
			}
			System.out.println("");
		}
		
		else {
			var decisionNode = (DecisionNode) t;
			System.out.println(spacing + t.GetQuestion().PrintQuestion());
			
			//Call this function recursively on the true branch
			System.out.println(spacing + "--> True:");		
			PrintTree(decisionNode.GetTrueBranch(), spacing + "  ");
			
			//Call this function recursively on the false branch
			System.out.println(spacing + "--> False:");		
			PrintTree(decisionNode.GetFalseBranch(), spacing + "  ");
		}
		
	}

}
