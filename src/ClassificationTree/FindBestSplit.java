package ClassificationTree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

public class FindBestSplit {

	private float _bestGain = 0;
	private Question _bestQuestion = null;
	
	public FindBestSplit(ArrayList<Trainable> fruits, String[] features) {
		var currentUncertainty = ClassificationTreeHelper.Gini(fruits);
		
		for (String feature : features) {
			var values = new HashSet<Object>();

			
			for (Trainable fruit : fruits) {
				Field field;
				try {
					field = fruit.getClass().getDeclaredField(feature);
					field.setAccessible(true);
					Object value = field.get(fruit);
					values.add(value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			
			for (Object val : values) {
				
				var question = new Question(feature, val);
				
				// try splitting the dataset
				var rows = ClassificationTreeHelper
						.Partition(question, fruits);
				
				// Skip the dataset if no proper partition
				if(rows.get(0).size() == 0 || rows.get(1).size() == 0) {continue;}
				
				// Calculate information gain from the split
				var gain = ClassificationTreeHelper.InformationGain(rows.get(0), 
						rows.get(1), currentUncertainty);
				
				// if new best gain update status
				
				if (gain > _bestGain) {
					_bestGain = gain;
					_bestQuestion = question;
				}
					
			}
		}

		
	}
	
	public float GetBestGain() {
		return _bestGain;
	}
	
	public Question GetBestQuestion(){
		return _bestQuestion;
	}
}
