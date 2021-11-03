/*
 * Kristen Klimisch
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
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
 *
 * @author Kristen Klimisch
 * @version 1.0
 */
final class VideoObj implements Comparable<VideoObj> {

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String title;

	/** <p><b>Invariant:</b> greater than 1800, less than 5000 </p>*/
	private final int year;

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String director;

	/**
	 * Constructor. Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @param title     The title of the video.
	 * @param year      The year the video was released.
	 * @param director  The director of the video.
	 * @throws IllegalArgumentException if any object invariant is violated.
	 */
	VideoObj(String title, int year, String director) throws IllegalArgumentException {

		// Check invariants and throw Illegal Argument Exception if any object invariant
		// is violated.
		if ( (title == null) || (title.trim().isEmpty() ) || (year < 1801) || (year > 4999) ||
				(director == null) || (director.trim().isEmpty() ) ) {
			throw new IllegalArgumentException();
		}
		this.title = title.trim();
		this.year = year;
		this.director = director.trim();
	}

	/**
	 * Method to return the VideoObj object's director field.
	 *
	 * @return the director
	 */
	public String director() {
		return director;
	}

	/**
	 * Method to return the VideoObj object's title field.
	 *
	 * @return the title
	 */
	public String title() {
		return title;
	}

	/**
	 * Method to return the VideoObj's year field.
	 *
	 * @return the year
	 */
	public int year() {
		return year;
	}

	/**
	 * Equals() method. Compares all fields of this VideoObj object with those of thatObject.
	 * @param thatObject the Object to be compared.
	 * @return deep equality test between this and thatObject: returns true if this object and
	 *         thatObject are the same object or if this object and thatObject have equivalent
	 *         values for all fields. Else returns false.
	 */
	@Override
	public boolean equals(Object thatObject) {
		// Use == operator to check if thatObject argument is a reference
		// to this object. If it is, return true.
		if (thatObject == this) {
			return true;
		}

		// Use the instanceof operator to check if thatObject argument has the
		// correct type. If it doesn't, return false.
		if (!(thatObject instanceof VideoObj) ) {
			return false;
		}

		// Cast thatObject argument to the correct VideoObj type.
		VideoObj vo = (VideoObj)thatObject;

		// Check if thatObject fields match the corresponding fields in
		// this object. Use case-insensitive comparisons for String fields
		// title and director.
		return vo.title.equalsIgnoreCase(title) && vo.year == year &&
				vo.director.equalsIgnoreCase(director);
	}

	/**
	 * Return a hash code value for this object using the algorithm from Bloch:
	 * fields are added in the following order: title, year, director.
	 *
	 * Send String fields title and director to upper case before computing
	 * their hash codes.
	 *
	 * @return the computed hash code for the object
	 */
	@Override
	public int hashCode() {
		int result = title.toUpperCase().hashCode();
		result = 31 * result + Integer.hashCode(year);
		result = 31 * result + director.toUpperCase().hashCode();
		return result;
	}

	/**
	 * Compares the attributes of this object with those of thatObject, in
	 * the following order: title, year, director.
	 * @param thatObject the VideoObj to be compared.
	 * @return a negative integer, zero, or a positive integer as this
	 *  object is less than, equal to, or greater than that object.
	 */
	@Override
	public int compareTo(VideoObj thatObject) {
		int result = title.compareToIgnoreCase(thatObject.title);
		if (result == 0) {
			result = Integer.compare(year, thatObject.year);
			if (result == 0) {
				result = director.compareToIgnoreCase(thatObject.director);
			}
		}
		return result;
	}

	/**
	 * Return a string representation of the object in the following format:
	 * <code>"title (year) : director"</code>.
	 *
	 * @return String representation of the VideoObj object.
	 */
	@Override
	public String toString() {
		return title + " (" + year + ") : " + director;
	}

}