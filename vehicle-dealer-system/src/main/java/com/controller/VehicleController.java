package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.VehicleInventory;
import com.model.Vehicle;
import com.model.VehicleList;

/**
 * 
 * @author Poojitha_Alla
 *
 */
@RestController
public class VehicleController {
	
	@Autowired
	private VehicleInventory vehicleInventory;
	
	@RequestMapping(value="/vehicles",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public  VehicleList getVehicles(){
		List<Vehicle> vehicles =  vehicleInventory.getVehicles();
		return new VehicleList(vehicles.stream().sorted((p1,p2) -> Double.compare(p1.getPrice(),p2.getPrice())).collect(Collectors.toList()));
	}
	
	@RequestMapping(value="/vehicles/{type}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.ALL_VALUE})
	@ResponseBody
	public String getReportByAverage(@PathVariable String type){
		List<Vehicle> vehicles =  vehicleInventory.getVehicles();
		Map<String,Double> reportMap= null;
		if(type.equals("brand")){
		reportMap = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getBrand,Collectors.averagingDouble(Vehicle::getPrice)));
		}else if(type.equals("vehicle")){
			reportMap = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getVehicleType,Collectors.averagingDouble(Vehicle::getPrice)));
		}else if(type.equals("color")){
			reportMap = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getColor,Collectors.averagingDouble(Vehicle::getPrice)));
		}else if(type.equals("engine")){
			reportMap = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getEngineType,Collectors.averagingDouble(Vehicle::getPrice)));
		}else{
			return null;
		}
	
		return reportMap.toString();
	}
	

}
