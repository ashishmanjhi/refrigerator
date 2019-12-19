package com.refrigerator;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.refrigerator.exception.ItemNotFoundException;
import com.refrigerator.exception.NotEnoughSpaceException;
import com.refrigerator.model.Item;
import com.refrigerator.model.Shelf;


/**
 * @author Ashish.manjhi
 *
 */
public class RefrigeratorTest {

	private Refrigerator refrigerator;

	@Before
	public void init() {
		List<Shelf> shelves = new ArrayList<Shelf>();

		shelves.add(new Shelf(1, 10));
		shelves.add(new Shelf(2, 12));
		shelves.add(new Shelf(3, 13));
		shelves.add(new Shelf(4, 14));

		refrigerator = new Refrigerator(shelves);
		
		refrigerator.addItem(new Item(101, "Milk", 1.0f));
		refrigerator.addItem(new Item(102, "apple", 2.0f));
		refrigerator.addItem(new Item(103, "cake", 5.0f));
		refrigerator.addItem(new Item(104, "drink", 9.0f));
		refrigerator.addItem(new Item(105, "beer", 6.0f));
		refrigerator.addItem(new Item(106, "Banana", 5.0f));
		refrigerator.addItem(new Item(107, "Mango", 4.0f));
		refrigerator.addItem(new Item(108, "Ice", 3.0f));
		refrigerator.addItem(new Item(109, "Ice1", 6.0f));

	}

	@Test
	public void testAddItems() throws Exception {

		List<Shelf> shelves = new ArrayList<Shelf>();

		shelves.add(new Shelf(1, 10));
		shelves.add(new Shelf(2, 12));
		shelves.add(new Shelf(3, 13));
		shelves.add(new Shelf(4, 14));
		refrigerator = new Refrigerator(shelves);

		// Adding items inside the refrigerator
		refrigerator.addItem(new Item(101, "Milk", 1.0f));

		DoubleSummaryStatistics stats = refrigerator.getShelves().stream()
				.collect(Collectors.summarizingDouble(Shelf::getRemainingCapacity));

		System.out.println("remaining space" + stats.getSum());
	}

	@Test(expected = NotEnoughSpaceException.class)
	public void testAddItemNospaceAvailable() throws Exception {
		refrigerator.addItem(new Item(110, "Ice2", 5.0f));

		refrigerator.addItem(new Item(111, "Ice3", 3.0f));
		// inserting after space is zero
		refrigerator.addItem(new Item(112, "Ice4", 3.0f));

	}

	@Test(expected = NotEnoughSpaceException.class)
	public void testAddItemNoSpaceAvailableAfterShuffling() throws Exception {

		refrigerator.addItem(new Item(110, "Ice2", 4.0f));
		// inserting after remaining space is 4 but can not be inserted
		refrigerator.addItem(new Item(111, "Ice4", 4.0f));

	}

	@Test(expected = ItemNotFoundException.class)
	public void testGetItemByInvalidId() throws Exception {

		// Taking out an item from the refrigerator while the item is not present by
		// using itemId.
		refrigerator.getItemById(10987634);

	}

	@Test
	public void testGetItemById() throws Exception {
		// Taking out an item from the refrigerator by using itemId.
		Assert.assertEquals(new Item(101, "Milk", 1.0f), refrigerator.getItemById(101));

	}

	@Test
	public void testGetItemByName() throws Exception {
		refrigerator.addItem(new Item(112, "apple", 2.0f));
		// Taking out an item from the refrigerator by using itemId.
		Assert.assertEquals(2, refrigerator.getItemsByName("apple").size());
	}
}
