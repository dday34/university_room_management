package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import BL.TeacherFacade;

/**
 * Permet de générer l'interface graphique pour l'authentification.
 * @author URM Team
 */
@SuppressWarnings("serial")
public class LoginView extends JFrame implements ActionListener
{
	/**
	 * Façade permettant le dialogue avec la BL
	 * @see LoginView#actionPerformed(ActionEvent)
	 */
	private TeacherFacade account;
	
	/**
	 * Menu généré si la personne a été identifiée
	 * @see LoginView#actionPerformed(ActionEvent)
	 */
	@SuppressWarnings("unused")
	private MenuView menu;
	
	/**
	 * Boîte d'éddition du login.
	 * @see LoginView#LoginView()
	 */
	private JTextField tfLogin;
	
	/**
	 * Boîte d'éddition du mot de passe.
	 * @see LoginView#LoginView()
	 */
	private JPasswordField tfMdp;
	
	/**
	 * Bouton qui permet de valider l'entrée du login et du mot de passe.
	 * @see LoginView#LoginView()
	 */
	private JButton bValid;
	
	/**
	 * Bouton qui ferme le logiciel.
	 * @see LoginView#LoginView()
	 */
	private JButton bCancel;
	
	/**
	 * Constructeur de la fenêtre Login.
	 */
	public LoginView() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(299, 154);
		setResizable(false);
		setTitle("URM Authentification");
		getContentPane().setLayout(null);
		
		JPanel pIn = new JPanel();
		pIn.setBounds(53, 11, 186, 69);
		getContentPane().add(pIn);
		pIn.setLayout(null);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(91, 8, 86, 20);
		pIn.add(tfLogin);
		tfLogin.setColumns(10);
		tfLogin.setToolTipText("Entrez votre identifiant"); //indication à l'utilisateur
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(0, 42, 167, 14);
		pIn.add(lblMotDePasse);
		
		JLabel lblLogin = new JLabel("Login : ");
		lblLogin.setBounds(39, 11, 108, 14);
		pIn.add(lblLogin);
		
		tfMdp = new JPasswordField();
		tfMdp.setBounds(91, 39, 86, 20);
		tfMdp.addKeyListener(new KeyAdapter() 
        {
                public void keyPressed(KeyEvent e) 
                {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) 
                        {
                                getRootPane().setDefaultButton(bValid);
                        }
                }
        });
		tfMdp.setToolTipText("Entrez votre mot de passe"); //indication à l'utilisateur
		pIn.add(tfMdp);
		
		JPanel pOut = new JPanel();
		pOut.setBounds(38, 82, 217, 33);
		getContentPane().add(pOut);
		pOut.setLayout(null);
		
		bValid = new JButton("Valider");
		bValid.setActionCommand("valid");
		bValid.setBounds(10, 5, 86, 23);
		bValid.addActionListener(this);
		pOut.add(bValid);
		
		bCancel = new JButton("Annuler");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);
		bCancel.setBounds(121, 5, 86, 23);
		pOut.add(bCancel);
    	this.setLocationRelativeTo(null);     
		this.setVisible(true);
		
	}

	/**
	 * écoute les événements provenant d'un clic sur le bouton valider ou le bouton annuler - méthode issue de l'interface ActionListener
	 * @param e
	 * 			évenement provenant d'un clic sur un bouton
	 */
	public void actionPerformed(ActionEvent e) 
	{		
		if (e.getActionCommand().equals("valid")) //l'utilisateur clique sur valider
		{
			try
			{
				this.account = new TeacherFacade(this.tfLogin.getText(), new String(this.tfMdp.getPassword())); //on crée un nouvel objet compte identifiant un enseignant
				this.menu = new MenuView(account);
				this.setVisible(false);
			}
			catch (Exception sqle)
			{
				//System.out.println("L'utilisateur n'est pas connecté");
				JOptionPane.showMessageDialog(this, "Impossible de se connecter. Vérifiez vos identifiant et mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getActionCommand().equals("cancel")) 
		{
			System.exit(0);
		}
	}

	/**
	 * Lanceur du programme
	 * @param args the command line arguments
	 */
	public static void main(String args[]) 
	{
		new LoginView();
	}
}
