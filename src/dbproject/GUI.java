package dbproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kyle
 */
public class GUI extends javax.swing.JFrame {

	/**
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<String>();
		jButton1 = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox<String>();
		jComboBox2 = new javax.swing.JComboBox<String>();
		jComboBox3 = new javax.swing.JComboBox();
		jComboBox4 = new javax.swing.JComboBox();
		jComboBox5 = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		listModel = new DefaultListModel<String>();
		jList1.setModel(listModel);
		jScrollPane1.setViewportView(jList1);

		jButton1.setText("View routes");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jList1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());

					jList1ActionPerformed(evt, index);
				}
			}
		});

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(stops
				.toArray()));
		stops.set(0, "Select end route");

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(stops
				.toArray()));
		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(days));
		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(times));
		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"A.M", "P.M"}));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														310, Short.MAX_VALUE)
												.addComponent(jScrollPane1)
												.addComponent(
														jComboBox1,
														0,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jComboBox2,
														javax.swing.GroupLayout.Alignment.TRAILING,
														0,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jComboBox3,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		154,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jComboBox4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		75,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jComboBox5,
																		0,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jComboBox1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jComboBox2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										14, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jComboBox3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										23,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}// </editor-fold>

	private void jList1ActionPerformed(java.awt.event.MouseEvent evt, int index) {
		String routeName = listModel.get(index);
		try {
			ResultSet rs = statement
					.executeQuery("Select StopName from BUS_STOP, ROUTE_STOPS, ROUTE WHERE StopID = Stop_ID AND Route_ID = RouteID AND RouteName = \""
							+ routeName + "\"");
			String routeStops = "Stops:\n";
			while (rs.next()) {
				routeStops = routeStops + (rs.getString("StopName")) + "\n";
			}
			JOptionPane.showMessageDialog(this.getContentPane(), routeStops,
					"Showing info for " + routeName, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		listModel.clear();
		if (((String) jComboBox1.getSelectedItem()).contains("Select")) {
			JOptionPane.showMessageDialog(this.getContentPane(),
					"Please select a starting route!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (((String) jComboBox2.getSelectedItem()).contains("Select")) {
			ResultSet rs;
			try {
				System.out.println(jComboBox1.getSelectedItem());
				rs = statement
						.executeQuery("Select distinct RouteName from BUS_STOP, ROUTE_STOPS, ROUTE WHERE StopID = Stop_ID AND Route_ID = RouteID AND  StopName = \""
								+ jComboBox1.getSelectedItem() + "\"");

				while (rs.next()) {
					listModel.addElement(rs.getString("RouteName"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else {
			ResultSet rs;
			try {
				System.out.println(jComboBox1.getSelectedItem());
				rs = statement
						.executeQuery("Select distinct RouteName from BUS_STOP, ROUTE_STOPS, ROUTE WHERE StopID = Stop_ID AND Route_ID = RouteID AND  StopName = \""
								+ jComboBox1.getSelectedItem()
								+ "\" AND RouteName IN (Select distinct RouteName from BUS_STOP, ROUTE_STOPS, ROUTE WHERE StopID = Stop_ID AND Route_ID = RouteID AND  StopName = \""
								+ jComboBox2.getSelectedItem() + "\")");

				while (rs.next()) {
					listModel.addElement(rs.getString("RouteName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		stops = new ArrayList<String>();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException ex) {
		}
		try {
			connection = DriverManager
					.getConnection("jdbc:sqlite:src/dbproject/bcrta.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery("Select * FROM Bus_Stop");
			stops.add("Select starting route");
			while (rs.next()) {
				stops.add(rs.getString("StopName"));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				new GUI().setVisible(true);
			}

		});
	}

	private String[] days = {"Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	private String[] times = { "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
	private static DefaultListModel<String> listModel;
	private static ArrayList<String> stops;
	private static Statement statement;
	private static Connection connection;
	private javax.swing.JButton jButton1;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JComboBox<String> jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;

}