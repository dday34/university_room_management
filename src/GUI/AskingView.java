package GUI;
import java.awt.Dimension;
import javax.swing.*;

import BL.TeacherFacade;

import com.toedter.calendar.JCalendar;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Génére l'interface graphique pour les demandes de réservation.
 * @author URM Team
 */
@SuppressWarnings("serial")
class AskingView extends JFrame implements ActionListener, ItemListener, PropertyChangeListener, ListSelectionListener
{
	/**
	 * Façade permettant le dialogue avec la BL.
	 */
    private TeacherFacade account;
    
    /**
     * Enseignement sélectionné par l'utilisateur.
     */
    private String teachingSelected;
    
    /**
     * Date sélectionnée par l'utilisateur.
     */
    private Date dateSelected;
    
    /**
     * Créneau sélectionné par l'utilisateur
     */
    private String scheduleSelected;
    
    /**
     * Ensemble des caractéristiques sélectionnées par l'utilisateur.
     */
    private ArrayList<String> featuresSelected = new ArrayList<String>();
    
    /**
     * Capacité de la salle demandée par l'utilisateur.
     */
    private int capacity = 0;
    
    /**
     * Commentaires entrés par l'utilisateur.
     */
    private String comments = "";
    
    /**
     * Groupe de bouton qui rassemble les radio boutons réunion et enseignement.
     */
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    /**
     * Boite d'éddition de la capacitée de la salle.
     * @see AskingView#actionPerformed(ActionEvent)
     */
    private JTextField tfCapacity;
    
    /**
     * Boite déroulante du choix d'enseignement.
     * @see AskingView#itemStateChanged(ItemEvent)
     */
    private JComboBox<String> teachingChoice;
    
    /**
     * Calendrier pour le choix de la date.
     * @see AskingView#propertyChange(PropertyChangeEvent)
     */
    private JCalendar calendar;
    
    /**
     * Liste des créneaux.
     * @see AskingView#valueChanged(ListSelectionEvent)
     */
    private JList<String> schedulesList;
    
    /**
     * Liste des caractéristiques selectionnées.
     */
    private JList<String> lSelectedFt;
    
    /**
     * Liste des caractéristiques non selectionnées.
     */
    private JList<String> lUnselectedFt;
    
    /**
     * Zone de texte pour entrer les commentaires.
     */
    private JTextArea taComments;
    
    /**
     * Libellé affichant le nombre de salles libres pour les paramètres selectionnés.
     */
    private JLabel lblNbRooms;

