package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ConexaoBD.ModConexao;

public class TelaLo extends JFrame {

	private static final long serialVersionUID = 1L;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@SuppressWarnings("deprecation")
	public void logar() {
		String sql = "select * from tbusuario where login=? and senha=?";
		try {
			// as linhas abaixo consulta ao banco de dados em funcao do foi digitado nas
			// caixas de texto
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtSenha.getText());
			// a linha abaixo executa a query
			rs = pst.executeQuery();
			// se existir usuario e senha correspondente
			if (rs.next()) {
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				TelaPrincipal.lblNomeUsu.setText(rs.getString(2));
				TelaPrincipal.lblNumConta.setText(rs.getString(1));
				TelaPrincipal.lblTelefone.setText(rs.getString(3));

				// faz com que a tela login e o banco de dados feche apos abrir a proxima
				this.dispose();
				conexao.close();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha Invalido(s)");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLo frame = new TelaLo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLo() {

		setForeground(Color.DARK_GRAY);
		setFont(new Font("Arial Black", Font.PLAIN, 15));
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Bem Vindo ao Banco JAVA 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");

		txtSenha = new JPasswordField();

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamando o metodo logar
				logar();
			}
		});

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLo.class.getResource("/icones/dberror.png")));

		JButton btnCadastre = new JButton("Cadastre-se");
		btnCadastre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				dispose();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaLo.class.getResource("/icones/bancoP.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addContainerGap().addComponent(lblStatus).addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addGap(1)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel, Alignment.LEADING)
										.addComponent(txtUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 232,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, Alignment.LEADING)
										.addComponent(txtSenha, Alignment.LEADING)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCadastre)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE).addComponent(
												btnLogin, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(180).addComponent(lblNewLabel_2)))
				.addContainerGap(112, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(lblNewLabel_2).addGap(24).addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStatus)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnCadastre).addComponent(btnLogin)))
										.addGap(17)));
		contentPane.setLayout(gl_contentPane);
		conexao = ModConexao.conector();
		// a linha abaixo serve de apoio a status de conexao
		// System.out.println(conexao);
		if (conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));
			;
		} else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			;
		}
	}
}