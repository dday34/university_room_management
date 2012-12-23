package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import BL.TeacherFacade;
import javax.swing.JTable;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;


/**	
 *  Génére l'interface graphique pour la consultation de l'emploi du temps.	
 * @author URM Team		
 */		
@SuppressWarnings("serial")		
class ConsulterView extends JFrame implements ActionListener		
{		
	/**		
	 * la semaine à afficher dans l'emploi du temps. Par défaut la semaine en cours.		
	 */		
	private int week;		

	/**
	 * Façade permettant le dialogue avec la BL.	33		 *
	 */
	private TeacherFacade account;


	/**
	 * Table contenant l'emploi du temps.
	 */
	private JTable table;

	/**
	 * Bouton ppour passer à la semaine précédente.
	 */
	private JButton bPrecedent;

	/**
	 * Bouton pour passer à la semaine suivante.
	 */
	private JButton bSuivant;

	/**
	 * Bouton pour quitter l'emploi du temps.
	 */
	private JButton bQuit;

	/**
	 * Panneau qui contient le bouton pour quitter.
	 */
	private JPanel quit_panel;

	/**
	 * Panneau qui contient l'emploi du temps.
	 */
	private JPanel planning_panel;

	/**
	 * Constructeur de la fenêtre.
	 * @param c
	 * 			Façade contenant les données de l'utilisateur et permettant le dialogue avec la BL.
	 */
	public ConsulterView(TeacherFacade c) 
	{
		super("Mon planning");

		this.account = c;

		this.setSize(new Dimension(965, 467));
		getContentPane().setLayout(null);

		planning_panel = new JPanel();
		planning_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		planning_panel.setBounds(10, 10, 929, 356);
		getContentPane().add(planning_panel);
		planning_panel.setLayout(null);

		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.GRAY, null, null));
		table.setBounds(73, 4, 783, 341);
		table.setBackground(SystemColor.menu);
		planning_panel.add(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{"Horaires", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"},
						{"8h00 - 9h30", null, null, null, null, null, null, null},
						{"9h45 - 11h15", null, null, null, null, null, null, null},
						{"11h30 - 13h00", null, null, null, null, null, null, null},
						{"13h15 - 14h45", null, null, null, null, null, null, null},
						{"15h00 - 16h30", null, null, null, null, null, null, null},
						{"16h45 - 18h15", null, null, null, null, null, null, null},
						{"18h30 - 20h00", null, null, null, null, null, null, null},
				},
				new String[] {
						"Horaires", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setRowHeight(42);

		TableCellRenderer myRenderer = new MyTableCellRenderer();
		try 
		{
			table.setDefaultRenderer(Class.forName("java.lang.Object"), myRenderer);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

		bPrecedent = new JButton("<");
		bPrecedent.setBounds(9, 113, 58, 46);
		planning_panel.add(bPrecedent);
		bPrecedent.setActionCommand("Precedent");

		bSuivant = new JButton(">");
		bSuivant.setBounds(862, 113, 57, 46);
		planning_panel.add(bSuivant);
		bSuivant.setActionCommand("Suivant");
		bSuivant.addActionListener(this);
		bPrecedent.addActionListener(this);

		quit_panel = new JPanel();
		quit_panel.setBounds(0, 377, 939, 41);
		getContentPane().add(quit_panel);
		quit_panel.setLayout(null);

		bQuit = new JButton("Fermer");
		bQuit.setBounds(845, 9, 94, 23);
		quit_panel.add(bQuit);
		bQuit.setActionCommand("Fermer");
		bQuit.addActionListener(this);
		bQuit.setAlignmentX(Component.RIGHT_ALIGNMENT);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		Calendar cal = new GregorianCalendar();
		java.util.Date date= cal.getTime();
		SimpleDateFormat weekNum = new SimpleDateFormat("w");

		this.week = Integer.parseInt(weekNum.format(date));	
		this.genCalendar(week);
	}


	/**
	 * Create specific Cell renderer.
	 */
	public class MyTableCellRenderer extends DefaultTableCellRenderer
	{

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column)
		{
			JTextPane cellTable = new JTextPane();
			StyledDocument doc = cellTable.getStyledDocument();
			
			MutableAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, center, true);


			if ((String) value != null)
			{
				cellTable.setText((String)value);
			}

			return cellTable;
		} 

	}

	/**
	 * Génère l'affichage du calendrier en fonction de la semaine qu'il reçoit en paramètre.
	 * @param week
	 * 			génére les réservations de cette semaine.
	 */
	public void genCalendar(int week)
	{
		int i;
		ArrayList<ArrayList<String>> infosPlanning;
		infosPlanning = account.getValidBooking(week);

		Calendar cal = new GregorianCalendar();
		initPlaning();
		init_labels(week);
		for (i=0; i<infosPlanning.size(); i++) //modification de int i = 0 en int i = week
		{
			//DATE
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
			try {
				Date date = dateFormat.parse(infosPlanning.get(i).get(0));
				cal.setTime(date);

				// CRENEAU
				int cren = 1;
				while(cren<table.getRowCount() && ((String) table.getValueAt(cren,0)).startsWith(infosPlanning.get(i).get(1))==false)
				{
					++cren;
				}
				if (cren<table.getRowCount()) 
				{
					if (cal.get(Calendar.DAY_OF_WEEK) == 1) /* qui correspond a SUNDAY */
					{
						table.setValueAt(infosPlanning.get(i).get(3), cren, cal.get(Calendar.DAY_OF_WEEK)+6);
					}
					else
					{
						table.setValueAt(infosPlanning.get(i).get(3), cren, cal.get(Calendar.DAY_OF_WEEK)-1);
					}

				}
				else 
				{
					System.out.println("Creneau inexistant!");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Initialise les labels de la semaine avec les bonnes dates.
	 * @param week
	 * 			Numéro de la semaine à générer.
	 */
	private void init_labels(int week)
	{
		Calendar cal = new GregorianCalendar();
		String days[] = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};

		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		for(int i=1;i<=days.length;i++)
		{
			table.setValueAt(days[i-1]+" "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1),0,i);
			cal.add(Calendar.DAY_OF_WEEK,1);
		}
	}

	/**
	 * Initialise les valeurs du tableau.
	 */
	private void initPlaning() 
	{
		int i,j;
		for(i=1; i<table.getRowCount(); i++)
		{
			for(j=1; j<table.getColumnCount();j++) 
			{
				table.setValueAt(null,i,j);
			}
		}
	}

	/**
	 * écoute les événements provenant d'un clic sur les bouton  - méthode issue de l'interface ActionListener
	 * @param e
	 * 			évenement provenant d'un clic sur un bouton
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Suivant")) 
		{
			this.week++;
			genCalendar(week);
		}
		else if (e.getActionCommand().equals("Precedent"))  
		{
			this.week--;
			genCalendar(week);
		}
		else if (e.getActionCommand().equals("Fermer"))  
		{
			this.setVisible(false);
		}
	}


}
