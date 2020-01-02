package com.refrigerator.model;

/**
 * 
 * This {@link ItemBuilder} class represents items which can be kept in a refrigerator.
 * @author Ashish.manjhi
 * Builder Style
 * 
 */
public class ItemBuilder {
	
	// Item id.
	private	int id;
	// The name of the item . ex : Apple.
	private	String name;
	// The space which the item uses in cubic centimeters.
	private	double capacity;

	/*
	 * Getters
	 */
	
	/**
	 * @return Item id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return Item name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Item capacity
	 */
	public double getCapacity() {
		return capacity;
	}
	
	/**
	 * Constructor for Item model
	 * 
	 * @param builder class
	 */
	private ItemBuilder(Builder builder) {
	
		this.id=builder.id;
		this.name=builder.name;
		this.capacity=builder.capacity;
	
	}
	
	
	/**
	 * This {@link Builder} class set the members of ItemBuilder class
	 * @author Ashish.manjhi
	 *
	 */
	public static class Builder{
		
		// Item id.
		private	int id;
		// The name of the item . ex : Apple.
		private	String name;
		// The space which the item uses in cubic centimeters.
		private	double capacity;
		
		/*
		 * Builder Style 
		 */
		
		/**
		 * @param Item id
		 */
		public Builder(int id) {
			this.id=id;
		}
		
		/**
		 * @param Item name
		 * @return Item name
		 */
		public Builder withName(String name) {
			this.name=name;
			return this;
		}
		
		/**
		 * @param Item capacity
		 * @return Item capacity
		 */
		public Builder atCapacity(double capacity) {
			this.capacity=capacity;
			return this;
		}
		
		/**
		 * @return Item
		 */
		public ItemBuilder build() {		
			return new ItemBuilder(this);
		}
	}	
}

/*
 * ItemBuilder item =new ItemBuilder.Builder(101).withName("apple").atCapacity(5.0);
 */
