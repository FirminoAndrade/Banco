package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ConexaoBD.ModConexao;
import javax.swing.ImageIcon;

public class TelaCadastro extends JFrame {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField txtIduser;
	public JTextField txtUsuario;
	public JTextField txtFone;
	public JTextField txtLogin;
	public JTextField txtSenha;
	private JLabel lblNewLabel_5;
	private double saldo;

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void adicionar() {

		String sql = "insert into tbusuario(iduser,usuario,fone,login,senha,valor) values(?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtIduser.getText());
			pst.setString(2, txtUsuario.getText());
			pst.setString(3, txtFone.getText());
			pst.setString(4, txtLogin.getText());
			pst.setString(5, txtSenha.getText());
			pst.setDouble(6, this.saldo);
			int adcionado = pst.executeUpdate();
			if (adcionado > 0) {
				JOptionPane.showMessageDialog(null, "Cadastro Efetuado Com Sucesso!");
				dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dados Inválidos!");
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		conexao = ModConexao.conector();

		setFont(new Font("Dialog", Font.BOLD, 17));
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Nome");

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fone");

		txtFone = new JTextField();
		txtFone.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");

		txtLogin = new JTextField();
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");

		txtSenha = new JTextField();
		txtSenha.setColumns(10);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});

		txtIduser = new JTextField();
		txtIduser.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Conta");

		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icones/livro.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtSenha, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 110,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, Alignment.LEADING)
								.addComponent(lblNewLabel_2, Alignment.LEADING)
								.addComponent(lblNewLabel_3, Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																.addComponent(txtLogin, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
																.addComponent(txtFone, GroupLayout.DEFAULT_SIZE, 242,
																		Short.MAX_VALUE))
														.addGap(112))
												.addGroup(gl_contentPane
														.createSequentialGroup().addComponent(lblNewLabel_1)
														.addPreferredGap(ComponentPlacement.RELATED)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtIduser, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_4))
										.addGap(72))
								.addComponent(txtUsuario, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 462,
										GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
						.addGroup(Alignment.TRAILING,
								gl_contentPane.createSequentialGroup().addComponent(btnNewButton).addGap(67)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE).addGap(7)
				.addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(
						txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIduser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_2)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_3)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE).addComponent(btnNewButton)
				.addGap(47)));
		contentPane.setLayout(gl_contentPane);
	}
}
