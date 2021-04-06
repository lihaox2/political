package com.bayee.political.domain;

public class AlarmScoreItem {
    private Integer id;

    private String itemName;
    
    private Integer dimension;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
	
}