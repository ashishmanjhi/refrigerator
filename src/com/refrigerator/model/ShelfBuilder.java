package com.refrigerator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link ShelfBuilder} class represents spaces which are present in a
 * refrigerator.
 * Builder Style
 */
public class ShelfBuilder {
	
	// Shelf id
	private int id;

	// Shelf remaining capacity in cubic centimeters
	private double remainingCapacity;

	// list of items inside the shelf eg:-apple,mango.
	List<Item> items = new ArrayList<Item>();
	
	
	/*
	 * Getters
	 */
	public int getId() {
		return id;
	}
	
	public double getRemainingCapacity() {
		return remainingCapacity;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	
	/**
	 * Constructor for ShelfBuilder
	 * @param builder class
	 */
	private ShelfBuilder(Builder builder) {
		this.id=builder.id;
		this.remainingCapacity=builder.remainingCapacity;
		this.items=builder.items;
	}


	/**
	 *  This {@link Builder} class builds the member of the ShelfBuilder class
	 * @author Ashish.manjhi
	 *
	 */
	public static class Builder{
		
		// Shelf id
		private int id;

		// Shelf remaining capacity in cubic centimeters
		private double remainingCapacity;

		// list of items inside the shelf eg:-apple,mango.
		List<Item> items = new ArrayList<Item>();
		
		public Builder(int id) {
			this.id=id;
		}
		
		public Builder byRemainingCapacity(double remainingCapacity) {
			this.remainingCapacity=remainingCapacity;
			return this;
		}
		
		public Builder atItem(List<Item> items) {
			this.items=items;
			return this;
		}
		
		// Builder
		public ShelfBuilder build() {		
			return new ShelfBuilder(this);
		}
	}
	
}
