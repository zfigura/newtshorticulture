package horticulture;

/**
 * If your block has more than 16 metadata options (say it
 * has 20 subtypes), use this interface to deal with the
 * extras. Make a second block instance in your mod class,
 * and use these functions to make dealing with said
 * displaced blocks easier.
 * @author ObsequiousNewt
 */
public interface IDisplaceable{
	void setDisplaced();
	
	void setDisplaced(int displacement);
	
	int getDisplacement();
	
	boolean isDisplaced();
}
