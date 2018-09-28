package com.yzsquare.model;

import java.util.Date;

public class Experience {

	private Boolean callSheetFree;

	private Date startDate;

	private Date endDate;

	private Craft craft;

	private ProductionHouse productionHouse;

	private Address address;

	public Boolean getCallSheetFree() {
		return callSheetFree;
	}

	public void setCallSheetFree(Boolean callSheetFree) {
		this.callSheetFree = callSheetFree;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Craft getCraft() {
		return craft;
	}

	public void setCraft(Craft craft) {
		this.craft = craft;
	}

	public ProductionHouse getProductionHouse() {
		return productionHouse;
	}

	public void setProductionHouse(ProductionHouse productionHouse) {
		this.productionHouse = productionHouse;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
