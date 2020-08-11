package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Example {
	public static void main(String[] args) {

		ObjectMapper objectMapper = new ObjectMapper();

		// Reading a Java object from a JSON string
		String carJson =
				"{\"brand\" : \"Mercedes\", \"doors\" : 5 }";
		Car car = null;
		try {
			car = objectMapper.readValue(carJson, Car.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("car brand = " + car.getBrand());
		System.out.println("car doors = " + car.getDoors());

		// You can also read an object from JSON loaded via a Reader instance.
		Reader reader = new StringReader(carJson);

		try {
			objectMapper.readValue(reader, Car.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("car brand = " + car.getBrand());
		System.out.println("car doors = " + car.getDoors());

		// Reading JSON from a file can of course be done via a FileReader
		File file1 = new File("data/car.json");

		try {
			car = objectMapper.readValue(file1, Car.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("car brand = " + car.getBrand());
		System.out.println("car doors = " + car.getDoors());

		// You can read an object from JSON via a URL (java.net.URL) like this:
		URL url = null;
		try {
			url = new URL("file:data/car.json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			car = objectMapper.readValue(url, Car.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("car brand = " + car.getBrand());
		System.out.println("car doors = " + car.getDoors());

		// Jackson also supports reading objects from a JSON byte array.
		byte[] bytes = new byte[0];
		try {
			bytes = carJson.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			car = objectMapper.readValue(bytes, Car.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("car brand = " + car.getBrand());
		System.out.println("car doors = " + car.getDoors());

		// The Jackson ObjectMapper can also read an array of objects from a JSON array string.
		File file2 = new File("data/cars.json");
		List<Car> cars = null;
		Car[] cars1 = null;

		try {
			cars1 = objectMapper.readValue(file2, Car[].class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		for (Car c : cars1) {
			System.out.println("car brand = " + c.getBrand());
			System.out.println("car doors = " + c.getDoors());
		}

		// The Jackson ObjectMapper can also read a Java List of objects from a JSON array string.
		try {
			cars = objectMapper.readValue(file2, new TypeReference<List<Car>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Car c : cars) {
			System.out.println("car brand = " + c.getBrand());
			System.out.println("car doors = " + c.getDoors());
		}

		// The Jackson ObjectMapper can also read a Java Map from a JSON string.
		// Usually you will be reading a JSON object into a Java Map.
		Map<String, Object> carMap = null;
		try {
			carMap = objectMapper.readValue(file1, new TypeReference<Map<String, Object>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Map.Entry<String, Object> carEntry: carMap.entrySet()) {
			System.out.println("Key: " + carEntry.getKey());
			System.out.println("Car: " + carEntry.getValue());
		}

		// Sometimes you have more fields in the JSON than you do in the Java object you want to read from the JSON.
		// By default Jackson throws an exception in that case.
		// objectMapper.configure(Des)
	}
}