    /**
     * Constructeur de la fenêtre.
     * @param account
     * 			Façade contenant les données de l'utilisateur et permettant le dialogue avec la BL.
     */
    AskingView(TeacherFacade account) 
    {
            super("Demande de réservation");
            this.account = account;
            
            // Construction de la fenêtre
            setResizable(false);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            this.setSize(new Dimension(460, 560));
            
            // Panneau de choix du type de réservation
            JPanel chooseType = new JPanel();
            chooseType.setBounds(0, 0, 452, 53);
            chooseType.setBackground(SystemColor.menu);
            getContentPane().add(chooseType);
            chooseType.setLayout(null);
            
            JRadioButton rdbtnTeaching = new JRadioButton("Enseignement");
            rdbtnTeaching.setActionCommand("teach");
            rdbtnTeaching.addActionListener(this);
            rdbtnTeaching.setBounds(97, 0, 118, 23);
            buttonGroup.add(rdbtnTeaching);
            chooseType.add(rdbtnTeaching);
            
            JRadioButton rdbtnMeeting = new JRadioButton("R\u00E9union");
            rdbtnMeeting.setActionCommand("meet");
            rdbtnMeeting.addActionListener(this);
            rdbtnMeeting.setBounds(261, 0, 94, 23);
            buttonGroup.add(rdbtnMeeting);
            chooseType.add(rdbtnMeeting);
            
            teachingChoice = new JComboBox<String>();
            teachingChoice.setEnabled(false);
            teachingChoice.addItemListener(this);
            teachingChoice.setBounds(97, 28, 258, 23);
            chooseType.add(teachingChoice);
            
            // Panneau de la date et du créneau
            JPanel timeAndDate = new JPanel();
            timeAndDate.setBounds(0, 53, 452, 164);
            getContentPane().add(timeAndDate);
            timeAndDate.setLayout(null);
            
            calendar = new JCalendar();
            calendar.addPropertyChangeListener(this);
            calendar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            calendar.setBounds(10, 6, 205, 153);
            timeAndDate.add(calendar);
            
            JLabel lblSelectSchedule = new JLabel("Selection du cr\u00E9neau :");
            lblSelectSchedule.setBounds(246, 6, 146, 14);
            timeAndDate.add(lblSelectSchedule);
            
            schedulesList = new JList<String>();
            schedulesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            DefaultListModel<String> dlms = new DefaultListModel<String>();
            ArrayList<String> als = this.account.getSchedules();
            for (int i=0; i<als.size(); i++)
            {
                    dlms.addElement(als.get(i));
            }
            this.schedulesList.setModel(dlms);
            schedulesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            schedulesList.setBounds(246, 19, 196, 140);
            this.schedulesList.addListSelectionListener(this);
            timeAndDate.add(schedulesList);
            
            // Panneau des caractéristiques
            JPanel features = new JPanel();
            features.setBounds(0, 222, 442, 174);
            features.setBorder(new TitledBorder(null, "Caract\u00E9ristiques", TitledBorder.LEFT, TitledBorder.TOP, null, null));
            features.setBackground(SystemColor.menu);
            getContentPane().add(features);
            features.setLayout(null);
            
            JButton bAddFeature = new JButton(">");
            bAddFeature.setActionCommand("add");
            bAddFeature.addActionListener(this);
            bAddFeature.setBounds(200, 35, 41, 23);
            features.add(bAddFeature);
            
            JButton bDelFeature = new JButton("<");
            bDelFeature.setActionCommand("del");
            bDelFeature.addActionListener(this);
            bDelFeature.setBounds(200, 101, 41, 23);
            features.add(bDelFeature);
            
            JLabel lblCapacity = new JLabel("Capacit\u00E9 :");
            lblCapacity.setBounds(10, 150, 110, 14);
            features.add(lblCapacity);
            
            tfCapacity = new JTextField();
            tfCapacity.setText("0");
            tfCapacity.setBounds(71, 147, 86, 20);
            features.add(tfCapacity);
            tfCapacity.setColumns(10);
            
            lSelectedFt = new JList<String>();
            lSelectedFt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.lSelectedFt.setModel(new DefaultListModel<String>());
            lSelectedFt.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            lSelectedFt.setBounds(252, 21, 180, 118);
            features.add(lSelectedFt);
            
            lUnselectedFt = new JList<String>();
            lUnselectedFt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            DefaultListModel<String> dlm = new DefaultListModel<String>();
            ArrayList<String> fts = this.account.getFeatures(); 
            for (int i=0; i<fts.size(); i++)
            {
            	dlm.addElement(fts.get(i));
            }
            this.lUnselectedFt.setModel(dlm);
            lUnselectedFt.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            lUnselectedFt.setBounds(10, 21, 180, 118);
            features.add(lUnselectedFt);
            
            // Panneau des commentaires
            JPanel commentsPanel = new JPanel();
            commentsPanel.setBounds(0, 401, 452, 37);
            getContentPane().add(commentsPanel);
            commentsPanel.setLayout(null);
            
            JLabel lblComments = new JLabel("Commentaires :");
            lblComments.setBounds(10, 5, 147, 14);
            commentsPanel.add(lblComments);
            
            taComments = new JTextArea();
            taComments.setBounds(116, 0, 326, 37);
            commentsPanel.add(taComments);
            
            // Panneau du nombre de salles libres
            JPanel roomNb = new JPanel();
            roomNb.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            roomNb.setBounds(55, 447, 344, 37);
            roomNb.setBackground(SystemColor.menu);
            getContentPane().add(roomNb);
            roomNb.setLayout(null);
            
            lblNbRooms = new JLabel("Nombre de salles disponibles estim\u00E9 : ? ");
            lblNbRooms.setBounds(10, 11, 291, 14);
            roomNb.add(lblNbRooms);
            
            JButton bCheck = new JButton("V\u00E9rifier");
            bCheck.setActionCommand("ck");
            bCheck.addActionListener(this);
            bCheck.setBounds(246, 8, 88, 20);
            roomNb.add(bCheck);
            
            // Panneau des boutons de validation et annulation
            JPanel finish = new JPanel();
            finish.setBounds(125, 487, 203, 34);
            getContentPane().add(finish);
            finish.setLayout(null);
            
            JButton bCancel = new JButton("Annuler");
            bCancel.setActionCommand("cancel");
            bCancel.addActionListener(this);
            bCancel.setBounds(107, 5, 86, 23);
            finish.add(bCancel);
            
            JButton bValid = new JButton("Valider");
            bValid.setActionCommand("valid");
            bValid.addActionListener(this);
            bValid.setBounds(10, 5, 87, 23);
            finish.add(bValid);
            
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setVisible(true);
    }

