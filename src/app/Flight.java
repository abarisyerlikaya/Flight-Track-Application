package app;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight {
	private String flightNo;
	private String airlines;
	private String aircraft;
	private String from;
	private String to;
	private ArrayList<DayOfWeek> days;
	private LocalTime departure;
	private LocalTime arrival;
	private int delay;

	// Constructor with one day setted:
	public Flight(String flightNo, String airlines, String aircraft, String from, String to, DayOfWeek day,
			LocalTime departure, LocalTime arrival) {
		this.days = new ArrayList<DayOfWeek>();
		this.flightNo = flightNo;
		this.airlines = airlines;
		this.aircraft = aircraft;
		this.from = from;
		this.to = to;
		this.days.add(day);
		this.departure = departure;
		this.arrival = arrival;
		this.delay = 0;
	}

	// Constructor without any day setted: (Days can be added via addDay() function)
	public Flight(String flightNo, String airlines, String aircraft, String from, String to, LocalTime departure,
			LocalTime arrival) {
		this.days = new ArrayList<DayOfWeek>();
		this.flightNo = flightNo;
		this.airlines = airlines;
		this.aircraft = aircraft;
		this.from = from;
		this.to = to;
		this.departure = departure;
		this.arrival = arrival;
		this.delay = 0;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public DayOfWeek getDay(int index) {
		return days.get(index);
	}

	public int getDayCount() {
		return days.size();
	}

	public ArrayList<DayOfWeek> getDays() {
		return this.days;
	}

	public void addDay(DayOfWeek day) {
		this.days.add(day);
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
}