package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.Client;
import server.remote.IRemote;
import server.remote.Remote;


public class LogIn {
	
	final static  Logger logger = LoggerFactory.getLogger(Client.class);
	
	private ShowBooks showbooks;
	private ShowBooksAdmin showbooksAdmin;
	private JFrame frame;
	private JPanel logIn;
	private JTextField email;
	private JPasswordField password;
	private JTextField emailSignUp;
	private JTextField passwordSignUp;
	private JTextField confirmPass;
	private JTextField fullName;
	private JButton btnLogIn;
	private JLabel loginMesage;
	private JLabel signUpMesage;
	private JLabel imageLogo;
	private JButton btnNewUser;
	
	private String userEmail;
	private boolean role = false;   //true --> admin
									//false -->  user
	IRemote server;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn logIn = new LogIn();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public LogIn() {
		
		// Create and set up the window.
		frame = new JFrame("Book Shop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// Display the window.
		frame.setBounds(0, 0, 991, 661);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);
		 try {
			server = new Remote();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// Initialize the contents of the frame.
		initializeLogIn();
	}
	
	/**
	 * Initialize the contents of the Log In JPanel 
	 */
	private void initializeLogIn(){

		// Beginning of Log In JPanel~window 
		logIn = new JPanel();
		logIn.setBackground(SystemColor.window);
		logIn.setLayout(new GridBagLayout());
		frame.getContentPane().add(logIn, BorderLayout.CENTER);

		// JLabel for Logo Image
		imageLogo = new JLabel("BOOK STORE");
		imageLogo.setBackground(SystemColor.window);
		GridBagConstraints gbc_imageLogo = new GridBagConstraints();
		gbc_imageLogo.insets = new Insets(0, 0, 2, 2);
		gbc_imageLogo.gridx = 0;
		gbc_imageLogo.gridy = 0;
		gbc_imageLogo.fill= GridBagConstraints.HORIZONTAL;
		imageLogo.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		//Image imgBack = new ImageIcon(this.getClass().getResource("booklogo.jpg")).getImage();
		//System.out.println(getClass().getResource("booklogo.jpg"));
		//imageLogo.setIcon( (Icon) new ImageIcon(imgBack));
		logIn.add(imageLogo, gbc_imageLogo);
		
		// JLabel component about login message
		loginMesage = new JLabel("Log In");
		loginMesage.setForeground(new Color(0, 0, 0));
		loginMesage.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_loginMesage = new GridBagConstraints();
		gbc_loginMesage.anchor = GridBagConstraints.SOUTHWEST;
		gbc_loginMesage.insets = new Insets(0, 0, 5, 10);
		gbc_loginMesage.gridx = 2;
		gbc_loginMesage.gridy = 1;
		logIn.add(loginMesage, gbc_loginMesage);

		// JTextField component about email
		email = new JTextField("Email Address");

		// mouse event listeners about filling the email component
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String emailText = email.getText();
				if (emailText.equals("Email Address")) email.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String emailText = email.getText();
				if (emailText.equals("")) email.setText("Email Address");
			}
		});

		email.setForeground(SystemColor.textInactiveText);
		email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.insets = new Insets(0, 0, 3, 10);
		gbc_Email.fill = GridBagConstraints.BOTH;
		gbc_Email.gridx = 2;
		gbc_Email.gridy = 3;
		email.setColumns(12);
		logIn.add(email, gbc_Email);

		// JTextField component about password
		password = new JPasswordField();
		password.setEchoChar('*');
		
		// mouse event listeners about filling the password component
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String passwordText = String.valueOf(password.getPassword());
				if (passwordText.equals("Password")) password.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String passwordText = String.valueOf(password.getPassword());
				if (passwordText.equals("")) password.setText("Password");
			}
		});

		password.setForeground(SystemColor.textInactiveText);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_Password = new GridBagConstraints();
		gbc_Password.insets = new Insets(0, 0, 3, 10);
		gbc_Password.fill = GridBagConstraints.BOTH;
		gbc_Password.gridx = 2;
		gbc_Password.gridy = 4;
		password.setColumns(12);
		logIn.add(password, gbc_Password);
		
		// JButton component about Log In
		btnLogIn = new JButton("Log in");
		btnLogIn.setBackground(new Color(95, 158, 160));
		frame.getRootPane().setDefaultButton(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String accessEmail = email.getText();
				String accessPassword = String.valueOf(password.getPassword());
				try {
					role = server.getUser(accessEmail).getRole();
					server.registerUser(accessEmail, accessPassword, role);
					userEmail = accessEmail;
				} catch (RemoteException e) {
					logger.info(e.getMessage());
				}
				//Admin and user go to the same window
				if(role == false){
					showbooks = new ShowBooks(userEmail);
					frame.setVisible(false);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}else{
					showbooksAdmin = new ShowBooksAdmin(userEmail);
					frame.setVisible(false);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}
				
			}
		});
		btnLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		// setting an image as an icon for Log In Button
		
		//btnLogIn.setIcon( (Icon) new ImageIcon(LogIn.class.getResource("login.png")));
		//System.out.println(getClass().getResource("login.png"));
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.insets = new Insets(0, 0, 2, 10);
		gbc_btnLogIn.gridx = 2;
		gbc_btnLogIn.gridy = 5;
		gbc_btnLogIn.fill = GridBagConstraints.BOTH;
		logIn.add(btnLogIn, gbc_btnLogIn);
		

		// JLabel component about sign up message
		signUpMesage = new JLabel("Sign Up");
		signUpMesage.setBackground(new Color(169, 169, 169));
		signUpMesage.setForeground(new Color(0, 0, 0));
		signUpMesage.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_signUpMesage = new GridBagConstraints();
		gbc_signUpMesage.anchor = GridBagConstraints.SOUTHWEST;
		gbc_signUpMesage.insets = new Insets(0, 0, 5, 10);
		gbc_signUpMesage.gridx = 1;
		gbc_signUpMesage.gridy = 1;
		gbc_signUpMesage.fill = GridBagConstraints.BOTH;
		logIn.add(signUpMesage, gbc_signUpMesage);

		// JTextField component about full name
		fullName = new JTextField("Full Name");

		// mouse event listeners about filling the full name component
		fullName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String fullNameText = fullName.getText();
				if (fullNameText.equals("Full Name")) fullName.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String fullNameText = fullName.getText();
				if (fullNameText.equals("")) fullName.setText("Full Name");
			}
		});

		fullName.setForeground(SystemColor.textInactiveText);
		fullName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_fullName = new GridBagConstraints();
		gbc_fullName.insets = new Insets(0, 0, 3, 10);
		gbc_fullName.fill = GridBagConstraints.BOTH;
		gbc_fullName.gridx = 1;
		gbc_fullName.gridy = 2;
		fullName.setColumns(12);
		logIn.add(fullName, gbc_fullName);

		// JTextField component about email
		emailSignUp = new JTextField("Email Address");

		// mouse event listeners about filling the email component
		emailSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String emailTxtSign = emailSignUp.getText();
				if (emailTxtSign.equals("Email Address")) emailSignUp.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String emailTxtSign = emailSignUp.getText();
				if (emailTxtSign.equals("")) emailSignUp.setText("Email Address");
			}
		});

		emailSignUp.setForeground(SystemColor.textInactiveText);
		emailSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_EmailSignUp = new GridBagConstraints();
		gbc_EmailSignUp.insets = new Insets(0, 0, 3, 10);
		gbc_EmailSignUp.fill = GridBagConstraints.BOTH;
		gbc_EmailSignUp.gridx = 1;
		gbc_EmailSignUp.gridy = 3;
		email.setColumns(12);
		logIn.add(emailSignUp, gbc_EmailSignUp);

		// JTextField component about password
		passwordSignUp = new JTextField("Password");
		
		// mouse event listeners about filling the password component
		passwordSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String passwordTxtSign = passwordSignUp.getText();
				if (passwordTxtSign.equals("Password")) passwordSignUp.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String passwordTxtSign = passwordSignUp.getText();
				if (passwordTxtSign.equals("")) passwordSignUp.setText("Password");
			}
		});

		passwordSignUp.setForeground(SystemColor.textInactiveText);
		passwordSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_passwordSignUp = new GridBagConstraints();
		gbc_passwordSignUp.insets = new Insets(0, 0, 3, 10);
		gbc_passwordSignUp.fill = GridBagConstraints.BOTH;
		gbc_passwordSignUp.gridx = 1;
		gbc_passwordSignUp.gridy = 4;
		passwordSignUp.setColumns(12);
		logIn.add(passwordSignUp, gbc_passwordSignUp);
		
		// JTextField component about confirming password
		confirmPass = new JTextField("Confirm Password");
		
		// mouse event listeners about filling the password component
		confirmPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				String confirmPassText = confirmPass.getText();
				if (confirmPassText.equals("Confirm Password")) confirmPass.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String confirmPassText = confirmPass.getText();
				if (confirmPassText.equals("")) confirmPass.setText("Confirm Password");
			}
		});

		confirmPass.setForeground(SystemColor.textInactiveText);
		confirmPass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GridBagConstraints gbc_confirmPass = new GridBagConstraints();
		gbc_confirmPass.insets = new Insets(0, 0, 3, 10);
		gbc_confirmPass.fill = GridBagConstraints.BOTH;
		gbc_confirmPass.gridx = 1;
		gbc_confirmPass.gridy = 5;
		confirmPass.setColumns(12);
		logIn.add(confirmPass, gbc_confirmPass);
		

		btnNewUser = new JButton("Sign Me Up");
		btnNewUser.setBackground(new Color(95, 158, 160));
		btnNewUser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passtxt = passwordSignUp.getText();
				String confpasstxt = confirmPass.getText();
				if (passtxt.equals(confpasstxt) && !emailSignUp.equals("Email Address") && !fullName.equals("Full Name")){
					try {
						role = false;
						server.registerUser(emailSignUp.getText(), passtxt, role);
					} catch (RemoteException e) {
						logger.info(e.getMessage());
					}
					//User go to the same window
					showbooks = new ShowBooks(userEmail);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry unable to register, try again!", "InfoBox: " + "Log In", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnNewUser = new GridBagConstraints();
		gbc_btnNewUser.insets = new Insets(0, 0, 2, 10);
		gbc_btnNewUser.gridx = 1;
		gbc_btnNewUser.gridy = 6;
		gbc_btnNewUser.fill = GridBagConstraints.BOTH;
		logIn.add(btnNewUser, gbc_btnNewUser);	
		
		logIn.revalidate();
		logIn.repaint();
	}
	

}