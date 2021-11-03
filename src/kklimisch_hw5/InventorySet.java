/*
 * Kristen Klimisch
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package kklimisch_hw5;;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

/**
 * An Inventory implemented using a <code>HashMap&lt;Video, Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * <p><b>Class Type:</b> Mutable Collection of Records</p>
 * <p><b>Object Invariant:</b></p>
 *   Every key and value in the map is non-<code>null</code>.
 * <p><b>Object Invariant:</b></p>
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 *
 * @author Kristen Klimisch
 * @version 1.0
 */
final class InventorySet {

	/** <p><b>Invariant:</b> <code>_data != null</code> </p>*/
	private final Map<VideoObj, Record> data = new HashMap<VideoObj, Record>();

	/**
	 * Default constructor.
	 */
	InventorySet() { }

	/**
	 * Method to return the number of Records in the Inventory Set.
	 *
	 * @return the number of Records.
	 */
	public int size() {

		return data.size();
	}

	/**
	 * Method to get a copy of the Record for a given Video.
	 *
	 * @param v the Video
	 * @return a shallow copy of the Record associated with the Video in the InventorySet;
	 *         if the Video is not present in the InventorySet, return null.
	 */
	public Record get(VideoObj v) {

		// Get the Record associated with the Video in the InventorySet and invoke the Record's
		// copy() method to create a shallow copy of it. Return the copy of the Record.
		Record r = data.get(v).copy();
		return r;
	}

	/**
	 * Method to get an ArrayList of all Records in the InventorySet.
	 * The Records in the InventorySet are copied and then added to the Array List,
	 * Neither the underlying collection, nor the actual Records are returned.
	 *
	 * @returns an ArrayList containing copies of all the Records in the InventorySet.
	 */
	public Collection<Record> toCollection() {
		ArrayList<Record> collection = new ArrayList<Record>();
		for (Record record : data.values()) {
			collection.add(record.copy());
		}
		return collection;
	}

	/**
	 * Add or remove copies of a video from the inventory.
	 * If a video record is not already present (and change is
	 * positive), a record is created.
	 * If a record is already present, <code>numOwned</code> is
	 * modified using <code>change</code>.
	 * If <code>change</code> brings the number of copies to be zero,
	 * the record is removed from the inventory.
	 * @param video the video to be added.
	 * @param change the number of copies to add (or remove if negative).
	 * @throws IllegalArgumentException if video null, change is zero,
	 *  if attempting to remove more copies than are owned, or if
	 *  attempting to remove copies that are checked out.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void addNumOwned(VideoObj video, int change) {

		// Throw IllegalArgumentException if video is null or change is zero
		if(video == null || change == 0) {
			throw new IllegalArgumentException();
		}

		// Get Record associated with video from InventorySet.
		Record record = data.get(video);

		// If a Record does not exist for the video in the InventorySet, data.get(video)
		// will return null. If this occurs and change is positive, create a Record and
		// add the video - Record pair to the HashMap; else throw an IllegalArgumentException.
		if (record == null) {
			if (change > 0) {

				// Initialize the numOwned to change, which is the number of copies of
				// the VideoObj to be added to the InventorySet. Initialize numOut and
				// numRentals to 0 because this VideoObj is just being added to
				// the InventorySet, and thus hasn't been checked out or rented.
				record = new Record(video, change, 0, 0);
				data.put(video, record);

			} else {
				throw new IllegalArgumentException();
			}

		// Throw IllegalArgumentException if trying to remove more copies
		// than are currently present in the InventorySet.
		} else if ((record.numOwned - record.numOut) + change < 0) {
				throw new IllegalArgumentException();

		// If change brings the number of copies owned to 0, remove VideoObj - Record pair from
		// InventorySet.
		} else if ((record.numOwned + change) == 0) {
			data.remove(video);

		// Adjust number of copies of VideoObj owned by the change.
		} else {
			record.numOwned += change;
		}
	}

	/**
	 * Check out a video.
	 * @param video the video to be checked out.
	 * @throws IllegalArgumentException if video has no record or numOut
	 * equals numOwned.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void checkOut(VideoObj video) {

		// Get Record associated with video from InventorySet.
		Record record = data.get(video);

		// If video has no Record or numOut equals numOwned,
		// throw IllegalArgumentException.
		if ( (record == null) || (record.numOwned == record.numOut) ) {
			throw new IllegalArgumentException();
		}

		// Increment the numOut for the Record to reflect that
		// a copy of the Video has been checked out.
		record.numOut++;
	}

	/**
	 * Check in a video.
	 * @param video the video to be checked in.
	 * @throws IllegalArgumentException if video has no record or numOut
	 * non-positive.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void checkIn(VideoObj video) {
		// Get Record associated with video from InventorySet.
		Record record = data.get(video);

		// If video has no Record or numOut is non-positive
		// (meaning the Video has not been checked out and thus cannot logically
		// be checked in), throw IllegalArgumentException.
		if ( (record == null) || (record.numOut <= 0) ) {
			throw new IllegalArgumentException();
		}

		// Decrement numOut for the Record to reflect
		// that a copy of the Video has been checked in.
		record.numOut--;
	}

	/**
	 * Remove all records from the inventory.
	 * <p><b>Postcondition:</b> <code>size() == 0</code></p>
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * Return the contents of the inventory as a string.
	 *
	 * @return a String representation of the contents of the inventory.
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Database:\n");
		for (Record r : data.values()) {
			buffer.append("  ");
			buffer.append(r);
			buffer.append("\n");
		}
		return buffer.toString();
	}
}