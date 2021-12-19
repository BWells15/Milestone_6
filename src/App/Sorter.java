package App;
import java.util.Comparator;
import Products.SallableProd;

public class Sorter implements Comparator<SallableProd>{

	@Override
	/**
	 * @param o1 compare 1
	 * @param o2 compare 2
	 */
	public int compare(SallableProd o1, SallableProd o2) {
		// TODO Auto-generated method stub
		return o2.getName().compareToIgnoreCase(o1.getName());
	}

}
