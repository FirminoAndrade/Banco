package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ConexaoBD.ModConexao;

public class TelaPrincipal extends JFrame {
	TelaCadastro tc = new TelaCadastro();
	

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtResultado;
	private JButton btnApagar;
	private JButton btnSacar;
	private JButton btnDepositar;
	private double valor;
	private JLabel lblNome;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnSair;
	private JLabel lblNewLabel_6;
	private JTextField txtJanelaSaldo;
	public static JLabel lblNomeUsu;
	public static JLabel lblNumConta;
	public static JLabel lblTelefone;


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {

		conexao = ModConexao.conector();
		setResizable(false);

		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Banco Java1");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtResultado = new JTextField();
		txtResultado.setFont(new Font("Tahoma", Font.BOLD, 50));
		txtResultado.setColumns(10);

		btnApagar = new JButton("");
		btnApagar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/voltar.png")));
		btnApagar.setBackground(new Color(255, 51, 0));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtResultado.setText("");
			}
		});

		btnSacar = new JButton("Sacar");
		btnSacar.setBackground(new Color(112, 128, 144));
		btnSacar.setForeground(new Color(0, 255, 0));
		btnSacar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/saqueP.png")));
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            
				// transforma a string em double
				valor = Double.parseDouble(txtResultado.getText());

				if (txtResultado.getText() == null) {
					JOptionPane.showMessageDialog(null, "Digite Algum Valor", "Atenção", JOptionPane.ERROR_MESSAGE);
				}
				if (valor <= tc.getSaldo()) {
					tc.setSaldo(tc.getSaldo() - valor);
					txtJanelaSaldo.setText(String.valueOf(tc.getSaldo()));
					// transforma o double em String
					txtJanelaSaldo.setText(String.valueOf(tc.getSaldo()));
					JOptionPane.showMessageDialog(null, "Saque Efetuado com Sucesso!", "Saldo",
							JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "$ " + tc.getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Saldo Insufuciente para este Saque!", "Atenção",
							JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "$ " + tc.getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});

		btnDepositar = new JButton("Depositar");
		btnDepositar.setForeground(new Color(255, 0, 0));
		btnDepositar.setBackground(new Color(60, 179, 113));
		btnDepositar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/depositoP.png")));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// transforma a string em double
				valor = Double.parseDouble(txtResultado.getText());
				if (valor > 0) {
					tc.setSaldo(tc.getSaldo() + valor);
					// transforma o double em String
					txtJanelaSaldo.setText(String.valueOf(tc.getSaldo()));
					JOptionPane.showMessageDialog(null, "Depósito Efetuado com Sucesso!", "Saldo",
							JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "$ " + tc.getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Digite um Valor", "Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtJanelaSaldo = new JTextField();
		txtJanelaSaldo.setEditable(false);
		txtJanelaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtJanelaSaldo.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/saldo.png")));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/moeda.png")));

		JLabel lblNewLabel_2 = new JLabel("Banco Java1");
		lblNewLabel_2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 45));

		lblNome = new JLabel();
		lblNome.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 35));
		lblNome.setEnabled(false);

		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
		JLabel lblData = new JLabel();
		lblData.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		lblData.setEnabled(false);
		lblData.setText(formatador.format(data));

		lblNewLabel_3 = new JLabel("Cliente: ");
		lblNewLabel_3.setBackground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tw Cen MT", Font.BOLD, 20));

		lblNomeUsu = new JLabel("Nome Usuario");
		lblNomeUsu.setBackground(new Color(128, 0, 0));
		lblNomeUsu.setEnabled(false);
		lblNomeUsu.setForeground(new Color(0, 0, 204));
		lblNomeUsu.setFont(new Font("Stencil", Font.BOLD, 20));

		JButton btnRenderJ = new JButton("Render Juros");
		btnRenderJ.setForeground(new Color(0, 100, 0));
		btnRenderJ.setBackground(new Color(255, 215, 0));
		btnRenderJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double taxaJuros = 1.050;
				valor = Double.parseDouble(txtResultado.getText());
				tc.setSaldo(tc.getSaldo() * taxaJuros);
				txtJanelaSaldo.setText(String.valueOf(tc.getSaldo()));
			}
		});
		btnRenderJ.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/renderJurosM.png")));

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/moed.png")));

		btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/sair.png")));

		JLabel lblNewLabel_5 = new JLabel("Conta:");
		lblNewLabel_5.setBackground(new Color(102, 0, 0));
		lblNewLabel_5.setFont(new Font("Tw Cen MT", Font.BOLD, 20));

		lblNumConta = new JLabel("Numero da conta");
		lblNumConta.setEnabled(false);
		lblNumConta.setFont(new Font("Stencil", Font.BOLD, 20));

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/calendario.png")));

		JLabel lblNewLabel_7 = new JLabel("Fone:");
		lblNewLabel_7.setBackground(new Color(102, 0, 0));
		lblNewLabel_7.setFont(new Font("Tw Cen MT", Font.BOLD, 20));

		lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setEnabled(false);
		lblTelefone.setFont(new Font("Stencil", Font.BOLD, 20));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(50).addComponent(lblNewLabel_4).addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(
										btnApagar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtResultado, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnSacar, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnDepositar, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRenderJ,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 74,
												GroupLayout.PREFERRED_SIZE))
										.addGap(379))))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5).addComponent(lblNewLabel_7))
						.addGap(46)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNumConta)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel_6).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_2).addGap(54)
										.addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtJanelaSaldo, GroupLayout.PREFERRED_SIZE, 164,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNomeUsu, GroupLayout.PREFERRED_SIZE, 379,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNome,
												GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTelefone))
						.addContainerGap(80, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
								.addComponent(lblNomeUsu, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
								.addComponent(lblNumConta, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2).addComponent(lblNewLabel)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(12)
												.addComponent(txtJanelaSaldo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 49,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_6).addComponent(lblNome,
														GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))))
				.addGap(18)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_7).addComponent(lblTelefone))
				.addGap(92)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(txtResultado, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRenderJ, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDepositar).addComponent(btnSacar))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
						.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE))
				.addGap(264)));
		contentPane.setLayout(gl_contentPane);
	}
}
