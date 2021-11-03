package kklimisch_hw5;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class InventorySetTest {

	InventorySet s = new InventorySet();
	final VideoObj v1 = new VideoObj( "A", 2000, "B" );
	final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
	final VideoObj v2 = new VideoObj( "B", 2000, "B" );

	@Test
	public void testAddNumOwned() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video );
		assertEquals( 1, s.get(v1).numOwned );
		s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned );
		s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned );
		assertEquals( 2, s.size() );
		s.addNumOwned(v1, -3);
		assertEquals( 1, s.size() );
		try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
	}

	@Test
	public void testSize() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
		s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
		s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
		s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
		s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
		try { s.addNumOwned(v1, -3); fail(); } catch ( IllegalArgumentException e ) {}
		assertEquals( 0, s.size() );
	}

	@Test
	public void testCheckOutCheckIn() {
		// Adding Video-Record pairs to InventorySet.
		s.addNumOwned(v1, 3);
		s.addNumOwned(v2, 1);

		// Testing that checkOut and checkIn increment and decrement
		// the numOut of the Record associated with the Video as expected
		// when there are no conditions causing an exception to be thrown.
		s.checkOut(v1); assertEquals(1, s.get(v1).numOut);

		// Note: Since v.equals(v1) returns true, calling s.checkOut(v1copy) or s.checkIn(v1copy)
		// produces the same result as calling s.checkOut(v1) or s.checkIn(v1copy).
		s.checkOut(v1copy); assertEquals(2, s.get(v1).numOut);
		s.checkOut(v2); assertEquals(1, s.get(v2).numOut);
		s.checkIn(v1); assertEquals(1, s.get(v1).numOut);
		s.checkIn(v1copy); assertEquals(0, s.get(v1).numOut);
		s.checkIn(v2); assertEquals(0, s.get(v2).numOut);
		s.checkOut(v1); assertEquals(1, s.get(v1).numOut);
		s.checkOut(v1); assertEquals(2, s.get(v1).numOut);
		s.checkOut(v1); assertEquals(3, s.get(v1).numOut);

		// Testing exceptions
		// Verifying that checkIn and checkOut throw exceptions when
		// the video passed in has no associated record in the InventorySet.
		try {
			s.checkOut(new VideoObj("C", 2001, "C"));
			fail();
		} catch ( IllegalArgumentException e ) {}
		try {
			s.checkIn(new VideoObj("C", 2001, "C"));
			fail();
		} catch ( IllegalArgumentException e ) {}

		// Verifying that checkOut throws an exception when numOut = numOwned.
		try {
			s.checkOut (v1);
			fail();
		}  catch ( IllegalArgumentException e ) {}

		// Verifying that checkIn throws an exception when numOut is non-positive.
		try {
			s.checkIn(v2);
			fail();
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testClear() {

		s.addNumOwned(v1, 3);
		s.addNumOwned(v2, 1);
		assertEquals(2, s.size());
		s.clear();
		assertEquals(0, s.size());

		s.addNumOwned(v2, 4);
		s.addNumOwned(v1, 2);
		assertEquals(2, s.size());
		s.clear();
		assertEquals(0, s.size());
	}

	@Test
	public void testGet() {
		// Adding Video-Record pairs to InventorySet.
		s.addNumOwned(v1, 3);
		s.addNumOwned(v2, 1);

		// Calling get method to get copies of the Records.
		Record r1 = s.get(v1);
		Record r2 = s.get(v2);

		// Verifying the copy of Record returned by the get method has equivalent values for
		// all fields as the Record in the Inventory Set.
		assertTrue(r1.video.equals(v1));
		assertEquals(s.get(v1).numOwned, r1.numOwned);
		assertEquals(s.get(v1).numOut, r1.numOut);
		assertEquals(s.get(v1).numRentals, r1.numRentals);
		assertTrue(r2.video.equals(v2));
		assertEquals(s.get(v2).numOwned, r2.numOwned);
		assertEquals(s.get(v2).numOut, r2.numOut);
		assertEquals(s.get(v2).numRentals, r2.numRentals);

		// Verifying that changing numOwned, numOut, and numRentals
		// fields in the copy of the Record does not affect the
		// original Record in the InventorySet.
		// The field for the VideoObj is immutable and cannot be
		// changed in either the original or the copy of the Record.
		r1.numOwned = 3;
		r1.numRentals = 6;
		r2.numOwned = 5;
		r2.numOut = 1;
		assertEquals(3, s.get(v1).numOwned);
		assertEquals(0, s.get(v1).numRentals);
		assertEquals(1, s.get(v2).numOwned);
		assertEquals(0, s.get(v2).numOut);
	}

	@Test
	public void testToCollection() {
		// Adding Video-Record pairs to InventorySet.
		s.addNumOwned(v1, 3);
		s.addNumOwned(v2, 4);
		Record r1 = s.get(v1);
		Record r2 = s.get(v2);


		Collection<Record> collection = s.toCollection();
		// Notes on the collection:
		// The collection now contains copies of the 2 Records in InventorySet s.
		// The copy Records in collection are different objects than the
		// Records in Inventory set s, but the copy Records have a pointer to the
		// same VideoObj objects as do the original Records..

		// Verifying that the collection contains the expected
		// number of Record objects.
		assertEquals(2, collection.size());

		// Verifying that the collection does not contain the
		// actual Record objects in InventorySet s.
		assertFalse(collection.contains(r1));
		assertFalse(collection.contains(r2));

		// To ensure that the copy Records in the collection contain the same
		// information as the original Records in InventorySet s, I will verify that
		// the same String is produced when the toString() method is invoked on the
		// original and the copy Records. To do this, I will iterate over
		// the Records in the collection, invoke their toString() methods,
		// and store the Strings in a temp ArrayList. I'll then verify
		// that the temp ArrayList contains the Strings produced when the
		// toString() method is invoked on the Records in InventorySet s..
		ArrayList<String> temp = new ArrayList<>();
		for (Record r : collection) {
			temp.add(r.toString());

			// Modify fields in the Record.
			r.numOwned = 5;
			r.numRentals = 7;
		}
		assertTrue(temp.contains(r1.toString()));
		assertTrue(temp.contains(r2.toString()));

		// Verifying that modifying the fields of the Records in
		// collection does not affect the fields of the Records
		// in InventorySet s.
		assertFalse(s.get(v1).numOwned == 5);
		assertFalse(s.get(v1).numRentals == 7);
		assertFalse(s.get(v2).numOwned == 5);
		assertFalse(s.get(v2).numRentals == 7);
	}

}
