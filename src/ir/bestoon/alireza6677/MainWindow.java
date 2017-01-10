package ir.bestoon.alireza6677;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.PlainDocument;

import ir.bestoon.alireza6677.connection.Connection;
import ir.bestoon.alireza6677.utils.MyIntFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JPasswordField password_field;
	private JTextField expense_count;
	private JTextField expense_amount;
	private JTextField income_count;
	private JTextField income_amount;
	private JTabbedPane tabbedPane;
	private JButton login_btn;
	private JButton logout_btn;
	private JLabel status_label;
	private Connection con = new Connection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					// SyntheticaWhiteVisionLookAndFeel());
					// SwingUtilities.updateComponentTreeUI(this);
					// UIManager.setLookAndFeel(new NimbusLookAndFeel());
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setTitle("Bestoon Gui");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_stats = new JPanel();
		tabbedPane.addTab("وضعیت", null, panel_stats, null);

		JLabel label = new JLabel("خرج");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel label_1 = new JLabel("در آمد");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel label_2 = new JLabel("تعداد:");
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel label_3 = new JLabel("جمع کل:");
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel label_4 = new JLabel("تعداد:");
		label_4.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel label_5 = new JLabel("جمع کل:");
		label_5.setFont(new Font("Dialog", Font.BOLD, 16));

		expense_count = new JTextField();
		expense_count.setFont(new Font("Dialog", Font.PLAIN, 14));
		expense_count.setEditable(false);
		expense_count.setColumns(10);

		expense_amount = new JTextField();
		expense_amount.setFont(new Font("Dialog", Font.PLAIN, 14));
		expense_amount.setEditable(false);
		expense_amount.setColumns(10);

		income_count = new JTextField();
		income_count.setFont(new Font("Dialog", Font.PLAIN, 14));
		income_count.setEditable(false);
		income_count.setColumns(10);

		income_amount = new JTextField();
		income_amount.setFont(new Font("Dialog", Font.PLAIN, 14));
		income_amount.setEditable(false);
		income_amount.setColumns(10);

		JButton button = new JButton("تازه سازی");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadApp();
			}
		});
		GroupLayout gl_panel_stats = new GroupLayout(panel_stats);
		gl_panel_stats.setHorizontalGroup(gl_panel_stats.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_stats
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_stats.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addGroup(gl_panel_stats.createSequentialGroup()
								.addGroup(gl_panel_stats.createParallelGroup(Alignment.TRAILING)
										.addComponent(expense_count, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
										.addComponent(expense_amount, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel_stats.createParallelGroup(Alignment.LEADING)
										.addComponent(label_3, Alignment.TRAILING).addComponent(label_2,
												Alignment.TRAILING)))
						.addGroup(
								gl_panel_stats.createSequentialGroup()
										.addGroup(gl_panel_stats.createParallelGroup(Alignment.LEADING)
												.addComponent(income_count, GroupLayout.DEFAULT_SIZE, 252,
														Short.MAX_VALUE)
												.addComponent(income_amount, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_stats.createParallelGroup(Alignment.LEADING)
												.addComponent(label_5, Alignment.TRAILING)
												.addComponent(label_4, Alignment.TRAILING)))
						.addComponent(button, Alignment.TRAILING))
				.addContainerGap()));
		gl_panel_stats.setVerticalGroup(gl_panel_stats.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_stats
				.createSequentialGroup().addGap(23).addComponent(label).addGap(18)
				.addGroup(gl_panel_stats.createParallelGroup(Alignment.BASELINE).addComponent(label_2).addComponent(
						expense_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_stats.createParallelGroup(Alignment.BASELINE).addComponent(label_3).addComponent(
						expense_amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(67).addComponent(label_1).addGap(18)
				.addGroup(gl_panel_stats.createParallelGroup(Alignment.BASELINE).addComponent(label_4).addComponent(
						income_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_stats.createParallelGroup(Alignment.BASELINE).addComponent(label_5).addComponent(
						income_amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE).addComponent(button)
				.addContainerGap()));
		panel_stats.setLayout(gl_panel_stats);

		JPanel panel_expense = new JPanel();
		tabbedPane.addTab("خرج", null, panel_expense, null);

		JLabel lblNewLabel = new JLabel("ثبت خرج");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel label_6 = new JLabel("مبلغ:");
		label_6.setFont(new Font("Dialog", Font.BOLD, 16));

		// NumberFormat format = NumberFormat.getInstance();
		// NumberFormatter formatter = new NumberFormatter(format);
		// formatter.setValueClass(Integer.class);
		// formatter.setMinimum(5);
		// //formatter.setMaximum(Integer.MAX_VALUE);
		// formatter.setAllowsInvalid(false);
		// form
		// // If you want the value to be committed on each keystroke instead of
		// // focus lost
		// formatter.setCommitsOnValidEdit(true);

		JFormattedTextField amount_e = new JFormattedTextField();
		amount_e.setFont(new Font("Dialog", Font.PLAIN, 16));
		PlainDocument doc = (PlainDocument) amount_e.getDocument();
		doc.setDocumentFilter(new MyIntFilter());

		JLabel lblNewLabel_1 = new JLabel("موضوع:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));

		JFormattedTextField text_e = new JFormattedTextField();
		text_e.setFont(new Font("Dialog", Font.PLAIN, 16));

		JButton submit_e = new JButton("ثبت");
		GroupLayout gl_panel_expense = new GroupLayout(panel_expense);
		gl_panel_expense.setHorizontalGroup(gl_panel_expense.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_expense.createSequentialGroup().addContainerGap().addGroup(gl_panel_expense
						.createParallelGroup(Alignment.LEADING)
						.addComponent(text_e, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(amount_e, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(label_6, Alignment.TRAILING).addComponent(lblNewLabel_1, Alignment.TRAILING)
						.addComponent(submit_e, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_expense.setVerticalGroup(gl_panel_expense.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_expense.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addGap(18).addComponent(label_6).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(amount_e, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(text_e, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(submit_e).addContainerGap(126, Short.MAX_VALUE)));
		panel_expense.setLayout(gl_panel_expense);

		JPanel panel_income = new JPanel();
		tabbedPane.addTab("درآمد", null, panel_income, null);

		JLabel label_7 = new JLabel("ثبت درآمد");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel label_8 = new JLabel("مبلغ:");
		label_8.setFont(new Font("Dialog", Font.BOLD, 16));

		JFormattedTextField amount_i = new JFormattedTextField();
		amount_i.setFont(new Font("Dialog", Font.PLAIN, 16));
		PlainDocument docs = (PlainDocument) amount_i.getDocument();
		docs.setDocumentFilter(new MyIntFilter());

		JLabel label_9 = new JLabel("موضوع:");
		label_9.setFont(new Font("Dialog", Font.BOLD, 16));

		JFormattedTextField text_i = new JFormattedTextField();
		text_i.setFont(new Font("Dialog", Font.PLAIN, 16));

		JButton submit_i = new JButton("ثبت");
		GroupLayout gl_panel_income = new GroupLayout(panel_income);
		gl_panel_income
				.setHorizontalGroup(gl_panel_income.createParallelGroup(Alignment.LEADING).addGroup(
						gl_panel_income.createSequentialGroup().addContainerGap().addGroup(gl_panel_income
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_income.createSequentialGroup()
										.addComponent(submit_i, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panel_income.createSequentialGroup()
										.addGroup(gl_panel_income.createParallelGroup(Alignment.LEADING)
												.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 333,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(label_8, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
														41, GroupLayout.PREFERRED_SIZE)
												.addComponent(amount_i, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
										.addContainerGap())
								.addGroup(Alignment.TRAILING,
										gl_panel_income.createSequentialGroup()
												.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
								.addGroup(Alignment.TRAILING,
										gl_panel_income.createSequentialGroup()
												.addComponent(text_i, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
												.addContainerGap()))));
		gl_panel_income.setVerticalGroup(gl_panel_income.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_income.createSequentialGroup().addContainerGap()
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(amount_i, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(label_9, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(text_i, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(submit_i).addContainerGap(126, Short.MAX_VALUE)));
		panel_income.setLayout(gl_panel_income);

		JPanel panel_config = new JPanel();
		tabbedPane.addTab("تنظیمات", null, panel_config, null);

		JLabel lblUsername = new JLabel("نام کاربری:");

		username_field = new JTextField();
		username_field.setFont(new Font("Dialog", Font.PLAIN, 14));
		username_field.setColumns(10);

		JLabel lblPassword = new JLabel("رمز عبور:");

		password_field = new JPasswordField();
		password_field.setFont(new Font("Dialog", Font.PLAIN, 14));
		password_field.setColumns(10);

		login_btn = new JButton("ورود");
		login_btn.setBackground(Color.GREEN);

		logout_btn = new JButton("خروج");
		logout_btn.setBackground(Color.RED);

		status_label = new JLabel("وضعیت:‌ وارد نشده");
		status_label.setFont(new Font("Dialog", Font.BOLD, 20));
		status_label.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_config = new GroupLayout(panel_config);
		gl_panel_config.setHorizontalGroup(gl_panel_config.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_config.createSequentialGroup().addContainerGap().addGroup(gl_panel_config
						.createParallelGroup(Alignment.LEADING)
						.addComponent(username_field, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(password_field, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(login_btn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(logout_btn, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(lblUsername, Alignment.TRAILING).addComponent(lblPassword, Alignment.TRAILING)
						.addComponent(status_label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_config.setVerticalGroup(gl_panel_config.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_config.createSequentialGroup().addGap(19).addComponent(lblUsername)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(username_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblPassword).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(password_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(login_btn).addGap(18).addComponent(logout_btn).addGap(18)
						.addComponent(status_label).addContainerGap(109, Short.MAX_VALUE)));
		panel_config.setLayout(gl_panel_config);

		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				if (sourceTabbedPane.getSelectedIndex() == 0) {
					if (con.isLoggedIn())
						reloadStats();
				}
			}

		};
		tabbedPane.addChangeListener(changeListener);

		reloadApp();

		logout_btn.addActionListener((e) -> {
			con.logout();
			reloadApp();
		});

		login_btn.addActionListener((e) -> {
			if (!con.login(username_field.getText(), password_field.getText())) {
				JOptionPane.showMessageDialog(null,
						"Error !\nUsername or password is wrong or internet connection problem.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			username_field.setText("");
			password_field.setText("");
			reloadApp();
		});

		submit_e.addActionListener((e) -> {
			if (!con.submitExpense(amount_e.getText(), text_e.getText())) {
				JOptionPane.showMessageDialog(null, "Error !\nTry again.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			amount_e.setText("");
			text_e.setText("");
			reloadApp();
		});

		submit_i.addActionListener((e) -> {
			if (!con.submitIncome(amount_i.getText(), text_i.getText())) {
				JOptionPane.showMessageDialog(null, "Error !\nTry again.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			amount_i.setText("");
			text_i.setText("");
			reloadApp();

		});
	}

	private void reloadStats() {
		EventQueue.invokeLater(() -> {
			HashMap<String, Integer> map = con.getStats();
			expense_amount.setText(Integer.toString(map.get("expense_amount")));
			expense_count.setText(Integer.toString(map.get("expense_count")));
			income_amount.setText(Integer.toString(map.get("income_amount")));
			income_count.setText(Integer.toString(map.get("income_count")));
		});
	}

	private void reloadApp() {
		if (!con.isLoggedIn()) {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, false);

			status_label.setText("وضعیت :‌ وارد نشده");
			logout_btn.setEnabled(false);
			login_btn.setEnabled(true);
			username_field.setEnabled(true);
			password_field.setEnabled(true);
			tabbedPane.setSelectedIndex(3);
		} else {
			reloadStats();
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(2, true);
			status_label.setText("وضعیت : وارد شده");
			login_btn.setEnabled(false);
			logout_btn.setEnabled(true);
			username_field.setEnabled(false);
			password_field.setEnabled(false);
		}
	}
}
