package gui;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

public class RecyclePageHolder {
	
	private static RecyclePageHolder recyclePageHolder;

	public static RecyclePageHolder getInstance(Composite parent,Object layoutDate,List<Object> objects) {
		if (recyclePageHolder == null) {
			recyclePageHolder = new RecyclePageHolder(parent,layoutDate,objects);
			recyclePageHolder.parent = parent;
		}
		return recyclePageHolder;
	}

	private Composite parent;

	private Composite composite;
	
	private RecyclePageHolder(Composite parent,Object layoutData,List<Object> objects){
		createComposite(parent,layoutData,objects);
	}
	
	private void createComposite(Composite parent,Object layoutData, List<Object> objects){
		composite = NewsListComposite.createNewsComposite(parent,layoutData,objects);
	}
	
	public Composite getComposite(){
		return composite;
	}

}
