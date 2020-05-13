package app;

import java.util.Calendar;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JTable;

public class Fly extends Thread {
	private Calendar now;
	private ArrayList<Flight> started;
	private String[][] data2;
	private int index;
	private JTable table_1;

	public Fly(Calendar now, ArrayList<Flight> started, JTable table_1, String[][] data2, int index) {
		this.now = now;
		this.started = started;
		this.data2 = data2;
		this.index = index;
		this.table_1 = table_1;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		LocalTime landing = started.get(index).getArrival().plusMinutes(started.get(index).getDelay());
		while (true) {
			try {
				// If flight is cancelled by tower or intterrupted, then stop thread:
				if (data2[index][11] == "Cancelled" || data2[index][11] == "Interrupted") {
					reportFlight(index);
					table_1.repaint();
					stop();
				}
				// Updating minute for each 200 ms:
				data2[index][10] = Long
						.toString(started.get(index).getDeparture().plusMinutes(started.get(index).getDelay()).until(
								LocalTime.of(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE)),
								ChronoUnit.MINUTES));
				table_1.repaint();
				// If landing time has come:
				if (now.get(Calendar.HOUR_OF_DAY) == landing.getHour()
						&& now.get(Calendar.MINUTE) == landing.getMinute()) {
					// If approved, then arrive, else wait in the air:
					if (data2[index][11] == "Approved") {
						data2[index][11] = "Arrived";
						data2[index][9] = String.format("%02d", (now.get(Calendar.HOUR_OF_DAY))) + ":"
								+ String.format("%02d", (now.get(Calendar.MINUTE)));
						table_1.repaint();
						reportFlight(index);
						stop();
					}
				}
				// If the flight waiting in the air is approved, then arrive:
				if (LocalTime.of(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE)).isAfter(landing)
						&& data2[index][11] == "Approved") {
					data2[index][11] = "Arrived";
					data2[index][9] = String.format("%02d", (now.get(Calendar.HOUR_OF_DAY))) + ":"
							+ String.format("%02d", (now.get(Calendar.MINUTE)));
					reportFlight(index);
					stop();
				}
				// When 15 mins remaining, wait for approval:
				else if (now.get(Calendar.HOUR_OF_DAY) == landing.minusMinutes(15).getHour()
						&& now.get(Calendar.MINUTE) == landing.minusMinutes(15).getMinute()) {
					JOptionPane.showMessageDialog(null,
							"The flight " + data2[index][0] + " is waiting approval from the control tower " + "of "
									+ data2[index][4]
									+ ". Airplane is going to wait in the air until the tower's approval.",
							"Info", JOptionPane.INFORMATION_MESSAGE);
					data2[index][11] = "Waiting approval";
					table_1.repaint();
				}
				sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	// Function reports the flight with given index to file (flight_report.txt):
	public void reportFlight(int index) {
		try {
			FileWriter out = new FileWriter(new File("flight_report.txt"), true);
			String text = "\n\n*******************************************\n" + "Flight Number: " + data2[index][0]
					+ "\n" + "Airlines: " + data2[index][1] + "\n" + "Aircraft Model: " + data2[index][2] + "\n"
					+ "Departure Destination: " + data2[index][3] + "\n" + "Arrival Destination: " + data2[index][4]
					+ "\n" + "Flight Date: " + data2[index][5] + "\n" + "Scheduled Departure Time: " + data2[index][6]
					+ "\n" + "Scheduled Arrival Time: " + started.get(index).getArrival().toString() + "\n"
					+ "Actual Takeoff Time: " + data2[index][7] + "\n" + "Actual Landing Time: " + data2[index][9]
					+ "\n" + "Time Taken (minutes): " + data2[index][10] + "\n" + "Result: " + data2[index][11] + "\n"
					+ "*******************************************";
			out.write(text);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}