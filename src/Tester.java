import java.util.Iterator;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loop<String> loop = new LinkedLoop<>();
		loop.insert("A");
		loop.insert("B");
		loop.insert("C");
		loop.insert("D");
		loop.insert("E");
		
		Iterator<String> itr = loop.iterator();
		
		while (itr.hasNext())
			System.out.println(itr.next());
		
		itr.next();
	}

}
