package com.refrigerator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link Shelf} class represents spaces which are present in a
 * refrigerator.
 */
public class Shelf {

	// Shelf id
	private int id;

	// Shelf remaining capacity in cubic centimeters
	private double remainingCapacity;

	// list of items inside the shelf eg:-apple,mango.
	List<Item> items = new ArrayList<Item>();

	/**
	 * Default Constructor
	 */
	public Shelf() {

	}

	/**
	 * Constructor for Shelf model
	 * 
	 * @param id
	 * @param remainingCapacity
	 */
	public Shelf(int id, double remainingCapacity) {
		this.id = id;
		this.remainingCapacity = remainingCapacity;
	}

	/**
	 * @return Shelf id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setShelfId(int id) {
		this.id = id;
	}

	/**
	 * @return Shelf remaining space
	 */
	public double getRemainingCapacity() {
		return remainingCapacity;
	}

	/**
	 * @param Shelf remainingCapacity
	 */
	public void setRemainingCapacity(double remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "\n Shelf [shelfId=" + id + ", remainingCapacity=" + remainingCapacity + ", items=" + items + "]";
	}

}
