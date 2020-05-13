package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.time.DayOfWeek;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class AddNewFlight extends JDialog {

	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private DefaultListModel<String> destinations;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private JCheckBox sunday;
	private JCheckBox monday;
	private JCheckBox tuesday;
	private JCheckBox wednesday;
	private JCheckBox thursday;
	private JCheckBox friday;
	private JCheckBox saturday;
	@SuppressWarnings("unused")
	private GUI2 parent;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddNewFlight(DefaultListModel<String> destinations, GUI2 parent) {
		// Initializations:
		// Icon:
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());
		// Window properties:
		setResizable(false);
		setTitle("Add New Flight");
		this.destinations = destinations;
		setBounds(150, 100, 344, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		/////////////////////////////

		// Flight no text field:
		textField = new JTextField();
		textField.setBounds(84, 11, 183, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		/////////////////////////

		// Airlines text field:
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(84, 42, 183, 20);
		contentPanel.add(textField_1);
		/////////////////////////////

		// Aircraft text field:
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(84, 73, 183, 20);
		contentPanel.add(textField_2);
		/////////////////////////////

		// From combo box:
		comboBox = new JComboBox();
		comboBox.setBounds(84, 104, 183, 22);
		contentPanel.add(comboBox);
		// Add capitals as item:
		for (int i = 0; i < destinations.size(); i++) {
			comboBox.addItem(destinations.get(i));
		}
		///////////////////////////

		// To combo box:
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(84, 137, 183, 22);
		contentPanel.add(comboBox_1);
		// Add capitals as item:
		for (int i = 0; i < destinations.size(); i++) {
			comboBox_1.addItem(destinations.get(i));
		}
		////////////////////////////

		// Departure time:
		// Hour text field:
		textField_3 = new JTextField();
		textField_3.setBounds(84, 170, 20, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		// Minute text field:
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(114, 170, 20, 20);
		contentPanel.add(textField_4);
		/////////////////////////////

		// Arrival time:
		// Hour text field:
		textField_5 = new JTextField();
		textField_5.setBounds(84, 201, 20, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		// Minute text field:
		textField_6 = new JTextField();
		textField_6.setBounds(114, 201, 20, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		///////////////////////////

		// Delay text field:
		textField_7 = new JTextField();
		textField_7.setBounds(84, 232, 50, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);
		//////////////////////////

		// Labels:
		JLabel lblNewLabel = new JLabel("Flight No:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 14, 64, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Airlines:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 45, 64, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Aircraft:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(10, 76, 64, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("From:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(10, 108, 64, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("To:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setBounds(10, 141, 64, 14);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Departure:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(10, 173, 64, 14);
		contentPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Arrival:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(10, 204, 64, 14);
		contentPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Delay:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_7.setBounds(10, 235, 64, 14);
		contentPanel.add(lblNewLabel_7);
		////////////////////////////////

		// Checkboxes:
		sunday = new JCheckBox("Sunday");
		sunday.setBounds(140, 166, 78, 23);
		contentPanel.add(sunday);

		monday = new JCheckBox("Monday");
		monday.setBounds(218, 166, 92, 23);
		contentPanel.add(monday);

		tuesday = new JCheckBox("Tuesday");
		tuesday.setBounds(140, 192, 78, 23);
		contentPanel.add(tuesday);

		wednesday = new JCheckBox("Wednesday");
		wednesday.setBounds(218, 192, 92, 23);
		contentPanel.add(wednesday);

		thursday = new JCheckBox("Thursday");
		thursday.setBounds(140, 218, 78, 23);
		contentPanel.add(thursday);

		friday = new JCheckBox("Friday");
		friday.setBounds(218, 218, 92, 23);
		contentPanel.add(friday);

		saturday = new JCheckBox("Saturday");
		saturday.setBounds(140, 241, 78, 23);
		contentPanel.add(saturday);
		///////////////////////////
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				// Submit button:
				JButton okButton = new JButton("Submit");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int j = 0;
							int sameNo = 0;
							// Check if flight number exists already:
							while (j < parent.getFlights().size() && sameNo == 0) {
								if (parent.getTable()[j][0].equals(textField.getText()))
									sameNo++;
								j++;
							}
							int dayCount = 0;
							// Check if there is at least one day selected:
							if (sunday.isSelected() == true)
								dayCount++;
							else if (monday.isSelected() == true)
								dayCount++;
							else if (tuesday.isSelected() == true)
								dayCount++;
							else if (wednesday.isSelected() == true)
								dayCount++;
							else if (thursday.isSelected() == true)
								dayCount++;
							else if (friday.isSelected() == true)
								dayCount++;
							else if (saturday.isSelected() == true)
								dayCount++;
							// If inputs are valid, then update flight:
							if (Integer.parseInt(textField_3.getText()) >= 0
									&& Integer.parseInt(textField_3.getText()) < 24
									&& Integer.parseInt(textField_5.getText()) >= 0
									&& Integer.parseInt(textField_5.getText()) < 24
									&& Integer.parseInt(textField_4.getText()) >= 0
									&& Integer.parseInt(textField_4.getText()) < 60
									&& Integer.parseInt(textField_6.getText()) >= 0
									&& Integer.parseInt(textField_6.getText()) < 60
									&& Integer.parseInt(textField_7.getText()) >= 0
									&& comboBox.getSelectedItem() != comboBox_1.getSelectedItem() && dayCount != 0
									&& sameNo == 0) {

								Flight flight = new Flight(textField.getText(), textField_1.getText(),
										textField_2.getText(), comboBox.getSelectedItem().toString(),
										comboBox_1.getSelectedItem().toString(),
										LocalTime.of(Integer.parseInt(textField_3.getText()),
												Integer.parseInt(textField_4.getText())),
										LocalTime.of(Integer.parseInt(textField_5.getText()),
												Integer.parseInt(textField_6.getText())));
								flight.setDelay(Integer.parseInt(textField_7.getText()));
								parent.getFlights().add(flight);
								int i = parent.getFlights().size() - 1;
								parent.getTable()[i][0] = parent.getFlights().get(i).getFlightNo();
								parent.getTable()[i][1] = parent.getFlights().get(i).getAirlines();
								parent.getTable()[i][2] = parent.getFlights().get(i).getAircraft();
								parent.getTable()[i][3] = parent.getFlights().get(i).getFrom();
								parent.getTable()[i][4] = parent.getFlights().get(i).getTo();
								parent.getTable()[i][5] = "";
								parent.getTable()[i][6] = parent.getFlights().get(i).getDeparture().toString();
								parent.getTable()[i][7] = parent.getFlights().get(i).getArrival().toString();
								parent.getTable()[i][8] = Integer.toString(parent.getFlights().get(i).getDelay());
								if (sunday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(7));
									parent.getTable()[i][5] = parent.getTable()[i][5] + "SUNDAY ";
								}
								if (monday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(1));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "MONDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", MONDAY";
								}
								if (tuesday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(2));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "TUESDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", TUESDAY";
								}
								if (wednesday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(3));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "WEDNESDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", WEDNESDAY";
								}
								if (thursday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(4));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "THURSDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", THURSDAY";
								}
								if (friday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(5));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "FRIDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", FRIDAY";
								}
								if (saturday.isSelected()) {
									parent.getFlights().get(i).getDays().add(DayOfWeek.of(6));
									if (parent.getTable()[i][5] == "")
										parent.getTable()[i][5] = "SATURDAY";
									else
										parent.getTable()[i][5] = parent.getTable()[i][5] + ", SATURDAY";
								}
								parent.getScheduled().repaint();
								setVisible(false);
							}
							// If inputs are invalid, then show error message:
							else {
								JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						// If inputs are invalid, then show error message:
						catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				////////////////////////////////////////
			}
			{
				// Cancel button:
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				////////////////////////////
			}
		}
	}
}