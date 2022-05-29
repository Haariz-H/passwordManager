package passwordManager;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPass;
	Connection con;
	Statement stmt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void sqlMet() {
		
		try {
		stmt=con.createStatement();
		String sql = "Select * from users where username='"+txtUser.getText()+"' and password='"+txtPass.getText().toString()+"'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
//			JOptionPane.showMessageDialog(null, "LogIn Sucessfully");
			frame.setVisible(false);
			mainWindow mw= new mainWindow();
			mw.setVisible(true);
			
		}
			
		else
			JOptionPane.showMessageDialog(null, "LogIn failed");
		finished();
		
	
		
	} catch (Exception e2) {
		// TODO: handle exception
		System.out.println(e2);
		
	}
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize()  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/passMan","root","rootroot");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 273);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 47, 94, 38);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 117, 94, 38);
		panel.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(164, 57, 174, 28);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(164, 127, 174, 28);
		panel.add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlMet();
				
			}
		});
		btnLogin.setBounds(262, 182, 117, 25);
		panel.add(btnLogin);
	}
	void finished() throws SQLException {
		stmt.close();
		
		con.close();
	}
}

