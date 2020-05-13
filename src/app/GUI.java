package app;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel contentPane;
	private DefaultListModel<String> destinations;
	private JList<String> list;

	public GUI() {
		// Initialize:
		// Destinations list:
		destinations = new DefaultListModel<>();
		destinations.addElement("Istanbul");
		destinations.addElement("New York");
		destinations.addElement("Paris");
		destinations.addElement("Sydyney");
		destinations.addElement("Tokyo");
		// Icon:
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());
		// Window properties:
		setTitle("Flight Track Application");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		////////////////////////////

		// Add new button:
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String newDestination = JOptionPane.showInputDialog(null, "Please enter new destination:",
							"Add New Destination", JOptionPane.PLAIN_MESSAGE);
					// If input is not valid, then show error message:
					if (newDestination.length() == 0)
						JOptionPane.showMessageDialog(null, "Please enter a valid input.", "Error",
								JOptionPane.ERROR_MESSAGE);
					// If input is valid, then add it as a new capital:
					else if (newDestination != null)
						destinations.addElement(newDestination);
				} catch (NullPointerException e1) {
				}
			}
		});
		btnNewButton.setBounds(10, 251, 89, 23);
		contentPane.add(btnNewButton);
		/////////////////////////////

		// Update button:
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// If a capital is selected, then ask for update:
					if (list.getSelectedIndex() >= 0) {
						String updated = JOptionPane.showInputDialog(null, "Please enter updated destination:",
								"Update Destination", JOptionPane.PLAIN_MESSAGE);
						// If input is not valid, then show error message:
						if (updated.length() == 0)
							JOptionPane.showMessageDialog(null, "Please enter a valid input.", "Error",
									JOptionPane.ERROR_MESSAGE);
						// If input is valid, then add it as a new capital:
						else if (updated != null)
							destinations.set(list.getSelectedIndex(), updated);
					} 
					// If not selected, then show error message:
					else
						JOptionPane.showMessageDialog(null, "Please select a destination to update.", "Error",
								JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {
				}
			}
		});
		btnNewButton_2.setBounds(109, 251, 89, 23);
		contentPane.add(btnNewButton_2);
		////////////////////////////////

		// Delete button:
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a capital is selected, then delete:
				if (list.getSelectedIndex() >= 0) {
					// If there is more than 5 capitals, then delete selected capital:
					if (list.getModel().getSize() > 5)
						destinations.remove(list.getSelectedIndex());
					// If there is 5 capitals remaining, show error message:
					else
						JOptionPane.showMessageDialog(null, "There must be at least 5 destinations. Delete failed.",
								"Error", JOptionPane.ERROR_MESSAGE);
				} 
				// If not selected, then show error message:
				else
					JOptionPane.showMessageDialog(null, "Please select a destination.", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(208, 251, 89, 23);
		contentPane.add(btnNewButton_1);
		///////////////////////////////

		// Initialize JList:
		list = new JList<>(destinations);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 47, 287, 193);
		contentPane.add(list);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 45, 287, 195);
		contentPane.add(scrollPane);
		//////////////////////

		// Destinations label:
		JLabel lblNewLabel = new JLabel("Capitals:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 210, 37);
		contentPane.add(lblNewLabel);
		////////////////////////////
		
		// Proceed button:
		JButton btnNewButton_3 = new JButton("Proceed");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI2 frame = new GUI2(destinations);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(10, 285, 287, 41);
		contentPane.add(btnNewButton_3);
		////////////////////////////////
	}
}