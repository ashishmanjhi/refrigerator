package com.refrigerator.model;

/**
 * This {@link Item} class represents which can be kept in a refrigerator.
 *
 */
public class Item {

	// Item id.
	int id;
	// The name of the item . ex : Apple.
	String name;
	// The space which the item uses in cubic centimeters.
	double capacity;

	/**
	 * Default constructor/
	 */
	public Item() {
	}

	/**
	 * Constructor for Item model
	 * 
	 * @param Item id
	 * @param Item name
	 * @param Item capacity
	 */
	public Item(int id, String name, double capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	// TODO Use Builder Pattern for getters and setters.
	
	/**
	 * @return Item id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param Item id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param Item name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @param Item id
	 * @param Item name
	 */
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", capacity=" + capacity + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item other = (Item) obj;
			return id == other.getId();
		}
		return false;
	}

}
