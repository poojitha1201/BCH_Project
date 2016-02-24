package com.model;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Poojitha_Alla
 *
 */
@XmlRootElement(name="Vehicles")
public class VehicleList {
	
	private List<Vehicle> vehicles;

	public VehicleList(){
		
	}
	public VehicleList(List<Vehicle> vehicles) {
		this.vehicles=vehicles;
	}
	@XmlElement(name="Vehicle")
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
