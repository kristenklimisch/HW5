package kklimisch_hw5;

/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * <p><b>Class Type:</b> Immutable Data Class</p>
 * <p><b>Object Invariant:</b></p>
 *   Title is non-null, no leading or final spaces, not empty string.
 * <p><b>Object Invariant:</b></p>
 *   Year is greater than 1800, less than 5000.
 * <p><b>Object Invariant:</b></p>
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String title;

	/** <p><b>Invariant:</b> greater than 1800, less than 5000 </p>*/
	private final int year;

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String director;

	/**
	 * Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @throws IllegalArgumentException if any object invariant is violated.
	 */
	VideoObj(String title, int year, String director) {
		// TODO: implement VideoObj constructor
		this.title = null;
		this.year = 0;
		this.director = null;	  
	}

	/**
	 * Return the value of the attribute.
	 */
	public String director() {
		// TODO: implement director method
		return "director";
	}

	/**
	 * Return the value of the attribute.
	 */
	public String title() {
		// TODO: implement title method
		return "title";
	}

	/**
	 * Return the value of the attribute.
	 */
	public int year() {
		// TODO: implement year method
		return -1;
	}

	/**
	 * Compare the attributes of this object with those of thatObject.
	 * @param thatObject the Object to be compared.
	 * @return deep equality test between this and thatObject.
	 */
	@Override
	public boolean equals(Object thatObject) {
		// TODO: implement equals method
		return false;
	}

	/**
	 * Return a hash code value for this object using the algorithm from Bloch:
	 * fields are added in the following order: title, year, director.
	 */
	@Override
	public int hashCode() {
		// TODO: implement hashCode method
		return -1;
	}

	/**
	 * Compares the attributes of this object with those of thatObject, in
	 * the following order: title, year, director.
	 * @param that the VideoObj to be compared.
	 * @return a negative integer, zero, or a positive integer as this
	 *  object is less than, equal to, or greater than that object.
	 */
	@Override
	public int compareTo(VideoObj thatObject) {
		// TODO: implement compareTo method
		return -1;
	}

	/**
	 * Return a string representation of the object in the following format:
	 * <code>"title (year) : director"</code>.
	 */
	@Override
	public String toString() {
		// TODO: implement toString method
		return "El Mariachi (1996) : Rodriguez";
	}

}