	/**
	 * écoute les événements provenant d'un clic sur les bouton  - méthode issue de l'interface ActionListener
	 * @param e
	 * 			évenement provenant d'un clic sur un bouton
	 */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
            if(e.getActionCommand().equals("teach"))
            {
                    ArrayList<String> ens = this.account.getTeachings();
                    this.teachingChoice.removeAllItems();
                    for(int i=0; i < ens.size(); i++)
                    {
                        this.teachingChoice.addItem(ens.get(i));
                    }
                    
                    this.teachingChoice.setEnabled(true);
            }
            else if(e.getActionCommand().equals("meet"))
            {
                    this.teachingChoice.setEnabled(false);
                    this.teachingChoice.removeAllItems();
                    this.teachingSelected = "reunion";
            }
            else if(e.getActionCommand().equals("add"))
            {
                    if(!this.lUnselectedFt.isSelectionEmpty())
                    {
                            DefaultListModel<String> dlm = (DefaultListModel<String>)this.lSelectedFt.getModel();
                            dlm.addElement(this.lUnselectedFt.getSelectedValue());
                            this.lSelectedFt.setModel(dlm);
                            
                            Enumeration<String> es = (Enumeration<String>) ((DefaultListModel<String>)this.lSelectedFt.getModel()).elements();
                            while (es.hasMoreElements())
                            {
                            	this.featuresSelected.add(es.nextElement());
                            }
                            
                            
                            
                            DefaultListModel<String> dlm2 = (DefaultListModel<String>)this.lUnselectedFt.getModel();
                            dlm2.removeElementAt(this.lUnselectedFt.getSelectedIndex());
                            this.lUnselectedFt.setModel(dlm2);
                    }
            }
            else if(e.getActionCommand().equals("del"))
            {
                    if(!this.lSelectedFt.isSelectionEmpty())
                    {
                            DefaultListModel<String> dlm = (DefaultListModel<String>)this.lUnselectedFt.getModel();
                            dlm.addElement(this.lSelectedFt.getSelectedValue());
                            this.lUnselectedFt.setModel(dlm);
                            
                            DefaultListModel<String> dlm2 = (DefaultListModel<String>)this.lSelectedFt.getModel();
                            dlm2.removeElementAt(this.lSelectedFt.getSelectedIndex());
                            this.lSelectedFt.setModel(dlm2);
                            Enumeration<String> es = (Enumeration<String>) dlm2.elements();
                            this.featuresSelected.clear();
                            while (es.hasMoreElements())
                            {
                            	this.featuresSelected.add(es.nextElement());
                            }
                    }
            }
            else if(e.getActionCommand().equals("ck"))
            {
                    if(this.dateSelected != null && this.scheduleSelected != null)
                    {
	                    	try
	                    	{
	                    		 this.capacity = 0 + Integer.parseInt(this.tfCapacity.getText());
	                    		 this.lblNbRooms.setText("Nombre de salles disponibles estim\u00E9 : "+this.account.checkFreeRooms(this.dateSelected, this.scheduleSelected, this.featuresSelected, this.capacity));
	                    	}
	                    	catch(NumberFormatException ex)
	                    	{
	                    		JOptionPane.showMessageDialog(this, "Veuillez rentrer un entier pour la capacité de la salle", "Erreur", JOptionPane.ERROR_MESSAGE);
	                    	}
	                    	catch (Exception e1) 
	                    	{
								JOptionPane.showMessageDialog(this, "Problème d'accès à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
                    }
            }
            else if(e.getActionCommand().equals("cancel"))
            {
                    dispose();
            }
            else if(e.getActionCommand().equals("valid"))
            {
                    if(this.teachingSelected != null)
                    {
                            if(this.dateSelected != null && this.scheduleSelected != null)
                            {
                            		try
                            		{
                            			this.capacity = 0+Integer.parseInt(this.tfCapacity.getText());
                            			this.comments = this.taComments.getText();
                            			this.account.confirmBooking(teachingSelected, dateSelected, scheduleSelected, featuresSelected, capacity, comments);
                            			dispose();
                            		}
                                    catch(NumberFormatException ex)
                                    {
                                    	JOptionPane.showMessageDialog(this, "Veuillez rentrer un entier pour la capacité de la salle", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }
									catch (Exception e1) 
									{
										JOptionPane.showMessageDialog(this, "Problème d'accès à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
									}
                                    
                            }
                    }
            }
    }

	/**
	 * écoute les événements provenant d'une selection d'un item dans la liste déroulante des enseignements.
	 * @param i
	 */
    @Override
    public void itemStateChanged(ItemEvent i) 
    {
            this.teachingSelected = (String) i.getItem();           
    }

    /**
     * écoute les événements de sélection d'un item dans la liste des créneaux 
     * @param arg0
     */
    @Override
    public void valueChanged(ListSelectionEvent arg0) 
    {
            this.scheduleSelected = (String) this.schedulesList.getSelectedValue();
    }

    /**
     * écoute les événements déclenchés lors de la sélection d'une date.
     * @param arg0
     */
    @Override
    public void propertyChange(PropertyChangeEvent arg0) 
    {
            this.dateSelected = this.calendar.getDate();
    }
}
