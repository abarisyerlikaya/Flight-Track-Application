package app;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalTime;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.Calendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class GUI2 extends JFrame {
	private GUI2 self;
	private ArrayList<Flight> scheduled;
	private ArrayList<Flight> started;
	private DefaultListModel<String> destinations;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblTimeControl_1_1;
	private JLabel lblTimeControl_1_1_1;
	private JLabel lblTimeControl_1_1_1_1;
	private Calendar setted;
	private Calendar now;
	private Thread time;
	private String[][] data;
	@SuppressWarnings("unused")
	private String[] heading;
	private String[][] data2;
	@SuppressWarnings("unused")
	private String[] heading2;
	private int lastProcess;

	public GUI2(DefaultListModel<String> destinations) {
		// Initialize:
		lastProcess = -1;
		self = this;
		this.destinations = destinations;
		// Icon:
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());
		// Window properties:
		setTitle("Flight Track Application");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 1022, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		////////////////////////////

		// Clear last report file and initalize for new report:
		try {
			FileWriter clear = new FileWriter("flight_report.txt");
			clear.write("REPORT OF FLIGHTS:");
			clear.flush();
			clear.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		////////////////////////

		// Scheduled flights table:
		String[] heading = { "Flight No", "Airlines", "Aircraft", "From", "To", "Weekdays", "Departure", "Arrival",
				"Delay" };
		data = new String[20][heading.length];
		initFlights(data);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 986, 214);
		contentPane.add(scrollPane);
		table = new JTable(data, heading);
		for (int i = 0; i < table.getColumnCount(); i++) {
			final DefaultCellEditor editor = (DefaultCellEditor) table.getDefaultEditor(table.getColumnClass(i));
			editor.setClickCountToStart(100);
		}
		scrollPane.setViewportView(table);
		JLabel lblNewLabel = new JLabel("Scheduled Flights");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 108, 986, 21);
		contentPane.add(lblNewLabel);
		////////////////////////////

		// Started flights table:
		String[] heading2 = { "Flight No", "Airlines", "Aircraft", "From", "To", "Date", "Sch. Departure", "Takeoff",
				"Sch. Arrival", "Landing", "Elapsed Minutes", "Status" };
		data2 = new String[20][heading2.length];
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 435, 986, 150);
		contentPane.add(scrollPane_1);
		table_1 = new JTable(data2, heading2);
		for (int i = 0; i < table_1.getColumnCount(); i++) {
			final DefaultCellEditor editor = (DefaultCellEditor) table_1.getDefaultEditor(table_1.getColumnClass(i));
			editor.setClickCountToStart(100);
		}
		scrollPane_1.setViewportView(table_1);
		JLabel lblNewLabel_1 = new JLabel("Started Flights");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 403, 986, 21);
		contentPane.add(lblNewLabel_1);
		/////////////////////////////

		// Add new flight:
		JButton btnNewButton = new JButton("Add New Flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewFlight dialog = new AddNewFlight(destinations, self);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 364, 316, 23);
		contentPane.add(btnNewButton);
		/////////////////////////////

		// Update flight:
		JButton btnUpdateFlight = new JButton("Update Flight");
		btnUpdateFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a row is selected, then ask for update:
				if (table.getSelectedRow() >= 0 && data[table.getSelectedRow()][0] != null) {
					UpdateFlight dialog = new UpdateFlight(destinations, self);
					dialog.setVisible(true);
				}
				// If not selected, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Please select a flight to update.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdateFlight.setBounds(336, 364, 315, 23);
		contentPane.add(btnUpdateFlight);
		////////////////////////////////

		// Delete flight:
		JButton btnDeleteFlight = new JButton("Delete Flight");
		btnDeleteFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a row is selected, then delete selected flight and shift table:
				if (table.getSelectedRow() >= 0 && data[table.getSelectedRow()][0] != null) {
					scheduled.remove(table.getSelectedRow());
					for (int i = table.getSelectedRow(); i < 19; i++) {
						for (int j = 0; j < heading.length; j++) {
							data[i][j] = data[i + 1][j];
						}
					}
					for (int i = 0; i < heading.length; i++)
						data[19][i] = null;
					table.repaint();
				}
				// If not selected, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Please select a flight to delete.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeleteFlight.setBounds(664, 364, 316, 23);
		contentPane.add(btnDeleteFlight);
		////////////////////////////////

		// Set system time:

		// Labels:
		JLabel lblTimeControl = new JLabel("Set System Time");
		lblTimeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimeControl.setBounds(10, 11, 148, 21);
		contentPane.add(lblTimeControl);

		JLabel lblNewLabel_2 = new JLabel("Date:");
		lblNewLabel_2.setBounds(10, 42, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("/");
		lblNewLabel_3.setBounds(72, 43, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("/");
		lblNewLabel_4.setBounds(104, 43, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_2_1 = new JLabel("Time:");
		lblNewLabel_2_1.setBounds(10, 73, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel(":");
		lblNewLabel_3_1.setBounds(73, 74, 46, 14);
		contentPane.add(lblNewLabel_3_1);

		// Day text field:
		textField = new JTextField();
		textField.setBounds(50, 40, 20, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// Month text field:
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 40, 20, 20);
		contentPane.add(textField_1);

		// Year text field:
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(110, 40, 40, 20);
		contentPane.add(textField_2);

		// Hour text field:
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(80, 71, 20, 20);
		contentPane.add(textField_3);

		// Minute text field:
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(50, 71, 20, 20);
		contentPane.add(textField_4);
		/////////////////////////////////

		// Set button:
		JButton btnNewButton_1 = new JButton("Set");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					// If input is valid, then set the time:
					if (Integer.parseInt(textField.getText()) > 0 && Integer.parseInt(textField.getText()) <= 31
							&& Integer.parseInt(textField_1.getText()) > 0
							&& Integer.parseInt(textField_1.getText()) <= 12
							&& Integer.parseInt(textField_4.getText()) >= 0
							&& Integer.parseInt(textField_4.getText()) < 24
							&& Integer.parseInt(textField_3.getText()) >= 0
							&& Integer.parseInt(textField_3.getText()) < 60
							&& Integer.parseInt(textField_2.getText()) >= 1970
							&& Integer.parseInt(textField.getText()) == new Date(
									Integer.parseInt(textField_2.getText()),
									Integer.parseInt(textField_1.getText()) - 1, Integer.parseInt(textField.getText()))
											.getDate()
							&& lastProcess != 0 && lastProcess != 2) {
						setted = Calendar.getInstance();
						setted.set(Integer.parseInt(textField_2.getText()), Integer.parseInt(textField_1.getText()) - 1,
								Integer.parseInt(textField.getText()), Integer.parseInt(textField_4.getText()),
								Integer.parseInt(textField_3.getText()));
						lblTimeControl_1_1.setText(String.format("%02d", setted.get(Calendar.HOUR_OF_DAY)) + ":"
								+ String.format("%02d", setted.get(Calendar.MINUTE)));
						lblTimeControl_1_1_1.setText(String.format("%02d", setted.get(Calendar.DAY_OF_MONTH)) + "/"
								+ String.format("%02d", setted.get(Calendar.MONTH) + 1) + "/"
								+ String.format("%04d", setted.get(Calendar.YEAR)));
						lblTimeControl_1_1_1_1
								.setText(setted.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
						now = Calendar.getInstance();
						now.setTime(setted.getTime());
					}
					// If time is ticking, then show error message:
					else if (lastProcess == 0 || lastProcess == 2) {
						JOptionPane.showMessageDialog(null, "Please stop time before setting it.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					// If input is not valid, then show error message:
					else {
						JOptionPane.showMessageDialog(null, "Please enter valid time.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				// If input is not valid, then show error message:
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please enter valid time.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(10, 96, 140, 23);
		contentPane.add(btnNewButton_1);
		///////////////////////////////

		// System time labels:

		// Title label:
		JLabel lblTimeControl_1 = new JLabel("System Time");
		lblTimeControl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimeControl_1.setBounds(816, 11, 190, 21);
		contentPane.add(lblTimeControl_1);

		// Hour label:
		lblTimeControl_1_1 = new JLabel("-- : --");
		lblTimeControl_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTimeControl_1_1.setBounds(816, 43, 190, 21);
		contentPane.add(lblTimeControl_1_1);

		// Date label:
		lblTimeControl_1_1_1 = new JLabel("--/--/----");
		lblTimeControl_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeControl_1_1_1.setBounds(816, 74, 190, 21);
		contentPane.add(lblTimeControl_1_1_1);

		// Day label:
		lblTimeControl_1_1_1_1 = new JLabel("-");
		lblTimeControl_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeControl_1_1_1_1.setBounds(816, 96, 190, 21);
		contentPane.add(lblTimeControl_1_1_1_1);

		// Time control label:
		JLabel lblTimeControl_2 = new JLabel("Time Control");
		lblTimeControl_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeControl_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimeControl_2.setBounds(10, 11, 986, 21);
		contentPane.add(lblTimeControl_2);
		/////////////////////////////////

		// Start / resume button:
		JButton btnNewButton_2 = new JButton("Start / Resume");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If time is setted by user, then start time:
				if (now != null) {
					time = new Thread() {
						public void run() {
							while (true) {
								try {
									// Set labels with current time:
									lblTimeControl_1_1.setText(String.format("%02d", now.get(Calendar.HOUR_OF_DAY))
											+ ":" + String.format("%02d", now.get(Calendar.MINUTE)));
									lblTimeControl_1_1_1.setText(String.format("%02d", now.get(Calendar.DAY_OF_MONTH))
											+ "/" + String.format("%02d", now.get(Calendar.MONTH) + 1) + "/"
											+ String.format("%04d", now.get(Calendar.YEAR)));
									lblTimeControl_1_1_1_1.setText(
											now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
									// Check if is there a flight should start now:
									for (int i = 0; i < scheduled.size(); i++) {
										// If there is a flight scheduled for now, then start it:
										if (scheduled.get(i).getDays()
												.contains(DayOfWeek.of(now.get(Calendar.DAY_OF_WEEK)).minus(1))
												&& scheduled.get(i).getDeparture()
														.plusMinutes(scheduled.get(i).getDelay())
														.getHour() == now.get(Calendar.HOUR_OF_DAY)
												&& scheduled.get(i).getDeparture()
														.plusMinutes(scheduled.get(i).getDelay())
														.getMinute() == now.get(Calendar.MINUTE)) {
											startFlight(i);
										}
									}
									sleep(1000);
									now.add(Calendar.MINUTE, 1);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}

							}
						}
					};
					time.start();
					lastProcess = 0;
				}
				// If time is not setted by user, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Please set the time before start.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(320, 50, 118, 32);
		contentPane.add(btnNewButton_2);
		///////////////////////////////

		// Stop button:
		JButton btnNewButton_3 = new JButton("Stop");
		btnNewButton_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// If time is ticking, then stop:
				if (lastProcess == 0 || lastProcess == 2) {
					time.stop();
					// Interrupt the current flights:
					for (int i = 0; i < started.size(); i++) {
						if (data2[i][11] == "Took off" || data2[i][11] == "Waiting approval") {
							data2[i][11] = "Interrupted";
							table_1.repaint();
						}
					}
					// Turn back to setted time:
					now.setTime(setted.getTime());
					lblTimeControl_1_1.setText(String.format("%02d", now.get(Calendar.HOUR_OF_DAY)) + ":"
							+ String.format("%02d", now.get(Calendar.MINUTE)));
					lblTimeControl_1_1_1.setText(String.format("%02d", now.get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.format("%02d", now.get(Calendar.MONTH) + 1) + "/"
							+ String.format("%04d", now.get(Calendar.YEAR)));
					lblTimeControl_1_1_1_1.setText(now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
					lastProcess = 1;
				}
				// If time is not ticking, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Time is not started, stop failed.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_3.setBounds(570, 50, 118, 32);
		contentPane.add(btnNewButton_3);
		////////////////////////////////

		// Pause button:
		JButton btnNewButton_3_1 = new JButton("Pause");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// If time is ticking, then stop:
				if (lastProcess == 0) {
					time.stop();
					lastProcess = 2;
				}
				// If time is not ticking, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Time is not started, pause failed.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_3_1.setBounds(445, 50, 118, 32);
		contentPane.add(btnNewButton_3_1);
		/////////////////////////////////

		// Cancel flight (control tower):
		JButton btnDeleteFlight_1 = new JButton("Cancel Flight");
		btnDeleteFlight_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a flight is selected:
				if (table_1.getSelectedRow() >= 0 && data2[table_1.getSelectedRow()][0] != null) {
					int i = table_1.getSelectedRow();
					// If selected flight is waiting approval, then cancel it:
					if (data2[i][11] == "Waiting approval") {
						data2[i][11] = "Cancelled";
					}
					//// If selected flight is not waiting approval, then show error message:
					else {
						JOptionPane.showMessageDialog(null, "Flight is not waiting approval from tower, cancel failed.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				// If not selected, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Please select a flight to cancel.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeleteFlight_1.setBounds(502, 596, 316, 23);
		contentPane.add(btnDeleteFlight_1);
		///////////////////////////////////

		// Approve Flight (Control Tower):
		JButton btnDeleteFlight_1_1 = new JButton("Approve Flight");
		btnDeleteFlight_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a flight is selected, then approve it:
				if (table_1.getSelectedRow() >= 0 && data2[table_1.getSelectedRow()][0] != null) {
					int i = table_1.getSelectedRow();
					// If selected flight is waiting approval, then approve it:
					if (data2[i][11] == "Waiting approval") {
						data2[i][11] = "Approved";
						table_1.repaint();
					}
					// If selected flight is not waiting approval, then show error message:
					else {
						JOptionPane.showMessageDialog(null, "Flight is not waiting approval from tower, approve failed.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				// If not selected, then show error message:
				else {
					JOptionPane.showMessageDialog(null, "Please select a flight to approve.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnDeleteFlight_1_1.setBounds(175, 596, 316, 23);
		contentPane.add(btnDeleteFlight_1_1);
		/////////////////////////////////
	}

	public void initFlights(String[][] data) {
		scheduled = new ArrayList<Flight>();
		started = new ArrayList<Flight>();
		scheduled.add(new Flight("QTR1336", "Qatar Airways", "Airbus A320", destinations.get(1), destinations.get(3),
				DayOfWeek.MONDAY, LocalTime.of(02, 30), LocalTime.of(22, 30)));
		scheduled.add(new Flight("SNG0274", "Singapore Airlines", "Boeing 777", destinations.get(0),
				destinations.get(4), DayOfWeek.MONDAY, LocalTime.of(13, 15), LocalTime.of(19, 15)));
		scheduled.add(new Flight("EMR2104", "Emirates", "Airbus A350 XWB", destinations.get(3), destinations.get(2),
				DayOfWeek.TUESDAY, LocalTime.of(00, 15), LocalTime.of(23, 45)));
		scheduled.add(new Flight("THY2100", "Turkish Airlines", "Boeing 787-9", destinations.get(2),
				destinations.get(1), DayOfWeek.WEDNESDAY, LocalTime.of(8, 15), LocalTime.of(16, 00)));
		scheduled.add(new Flight("ANA2288", "All Nippon Airways", "Bombardier 8-400", destinations.get(4),
				destinations.get(0), DayOfWeek.WEDNESDAY, LocalTime.of(13, 00), LocalTime.of(23, 15)));
		scheduled.add(new Flight("CTH6126", "Cathay Pacific Airways", "Airbus A330", destinations.get(0),
				destinations.get(1), DayOfWeek.THURSDAY, LocalTime.of(11, 00), LocalTime.of(22, 45)));
		scheduled.add(new Flight("EVA5202", "EVA Air", "Airbus A321", destinations.get(1), destinations.get(2),
				DayOfWeek.FRIDAY, LocalTime.of(00, 15), LocalTime.of(8, 45)));
		scheduled.add(new Flight("HAI1427", "Hainan Airlines", "Boeing 787-8", destinations.get(2), destinations.get(3),
				DayOfWeek.FRIDAY, LocalTime.of(1, 00), LocalTime.of(23, 45)));
		scheduled.add(new Flight("ACA2300", "Air Canada", "Embraer E175", destinations.get(3), destinations.get(4),
				DayOfWeek.SATURDAY, LocalTime.of(9, 00), LocalTime.of(19, 00)));
		scheduled.add(new Flight("DLT0824", "Delta Air", "MD-88", destinations.get(4), destinations.get(0),
				DayOfWeek.SUNDAY, LocalTime.of(17, 15), LocalTime.of(23, 30)));
		scheduled.get(1).addDay(DayOfWeek.of(3));
		scheduled.get(5).addDay(DayOfWeek.of(6));
		scheduled.get(7).addDay(DayOfWeek.of(7));
		scheduled.get(2).setDelay(30);
		scheduled.get(6).setDelay(15);
		scheduled.get(8).setDelay(20);
		for (int i = 0; i < scheduled.size(); i++) {
			data[i][0] = scheduled.get(i).getFlightNo();
			data[i][1] = scheduled.get(i).getAirlines();
			data[i][2] = scheduled.get(i).getAircraft();
			data[i][3] = scheduled.get(i).getFrom();
			data[i][4] = scheduled.get(i).getTo();
			data[i][5] = scheduled.get(i).getDay(0).toString();
			for (int j = 1; j < scheduled.get(i).getDayCount(); j++)
				data[i][5] = data[i][5] + ", " + scheduled.get(i).getDay(j).toString();
			data[i][6] = scheduled.get(i).getDeparture().toString();
			data[i][7] = scheduled.get(i).getArrival().toString();
			data[i][8] = Integer.toString(scheduled.get(i).getDelay());
		}
	}

	public ArrayList<Flight> getFlights() {
		return this.scheduled;
	}

	public String[][] getTable() {
		return this.data;
	}

	public JTable getScheduled() {
		return this.table;
	}

	public void startFlight(int i) {
		started.add(scheduled.get(i));
		// Add to started table:
		int j = started.size() - 1;
		data2[j][0] = started.get(j).getFlightNo();
		data2[j][1] = started.get(j).getAirlines();
		data2[j][2] = started.get(j).getAircraft();
		data2[j][3] = started.get(j).getFrom();
		data2[j][4] = started.get(j).getTo();
		data2[j][5] = String.format("%02d", now.get(Calendar.DAY_OF_MONTH)) + "/"
				+ String.format("%02d", now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
		data2[j][6] = started.get(j).getDeparture().toString();
		data2[j][7] = started.get(j).getDeparture().plusMinutes(started.get(j).getDelay()).toString();
		data2[j][8] = started.get(j).getArrival().toString();
		data2[j][9] = "Not landed";
		data2[j][10] = "0";
		data2[j][11] = "Took off";
		table_1.repaint();
		// Start thread for the flight:
		(new Fly(now, started, table_1, data2, j)).start();
	}

	public int getLastProcess() {
		return this.lastProcess;
	}
}