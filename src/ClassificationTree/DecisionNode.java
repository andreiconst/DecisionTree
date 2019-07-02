package ClassificationTree;

import java.util.ArrayList;

public class DecisionNode implements TreeObject{
	private Question _question;
	private TreeObject _trueBranch;
	private TreeObject _falseBranch;
	
	public DecisionNode(Question question, 
			TreeObject trueBranch,
			TreeObject falseBranch) {
		this._question = question;
		this._trueBranch = trueBranch;
		this._falseBranch = falseBranch;
	}

	@Override
	public ArrayList<Trainable> GetFruits() {
		var result = new ArrayList<Trainable>();
		
		for (Trainable fruit : _trueBranch.GetFruits()) {
			result.add(fruit);
		}
		for (Trainable fruit : _falseBranch.GetFruits()) {
			result.add(fruit);
		}
		
		return result;
	}

	@Override
	public Question GetQuestion() {
		// TODO Auto-generated method stub
		return _question;
	}

	@Override
	public TreeObject GetBranch() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public TreeObject GetTrueBranch() {
		// TODO Auto-generated method stub
		return this._trueBranch;
	}
	
	public TreeObject GetFalseBranch() {
		// TODO Auto-generated method stub
		return this._falseBranch;
	}
	
	public String Classify(Trainable fruit, TreeObject t) {
		// Base Case T is a string
		if (t instanceof Leaf) {
			return t.Classify(fruit, t);
		}
		
		var decisionNode = (DecisionNode) t;
		
		try {
			if (decisionNode.GetQuestion().match(fruit)){
				return Classify(fruit, decisionNode.GetTrueBranch());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Classify(fruit, decisionNode.GetFalseBranch());

	}

	
}
