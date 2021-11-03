/*
 * Kristen Klimisch
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package kklimisch_hw5;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoObjTest {

	/**
	 * Testing the constructor and attribute fields.
	 *
	 * For the String object reference fields director and title:
	 * ~Use assertSame to verify that when a new instance of the videoObj
	 * class is created using the parameterized constructor, the new instance
	 * fields for director and title refer to the same String objects as do the
	 * input arguments for title and director.
	 * ~Use assertEquals to verify that the parameterized constructor successfully
	 * initializes the fields for director and title to be equal to the String objects
	 * that were passed in, so that when separate variables are set equal to
	 * equivalent Strings, the instance fields and the separate variables are equal to
	 * each other.
	 *
	 * For the integral primitive field year:
	 * ~Use assertEquals to verify that when a new instance of the videoObj class is created
	 * using the parameterized constructor, the new instance field year is equal to the
	 * value that was passed in.
	 */
	@Test
	public void testConstructorAndAttributes() {
		String title1 = "XX";
		String director1 = "XY";
		String title2 = " XX ";
		String director2 = " XY ";
		int year = 2002;

		VideoObj v1 = new VideoObj(title1, year, director1);
		assertSame(title1, v1.title());
		assertEquals(year, v1.year());
		assertSame(director1, v1.director());

		VideoObj v2 = new VideoObj(title2, year, director2);
		assertEquals(title1, v2.title());
		assertEquals(director1, v2.director());
	}

	/**
	 * Testing that the constructor upholds the invariants for
	 * the field year.
	 *
	 * Invariants for year: Must greater than 1800 and less than 5000.
	 *
	 * Tests:
	 * ~Verify that the constructor throws an Illegal Argument Exception
	 * if an input value of 1800 or 5000 is passed in for year.
	 * ~Test edge cases: Verify that no exception is thrown if an input value
	 * of 1801 or 4999 is passed in for year.
	 */
	@Test
	public void testConstructorExceptionYear() {
		try {
			new VideoObj("X", 1800, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 5000, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 1801, "Y");
			new VideoObj("X", 4999, "Y");
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Testing that the constructor upholds the invariants for field title.
	 *
	 * Invariants: Title is not null, an empty String, or a String consisting
	 * of only spaces.
	 *
	 * Tests:
	 * ~Verify that the constructor throws an Illegal Argument Exception when
	 * the String passed in for title is null, an empty String, or a String
	 * consisting of only spaces.
	 */
	@Test
	public void testConstructorExceptionTitle() {
		try {
			new VideoObj(null, 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj(" ", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
	}


	/**
	 * Testing that the constructor upholds the invariants for field director.
	 *
	 * Invariants: Director is not null, an empty String, or a String consisting
	 * of only spaces.
	 *
	 * Tests:
	 * ~Verify that the constructor throws an Illegal Argument Exception when
	 * the String passed in for director is null, an empty String, or a String
	 * consisting of only spaces.
	 */
	@Test
	public void testConstructorExceptionDirector() {
		try {
			new VideoObj("Y", 2002, null);
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("Y", 2002, "");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("Y", 2002, " ");
			fail();
		} catch (IllegalArgumentException e) { }
	}

	/**
	 * Testing for hashCode() method. Verify that hashCode() method produces the
	 * expected results when called on different videoObj instances.
	 *
	 * Note for grader: I updated the expected hashValues to reflect that my hashCode()
	 * method pushes the String for field and director to upper case before computing
	 * their hash codes.
	 */
	@Test
	public void testHashCode() {
		assertEquals
		(-1901244571, new VideoObj("None", 2009, "Zebra").hashCode());
		assertEquals
		(2025667696, new VideoObj("Blah", 1954, "Cante").hashCode());
	}

	/**
	 * Testing for overridden equals() method. Verify that equals method is reflexive,
	 * symmetric, transitive, consistent, and returns false when equals(null) is called
	 * on a non-null videoObj. Also verify that equals method can detect lack of equality
	 * in all VideoObj fields.
	 */
	@Test
	public void testEquals() {
		// a, b, and c have the same values for all fields.
		VideoObj a = new VideoObj("Transformers", 2011, "Bay");
		VideoObj b = new VideoObj("Transformers", 2011, "Bay");
		VideoObj c =  new VideoObj("Transformers", 2011, "Bay");

		// d has a different value for title than a, b, and c.
		VideoObj d = new VideoObj("Transform", 2011, "Bay");

		// e has a different value for year than a, b, and c.
		VideoObj e =  new VideoObj("Transformers", 2010, "Bay");

		// f has a different value for year than a, b, and c.
		VideoObj f =  new VideoObj("Transformers", 2010, "LaBeouf");

		// Verifying equals() is reflexive.
		assertTrue(a.equals(a));
		assertTrue(b.equals(b));

		// Verifying equals() is symmetric.
		assertTrue(a.equals(b));
		assertTrue(b.equals(a));

		assertFalse(a.equals(d));
		assertFalse(d.equals(a));

		// Verifying equals() is transitive:
		// From testing above, we know a.equals(b) returns true.
		assertTrue(b.equals(c));
		assertTrue(c.equals(a));
		// From testing above, we know a.equals(d) returns false.
		assertFalse(c.equals(d));

		// Verifying consistency.
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));
		assertTrue(c.equals(a));

		assertFalse(a.equals(f));
		assertFalse(a.equals(f));
		assertFalse(a.equals(f));
		assertFalse(a.equals(f));
		assertFalse(a.equals(f));
		assertFalse(a.equals(f));
		assertFalse(a.equals(f));

		// Verifying equals returns false when equals(null) is called
		// on a non-null videoObj object.
		assertFalse(a.equals(null));

		// Verifying equals() can detect lack of equality in year field.
		// Equal(s) verified to detect lack of equality in director and
		// title fields by testing VideoObj a for equality with VideoObj d
		// and VideoObj f above.
		assertFalse(a.equals(e));

	}

	/**
	 * Testing for compareTo method. Verify that the following conditions
	 * of the general contract of the compareTo method are met:
	 * 1) x.compareTo(y) == -1 * (y.compareTo(x)), for all x and y.
	 * 2) compareTo relation is transitive.
	 * 3) x.compareTo(y) == 0 implies that
	 *    x.compareTo(z) == y.compareTo(z), for all z.
	 * 4) (x.compareTo(y) == 0) == (x.equals(y)).
	 *
	 * Also verify that the compareTo method compares instances of VideoObj
	 * by comparing fields in the following order: title, year, director.
	 * Title and director are ordered alphabetically, so that a videoObj instance
	 * with a title starting with "B" is greater than a videoObj instance with a
	 * title starting with "A." Year is ordered by integer value, so that
	 * higher/more recent years are greater than lower/less recent years.
	 */
	@Test
	public void testCompareTo() {
		VideoObj a = new VideoObj("Avator", 2009, "Cameron");
		VideoObj b = new VideoObj("Board", 2006, "Avery");
		VideoObj c = new VideoObj("Avator", 2008, "Cameron");
		VideoObj d = new VideoObj("Avator", 2009, "Daniel");
		VideoObj e = new VideoObj("Avator", 2009, "Daniel");

		// Verifying that compareTo compares instances of VideoObj by comparing
		// fields in the following order: title, year, director.
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(c));
		assertEquals (1, a.compareTo(c));
		assertEquals (-1, a.compareTo(d));
		assertEquals(0, d.compareTo(e));

		// Verifying that x.compareTo(y) == -1 * y.compareTo(x).
		assertEquals(a.compareTo(b), -1 * b.compareTo(a) );
		assertEquals(c.compareTo(d), -1 * d.compareTo(c) );

		// Verifying compareTo relation is transitive:
		assertEquals(1, a.compareTo(c));
		assertEquals(1, b.compareTo(a));
		assertEquals(1, b.compareTo(c));
		assertEquals(-1, a.compareTo(e));
		assertEquals (-1, e.compareTo(b));
		assertEquals(-1, a.compareTo(b));

		// Verifying that x.compareTo(y) == 0 means that
		// sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
		// From testing above, we know that d.compareTo(e) == 0.
		assertEquals(d.compareTo(b), e.compareTo(b));
		assertEquals(d.compareTo(c), e.compareTo(c));

		// Verifying that (x.compareTo(y) == 0) == (x.equals(y)).
		// From above, we know that d.compareTo(e) == 0;
		assertTrue(d.equals(e));
	}

	@Test
	public void testToString() {
		String s = new VideoObj("A", 2000, "B").toString();
		assertEquals( "A (2000) : B", s );
		s = new VideoObj(" A ", 2000, " B ").toString();
		assertEquals( "A (2000) : B", s );
	}

}
