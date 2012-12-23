package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import BL.TeacherFacade;

/**
 * Classe permettant de g�n�rer l'interface graphique pour le menu utilisateur.
 * @author URM Team
 */
@SuppressWarnings({ "unused", "serial" })
class MenuView extends JFrame implements ActionListener
{
	/**
	 * Vue graphique de la consultation de l'emploi du temps.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private ConsulterView consulting;
	
	/**
	 * Vue graphique de la demande de r�servation.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private AskingView Asking;
	
	/**
	 * Vue graphique du traitement des r�servations.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private HandlingView Handling;
	
	/**
	 * Bouton qui d�clenche le lancement de la vue de consultation.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private JButton consult;
	
	/**
	 * Bouton qui d�clenche le lancement de la vue de demande de r�servation.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private JButton ask;
	
	/**
	 * Bouton qui d�clenche le lancement de la vue de traitement des r�servations.
	 * @see MenuView#actionPerformed(ActionEvent)
	 */
	private JButton handle;
	
	/**		
	* Bouton qui ferme l'application.		
	* @see MenuView#actionPerformed(ActionEvent)		
	*/		
	private JButton quit;
	
	/**
	 * Fa�ade permettant le dialogue avec la BL.
	 */
	private TeacherFacade user;
	
	 /**
     * Constructeur
     * @param c
     * 			Fait r�f�rence au compte d'un enseignant.
     */
	public  MenuView(TeacherFacade c) 
	{
		this.user = c;
		initComponents();
	}
	
	/**
	 * Initialise les widgets qui composent la fen�tre.
	 */
	private void initComponents() 
	{

		//initialisation de la fen�tre et des widgets
    	this.setTitle("URM");
    	this.setSize(300,140); //On ajuste la taille de la fen�tre
    	this.setResizable(false);
    	
    	// liste des boutons
    	consult = new JButton("Consulter planning"); consult.setPreferredSize(new Dimension (250,30)); consult.setActionCommand("consult"); consult.addActionListener(this);
    	ask = new JButton("Demande de r�servation"); 
    	ask.setPreferredSize(new Dimension (250,30)); 
    	ask.setActionCommand("ask"); 
    	ask.addActionListener(this);
    	
    	quit = new JButton("Quitter");		
    	quit.setPreferredSize(new Dimension (250,30));		
    	quit.setActionCommand("quit");		
    	quit.addActionListener(this);		
    	
    	// Initialisation du panneau
    	JPanel p = new JPanel (new FlowLayout());
    	
    	p.add(consult);
    	p.add(ask);
    	if(this.user.isSuperUser())
    	{
    		this.setSize(300,180); //On donne une taille � notre fen�tre
			handle = new JButton("Traitement des demandes"); handle.setPreferredSize(new Dimension (250,30)); handle.setActionCommand("handle"); handle.addActionListener(this);
        	p.add(handle);
    	}
    	p.add(quit);
    	this.add(p);
    	
    	this.setLocationRelativeTo(null);     
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	
	}

	 /**
	 * �coute les �v�nements provenant d'un clic sur bouton - m�thode issue de l'interface ActionListener
     * @param e
     * 			�venement provenant d'un clic sur un des bouton
     * @see MenuView#consult
     * @see MenuView#ask
     * @see MenuView#handle
     */
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("consult"))
		{
			ConsulterView cv = new ConsulterView(this.user);
		}
		else if (e.getActionCommand().equals("ask"))
		{
			AskingView av = new AskingView(this.user);
		}
		else if (e.getActionCommand().equals("handle"))
		{
			JOptionPane.showMessageDialog(this, "Le traitement des demandes n'est pas encore disponible.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("quit"))		
		{		
			System.exit(0);		
		}
	}
	
}
