package com.refrigerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.refrigerator.exception.ItemNotFoundException;
import com.refrigerator.exception.NotEnoughSpaceException;
import com.refrigerator.model.Item;
import com.refrigerator.model.Shelf;

/**
 * @author Ashish.manjhi
 *
 * 
 *         This {@link Refrigerator} class represents a smart refrigerator with
 *         shelves and items .
 */
public class Refrigerator {

	// List of shelves inside the refrigerator
	private List<Shelf> shelves = new ArrayList<Shelf>();

	/**
	 * default Constructor
	 */
	public Refrigerator() {
	}

	/**
	 * 
	 * @param contain List of shelves
	 */
	public Refrigerator(List<Shelf> shelves) {
		this.setShelves(shelves);
	}

	/**
	 * This adds items in the refrigerator
	 * 
	 * @param Item item to be added.
	 */
	public void addItem(Item item) {

		for (Shelf shelf : getShelves()) {
			// If the item is added to the shelf, return.
			if (addItemToShelf(item, shelf)) {
				return;
			}
		}

		throw new NotEnoughSpaceException(item.getId());
	}

	/**
	 * Adds item to a particular shelf.
	 * 
	 * @param item  the item to be added.
	 * @param shelf the shelf to which the item needs to be added.
	 * @return boolean return true if the item is added, else false.
	 */
	private boolean addItemToShelf(Item item, Shelf shelf) throws NotEnoughSpaceException {

		boolean flag = false;

		/*
		 * Checking if the item capacity is less than or equal to the shelf capacity. If
		 * yes, add item to the shelf and decrease the shelf capacity and return true .
		 */
		if (shelf.getRemainingCapacity() >= item.getCapacity()) {
			shelf.getItems().add(item);
			// TODO Update the item to shelf id map to improve the lookup.
			shelf.setRemainingCapacity(shelf.getRemainingCapacity() - item.getCapacity());
			return true;
		}

		/*
		 * Use DoubleSummaryStatistics to get the statistics of the remaining space in
		 * the refrigerator.
		 */
		DoubleSummaryStatistics stats = getShelves().stream()
				.collect(Collectors.summarizingDouble(Shelf::getRemainingCapacity));

		/*
		 * Checking if some other shelf has a capacity to hold this item.
		 */
		if (item.getCapacity() <= stats.getMax()) {
			return false;
		}

		/*
		 * Checking if the item capacity is less than or equal to the total remaining
		 * capacity of the shelves. If yes, shuffle and add item to the shelf if
		 * possible else, throw
		 */
		if ((item.getCapacity() <= stats.getSum())) {
			// The temporary iterator of items to avoid ConcurrentModificationExcepion.
			List<Item> temporaryIterator = new ArrayList<>(shelf.getItems());

			for (Item i : temporaryIterator) {

				if (shuffle(i, shelf.getId()) == true) {
					// If shuffling happened, remove the item from the current shelf.
					shelf.getItems().remove(i);
					shelf.setRemainingCapacity(shelf.getRemainingCapacity() + i.getCapacity());
					// Try adding item to shelf after a shuffle.
					if (addItemToShelf(item, shelf) == true)
						return true;
				}
			}

		} else {
			throw new NotEnoughSpaceException(item.getId());
		}

		return flag;
	}

	/**
	 * Shuffle the item from current shelf to the shelves which are having minimum
	 * remaining capacity iteratively..
	 * 
	 * @param Item    the item to be shuffled to any one of the other shelves
	 * @param shelfId the current shelf id.
	 * @return boolean Return true if shuffling happens else false.
	 */
	public boolean shuffle(Item item, int shelfId) {

		for (Shelf shelf : getShelves().stream().sorted(Comparator.comparingDouble(Shelf::getRemainingCapacity))
				.collect(Collectors.toList())) {
			if (item.getCapacity() <= shelf.getRemainingCapacity() && shelfId != shelf.getId()) {
				shelf.getItems().add(item);
				shelf.setRemainingCapacity(shelf.getRemainingCapacity() - item.getCapacity());
				return true;

			}
		}
		return false;
	}

	/**
	 * Get item from {@link Refrigerator} by item id.
	 * 
	 * @param itemId - the item id.
	 * @return Item the item if available.
	 * @throws Exception Item Not Found
	 */

