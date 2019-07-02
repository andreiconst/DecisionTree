package ClassificationTree;

import java.util.ArrayList;

public class BuildTree {
//	Builds the tree.
//
//    Rules of recursion: 1) Believe that it works. 2) Start by checking
//    for the base case (no further information gain). 3) Prepare for
//    giant stack traces.
	
//    # Try partitioing the dataset on each of the unique attribute,
//    # calculate the information gain,
//    # and return the question that produces the highest gain.
	
	private static String[] features;
	private TreeObject root;
	
	public BuildTree(String[] features) {
		this.features = features;
	}
	
	public TreeObject BuildTreeHelper(ArrayList<Trainable> fruits) {
		var bestSplit = new FindBestSplit(fruits, features);
		
		// Base case: no further info gain
		// return Leaf
		if(bestSplit.GetBestGain() == 0) {
			return new Leaf(fruits);
		}
				
		//if we reach here he have found a usefull feature
		
		var partition = ClassificationTreeHelper.Partition(bestSplit.GetBestQuestion(), 
				fruits);
		
		// recursively build the rest
		var trueBranch = BuildTreeHelper(partition.get(0));
		var falseBranch = BuildTreeHelper(partition.get(1));

		root = new DecisionNode(bestSplit.GetBestQuestion(), 
				trueBranch.GetBranch(), falseBranch.GetBranch());
		return root;
	}
	
	public float Evalute(ArrayList<Trainable> fruits) {
		
		var correctAnswers = 0;
		for (Trainable fruit : fruits) {
			
			var isCorect = fruit.GetTarget() == root.Classify(fruit, root);
			if(isCorect) {correctAnswers += 1;}
			
		}
		
		return ((float) correctAnswers) / fruits.size();
	}
	
}
