package breakout.objects;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {

	private static ArrayList<GameObject> objects = new ArrayList<>();
	
	private ObjectManager() {
		throw new IllegalStateException("Object Utility Class");
	}

	public static void addObject(GameObject o) {
		objects.add(o);
	}

	public static void removeObject(GameObject o) {
		if (objects.contains(o))
			objects.remove(o);
	}
	
	public static List<GameObject> getObjects(){
		return objects;
	}

}