	public Item getItemById(int itemId) throws ItemNotFoundException {

		/*
		 * Using Maps and Stream
		 */
		Map<Integer,Shelf> map=getmap();

		/*
		 * Collecting the item requested by the User by using ItemId and storing in a List.
		 */
		List<Item> itemToBeRemoved=getShelves().stream().flatMap(s->s.getItems().stream()).filter(i->i.getId()==itemId).collect(Collectors.toList());

		/*
		 * When the item requested is not present in the refrigerator it will throw ItemNotFoundException.
		 */
		if(itemToBeRemoved.isEmpty())
			throw new ItemNotFoundException(itemId);
		
		/*
		 * This shelf contain the shelf details in which the requested item is present.
		 */
		Shelf shelf=map.get(itemToBeRemoved.get(0).getId());

		//Updating the shelf capacity i.e increasing it acc. to item capacity.
		shelf.setRemainingCapacity(shelf.getRemainingCapacity()+itemToBeRemoved.get(0).getCapacity());

		//Removing the item from the shelf.
		shelf.getItems().remove(itemToBeRemoved.get(0));

		

		return itemToBeRemoved.get(0);



		// TODO use streams to accomplish the same.
		//		// TODO use Maps to identify the shelf of a given item to improve the look up.
		//		for (Shelf shelf : getShelves()) {
		//			List<Item> itemList = shelf.getItems();
		//			for (Item item : itemList) {
		//				if (item.getId() == itemId) {
		//					shelf.setRemainingCapacity(shelf.getRemainingCapacity() + item.getCapacity());
		//					itemList.remove(item);
		//					return item;
		//				}
		//			}
		//
		//		}
		//		throw new ItemNotFoundException(itemId);

	}



	/**
	 * Get items from the shelves by item name.
	 * 
	 * @param Item name.
	 * @return iIf items match by name, return them else return empty list.
	 */
	public List<Item> getItemsByName(String itemName) {

		/*
		 * 
		 * Alternate Way of getting items from the refrigerator Using Maps and Streams
		 * 
		 */

		Map<Integer,Shelf> map=getmap();

		/*
		 * Collecting the items requested by the User by using ItemName and storing in a List to be removed.
		 */
		List<Item> itemToBeRemoved=getShelves().stream().flatMap(s->s.getItems().stream()).filter(i->i.getName()==itemName).collect(Collectors.toList());

		// No of items collected or matched with item name.
		int no=itemToBeRemoved.size()-1;

		// Removing Items one by one 
		while(no>=0)
		{
			/*
			 * This shelf contain the shelf details in which the requested item is present.
			 */
			Shelf shelf=map.get(itemToBeRemoved.get(no).getId());

			//Updating the shelf capacity i.e increasing it acc. to item capacity.
			shelf.setRemainingCapacity(shelf.getRemainingCapacity()+itemToBeRemoved.get(no).getCapacity());

			//Removing the item from the shelf.
			shelf.getItems().remove(itemToBeRemoved.get(no));

			// For count
			no--;
		}
		/*
		 * When the item requested is not present in the refrigerator it will throw ItemNotFoundException.
		 */
		if(itemToBeRemoved.isEmpty())
			throw new ItemNotFoundException(itemName);


		return itemToBeRemoved;





		// TODO use streams to accomplish the same.
		//TODO use Maps to identify the shelf of a given item to improve the look up.
		//		List<Item> itemRemoved = new ArrayList<Item>();
		//		for (Shelf shelf : getShelves()) {
		//			// Temp iterator to avoid concurrent modification.
		//			List<Item> tempIterator = new ArrayList<Item>(shelf.getItems());
		//			for (Item item : tempIterator) {
		//				if (item.getName().equals(itemName)) {
		//					itemRemoved.add(item);
		//					shelf.setRemainingCapacity(shelf.getRemainingCapacity() + item.getCapacity());
		//					shelf.getItems().remove(item);
		//				}
		//			}
		//		}
		//		
		//	if(itemRemoved.isEmpty())
		//         throw new ItemNotFoundException(itemName);
		//	
		//	return itemRemoved;
	}

	
	/**
	 * Map function to map itemId to shelves.
	 * @return Map<ItemId,Shelf>
	 */
	private Map<Integer, Shelf> getmap() {
		Map<Integer,Shelf> map=new HashMap<Integer, Shelf>();
		for (Shelf shelf : getShelves()) {
			List<Item> itemList = shelf.getItems();
			for (Item item : itemList) {
				map.put(item.getId(), shelf);
			}
		}
		return map;
	}

	
	@Override
	public String toString() {
		return "Refrigerator [shelves=" + getShelves() + ", maxNoOfShelves=" + getShelves().size() + "]";
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}

}
