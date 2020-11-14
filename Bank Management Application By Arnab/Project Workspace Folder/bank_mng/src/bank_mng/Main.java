package bank_mng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

class pane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	pane() {
		setLayout(null);
		setBounds(0, 0, 965, 640);
		setBackground(Color.gray.brighter());
	}
}

class user {
	int ac_num;
	double balance;
	String name;
	String pass;

	user() {
		ac_num = -1;
		balance = 0;
		name = "null";
		pass = "null";
	}
}

class empp {
	int id;
	double salary;
	String name;
	String position;

	empp() {
		id = -1;
		salary = 0;
		name = "null";
		position = "null";
	}
}

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Main_size_of_database = 1000;

	private pane pp;
	private Container c;
	private ImageIcon adminlogin;
	private Font f = new Font("Times New Roman", Font.BOLD, 20);
	private String getadmin, getpass, mainadmin = "arnab", mainpass = "arnab";
	private int trial = 3;
	private user[] cust = new user[Main_size_of_database];
	private int cust_range = 0, i;
	private String acn, bln, usr, pss, acc, bbl, trac1, trac2, trbl;
	private int index1, index2;

	private empp[] emp = new empp[Main_size_of_database];
	private int emp_range = 0;
	private String ename, eid, esal, epos;

	public Main() {
		setVisible(true);
		setBounds(100, 100, 980, 680);
		setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Bank Management Application Version 1.0.0 (by Arnab)");
		setResizable(false);
		try {
			readStrCust();
			readStrEmp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initC();
	}

	public void readStrCust() throws Exception {
		File file = new File("XeroBankDatabaseCustomers.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;

		i = 0;

		while ((st = br.readLine()) != null) {
			if (i % 4 == 0) {
				cust[cust_range] = new user();
				cust[cust_range].name = st;
			}
			if (i % 4 == 1) {
				cust[cust_range].ac_num = Integer.parseInt(st);
			}
			if (i % 4 == 2) {
				cust[cust_range].balance = Double.parseDouble(st);
			}
			if (i % 4 == 3) {
				cust[cust_range].pass = st;
				cust_range++;
			}
			System.out.println(st);
			System.out.println("++++++++++++++++++++++++++++" + i + "+++++++++++++++++++++");
			i++;
		}
		br.close();
	}

	public void readStrEmp() throws Exception {
		File file = new File("XeroBankDatabaseEmployee.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;

		i = 0;

		while ((st = br.readLine()) != null) {
			if (i % 4 == 0) {
				emp[emp_range] = new empp();
				emp[emp_range].name = st;
			}
			if (i % 4 == 1) {
				emp[emp_range].id = Integer.parseInt(st);
			}
			if (i % 4 == 2) {
				emp[emp_range].salary = Double.parseDouble(st);
			}
			if (i % 4 == 3) {
				emp[emp_range].position = st;
				emp_range++;
			}
			System.out.println(st);
			System.out.println("++++++++++++++++++++++++++++" + i + "+++++++++++++++++++++");
			i++;

		}
		br.close();
	}

	public void initC() {

		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.gray);

		ImageIcon ico = new ImageIcon(getClass().getResource("banklogo.png"));
		this.setIconImage(ico.getImage());

		pp = new pane();

		createHomePage();

		c.add(pp);
		repaint();
	}

	public void createHomePage() {
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("homepage.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JButton bank = new JButton();
		bank.setBounds(321, 248, 321, 65);
		JButton user = new JButton();
		user.setBounds(321, 349, 322, 65);
		bank.setContentAreaFilled(false);
		user.setContentAreaFilled(false);

		pp.add(bank);
		pp.add(user);
		repaint();

		bank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pp.removeAll();
				repaint();
				createBankLogin();
			}
		});

		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pp.removeAll();
				repaint();
				createUserLogin();
			}
		});
		pp.add(page);
		repaint();
	}

	public void createUserLogin() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		JButton add_cst, del_cst;
		add_cst = new JButton();
		del_cst = new JButton();
		ImageIcon img = new ImageIcon(getClass().getResource("userpage1.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		add_cst.setContentAreaFilled(false);
		add_cst.setBounds(249, 177, 464, 52);
		pp.add(add_cst);
		add_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userLogin();
			}
		});
		del_cst.setContentAreaFilled(false);
		del_cst.setBounds(249, 253, 464, 53);
		pp.add(del_cst);
		del_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSignup();
			}
		});

		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});

		pp.add(page);
		repaint();
	}

	public void userSignup() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("newcust.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		cust[cust_range] = new user();
		JTextField ac, bl, nm, ps;
		ac = new JTextField();
		bl = new JTextField();
		nm = new JTextField();
		ps = new JTextField();
		ac.setBackground(Color.white);
		bl.setBackground(Color.white);
		nm.setBackground(Color.white);
		ps.setBackground(Color.white);
		ac.setFont(f);
		bl.setFont(f);
		nm.setFont(f);
		ps.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		bl.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ps.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		ps.setBounds(339, 330, 456, 48);
		bl.setBounds(339, 404, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acn = ac.getText();
					bln = bl.getText();
					usr = nm.getText();
					pss = ps.getText();
					int accountNumberCheck = Integer.parseInt(acn);
					boolean duplicate = callingCheckDuplicate(accountNumberCheck);
					if (duplicate) {
						JOptionPane.showMessageDialog(null,
								"Illigal Account Number.\nThe Account Number is already Taken.\nChoose Another One.");
					} else {
						if ((!acn.isEmpty()) && (!bln.isEmpty()) && (!usr.isEmpty()) && (!pss.isEmpty())) {
							cust[cust_range].ac_num = Integer.parseInt(acn);
							cust[cust_range].name = usr;
							cust[cust_range].pass = pss;
							cust[cust_range].balance = Double.parseDouble(bln);
							JOptionPane.showMessageDialog(null,
									"------------------------------------------------------\nAccount Creation Successful\n====================================\nAccount Name : "
											+ cust[cust_range].name + "\nAccount Number : " + cust[cust_range].ac_num
											+ "\nAccount Balance : " + cust[cust_range].balance
											+ "\n====================================\n");
							cust_range++;
							userSignup();
						} else {
							JOptionPane.showMessageDialog(null, "Enter All Account Credentials To Create Account!");
							userSignup();
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}

		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createUserLogin();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(bl);
		pp.add(nm);
		pp.add(ps);
		pp.add(page);
		repaint();
	}

	public boolean callingCheckDuplicate(int xx) {
		boolean f = false;

		for (i = 0; i < cust_range; i++)
			if (xx == cust[i].ac_num) {
				f = true;
				break;
			}
		return f;
	}

	public void userLogin() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("userlogin.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac, nm;
		ac = new JTextField();
		nm = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String idd = ac.getText();
					String ppooss = nm.getText();
					int ideee = Integer.parseInt(idd);
					int index = -1;
					if (ac.getText().isEmpty() && nm.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter All Login Credentials!");
					} else {
						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == ideee) {
								index = i;
								break;
							}
						}
						if (index != -1) {
							if ((cust[index].pass).compareTo(ppooss) == 0) {
								JOptionPane.showMessageDialog(null, "Login Successfull!");
								userDashboard(index);

							} else {
								JOptionPane.showMessageDialog(null, "Invalid Account Number or Password!");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Invalid Account Number or Password!");
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter Account Number Correctly!");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createUserLogin();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void userDashboard(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		Font dash_font = new Font("Times New Roman", Font.BOLD, 24);
		JButton add_cst, del_cst, add_bl, del_bl, trans_bl, mm;
		add_cst = new JButton();
		del_cst = new JButton();
		add_bl = new JButton();
		del_bl = new JButton();
		trans_bl = new JButton();
		mm = new JButton();
		ImageIcon img = new ImageIcon(getClass().getResource("userdshbrd.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JLabel userName = new JLabel();
		userName.setBounds(350, 56, 467, 55);
		userName.setText(cust[index].name);
		userName.setFont(dash_font);
		pp.add(userName);
		JLabel accountNumber = new JLabel();
		accountNumber.setBounds(350, 84, 467, 55);
		accountNumber.setText(Integer.toString(cust[index].ac_num));
		accountNumber.setFont(dash_font);
		pp.add(accountNumber);
		JLabel currentBalance = new JLabel();
		currentBalance.setBounds(350, 112, 467, 55);
		currentBalance.setText(Double.toString(cust[index].balance));
		currentBalance.setFont(dash_font);
		pp.add(currentBalance);

		add_cst.setContentAreaFilled(false);
		add_cst.setBounds(248, 176, 467, 55);
		pp.add(add_cst);
		add_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit(index);
			}
		});
		del_cst.setContentAreaFilled(false);
		del_cst.setBounds(248, 253, 467, 55);
		pp.add(del_cst);
		del_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw(index);
			}
		});
		add_bl.setContentAreaFilled(false);
		add_bl.setBounds(248, 327, 467, 55);
		pp.add(add_bl);
		add_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendmoney(index);
			}
		});

		del_bl.setContentAreaFilled(false);
		del_bl.setBounds(248, 401, 467, 55);
		pp.add(del_bl);
		del_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepass(index);
			}
		});

		trans_bl.setContentAreaFilled(false);
		trans_bl.setBounds(248, 475, 467, 55);
		pp.add(trans_bl);
		trans_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parmdel(index);
			}
		});

		mm.setContentAreaFilled(false);
		mm.setBounds(248, 547, 467, 55);
		pp.add(mm);
		mm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});

		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createUserLogin();
			}
		});

		pp.add(page);
		repaint();
	}

	public void sendmoney(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("sendmoney.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField nm, ps;
		nm = new JTextField();
		ps = new JTextField();
		nm.setBackground(Color.white);
		ps.setBackground(Color.white);
		nm.setFont(f);
		ps.setFont(f);
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ps.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBounds(339, 256, 456, 48);
		ps.setBounds(339, 330, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String rcv = nm.getText();
					String amm = ps.getText();

					if (rcv.isEmpty() && amm.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter All Credentials.");
					} else {
						int index2_num = Integer.parseInt(rcv);
						int index2 = -1;
						Double sendbal = Double.parseDouble(amm);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == index2_num) {
								index2 = i;
								break;
							}
						}

						if (cust[index].balance < sendbal) {
							JOptionPane.showMessageDialog(null, "Not Enough Balance.");
						} else {
							if (index2 == -1) {
								JOptionPane.showMessageDialog(null, "Receiver Not Found.");
							} else {
								JOptionPane.showMessageDialog(null,
										"Send Money Successfull\nMoney Sent : " + sendbal
												+ "\nReceiver Account Number : " + index2_num
												+ "\nYour Current Balance : " + (cust[index].balance - sendbal));
								cust[index2].balance += sendbal;
								cust[index].balance -= sendbal;
							}
						}
					}

				} catch (Exception e2) {

				}
				sendmoney(index);
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDashboard(index);
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(nm);
		pp.add(ps);
		pp.add(page);
		repaint();
	}

	public void deposit(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("deposit.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JTextField nm;

		nm = new JTextField();
		nm.setBackground(Color.white);
		nm.setFont(f);
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBounds(339, 258, 456, 48);

		JButton addblnc = new JButton();
		addblnc.setContentAreaFilled(false);
		addblnc.setBounds(230, 495, 370, 49);

		addblnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acc = Integer.toString(cust[index].ac_num);
					bbl = nm.getText();
					if ((!acc.isEmpty()) && (!bbl.isEmpty())) {
						boolean f = false;
						int acnum = Integer.parseInt(acc);
						double adding = Double.parseDouble(bbl);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == acnum) {
								f = true;
								JOptionPane.showMessageDialog(null,
										"==============================\nAccount Balance Add Successful\n===+==========================\nAccount Name : "
												+ cust[i].name + "\nAccount Number : " + Integer.toString(acnum)
												+ "\nPrevious Balance : " + Double.toString(cust[i].balance)
												+ "\nAdded Balance : " + Double.toString(adding)
												+ "\nCurrent Balance : " + Double.toString(cust[i].balance + adding));
								cust[i].balance += adding;
								break;
							}
						}
						if (f) {
							deposit(index);
						} else
							JOptionPane.showMessageDialog(null, "Sorry Account Not Found\nPlease Try Again!");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Account Number And Amount To Add Balance!");
						deposit(index);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});

		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(51, 576, 368, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDashboard(index);
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(456, 576, 346, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(addblnc);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void changepass(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("changepass.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac, nm;
		ac = new JTextField();
		nm = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String old = ac.getText();
					String nw = nm.getText();

					if (nw.length() <= 3) {
						JOptionPane.showMessageDialog(null, "Password Must Be More Than 3 Latters.");
					} else {
						if (old.isEmpty() && nw.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Enter All Credentuals.");
						} else {
							if (old.compareTo(cust[index].pass) == 0) {
								cust[index].pass = nw;
								JOptionPane.showMessageDialog(null, "Password Change Successfull.");
							} else {
								JOptionPane.showMessageDialog(null, "Passsword Wrong!!!");
							}
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Correctly!");
				}
				changepass(index);
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDashboard(index);
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void parmdel(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("parmdel.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField nm;
		nm = new JTextField();
		nm.setBackground(Color.white);
		nm.setFont(f);
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBounds(339, 256, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String nw = nm.getText();

					if (nw.compareTo(cust[index].pass) == 0) {
						cust[index].ac_num = -1;
						cust[index].balance = 0;
						cust[index].name = "null";
						cust[index].pass = "null";
						JOptionPane.showMessageDialog(null, "Your Account Parmanently Deleted.");
						createUserLogin();
					} else {
						JOptionPane.showMessageDialog(null, "Enter Correct Password To Delete Your Account.");
						parmdel(index);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter Correct Credentials!");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDashboard(index);
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void withdraw(int index) {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("withdraw.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JTextField nm;

		nm = new JTextField();
		nm.setBackground(Color.white);
		nm.setFont(f);
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBounds(339, 259, 456, 48);

		JButton addblnc = new JButton();
		addblnc.setContentAreaFilled(false);
		addblnc.setBounds(227, 493, 372, 49);

		addblnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acc = Integer.toString(cust[index].ac_num);
					bbl = nm.getText();
					if ((!acc.isEmpty()) && (!bbl.isEmpty())) {
						boolean f = false;
						int acnum = Integer.parseInt(acc);
						double adding = Double.parseDouble(bbl);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == acnum) {
								f = true;
								JOptionPane.showMessageDialog(null,
										"==============================\nAccount Balance Withdrwal Successful\n===+==========================\nAccount Name : "
												+ cust[i].name + "\nAccount Number : " + Integer.toString(acnum)
												+ "\nPrevious Balance : " + Double.toString(cust[i].balance)
												+ "\nWithdrawn Balance : " + Double.toString(adding)
												+ "\nCurrent Balance : " + Double.toString(cust[i].balance - adding));
								cust[i].balance -= adding;
								break;
							}
						}
						if (f) {
							withdraw(index);
						} else
							JOptionPane.showMessageDialog(null, "Sorry Account Not Found\nPlease Try Again!");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Account Number And Amount To Withdraw Balance!");
						withdraw(index);

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});

		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(48, 576, 368, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDashboard(index);
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(456, 576, 346, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(addblnc);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void createBankLogin() {
		pp.removeAll();
		JLabel adminlab = new JLabel();
		adminlab.setBounds(0, 0, 965, 640);
		adminlab.setOpaque(true);
		adminlogin = new ImageIcon(getClass().getResource("adminpage1.png"));
		adminlab.setIcon(adminlogin);

		JTextField admin_id = new JTextField();
		JPasswordField admin_pass = new JPasswordField();
		admin_id.setBackground(Color.white);
		admin_pass.setBackground(Color.white);
		admin_id.setBounds(347, 245, 271, 46);
		admin_pass.setBounds(347, 402, 271, 46);
		admin_id.setFont(f);
		admin_pass.setFont(f);
		admin_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		admin_pass.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		JButton login = new JButton();
		login.setBounds(410, 501, 142, 40);
		login.setBackground(Color.red);
		login.setBorderPainted(false);
		login.setOpaque(false);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getadmin = admin_id.getText();
				getpass = String.valueOf(admin_pass.getPassword());
				if ((!getadmin.isEmpty()) && (!getpass.isEmpty())) {
					if (getadmin.compareTo(mainadmin) == 0 && getpass.compareTo(mainpass) == 0 && trial > 0) {
						pp.removeAll();
						createBankManage();
						System.out.println("Login as Admin Successful");
					} else if (getadmin.compareTo(mainadmin) != 0 && getpass.compareTo(mainpass) != 0 && trial >= 0) {
						JOptionPane.showMessageDialog(null, "Invalid Admin ID or PassWord\nTrial Left " + trial + " !");
						trial--;
					} else if (trial <= 0) {
						JOptionPane.showMessageDialog(null,
								"Sorry You Have 0 trial left\nYou are prohibited for login!");
					}
				} else
					JOptionPane.showMessageDialog(null, "Please Enter Credentials to Login");
			}
		});
		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(login);
		pp.add(adminlab);
		pp.add(admin_pass);
		pp.add(admin_id);
		repaint();
	}

	public void createBankManage() {
		pp.removeAll();
		JLabel adminlab = new JLabel();
		adminlab.setBounds(0, 0, 965, 640);
		adminlab.setOpaque(true);
		adminlogin = new ImageIcon(getClass().getResource("adminmanage.png"));
		adminlab.setIcon(adminlogin);

		JButton emp, cst, premp, prcst, mm, ex;
		emp = new JButton();
		cst = new JButton();
		premp = new JButton();
		prcst = new JButton();
		mm = new JButton();
		ex = new JButton();

		premp.setContentAreaFilled(false);
		premp.setBounds(246, 180, 470, 52);
		pp.add(premp);

		prcst.setContentAreaFilled(false);
		prcst.setBounds(246, 258, 470, 52);
		pp.add(prcst);

		emp.setContentAreaFilled(false);
		emp.setBounds(246, 333, 470, 52);
		pp.add(emp);

		cst.setContentAreaFilled(false);
		cst.setBounds(246, 408, 470, 53);
		pp.add(cst);

		mm.setContentAreaFilled(false);
		mm.setBounds(246, 484, 470, 52);
		pp.add(mm);

		ex.setContentAreaFilled(false);
		ex.setBounds(246, 558, 470, 52);
		pp.add(ex);

		premp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printEmp();
			}
		});

		prcst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printCst();

			}
		});

		emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});

		cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});

		mm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnMain();
			}
		});

		ex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBankLogin();
			}
		});
		pp.add(adminlab);
		repaint();
	}

	public void createEmpMng() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		JButton add_emp, del_emp, incr_sal, decr_sal, add_bns, chng_pos;
		add_emp = new JButton();
		del_emp = new JButton();
		incr_sal = new JButton();
		decr_sal = new JButton();
		add_bns = new JButton();
		chng_pos = new JButton();
		ImageIcon img = new ImageIcon(getClass().getResource("empmng.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		add_emp.setContentAreaFilled(false);
		add_emp.setBounds(249, 176, 470, 53);
		pp.add(add_emp);
		add_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmp();
			}
		});
		del_emp.setContentAreaFilled(false);
		del_emp.setBounds(249, 255, 470, 53);
		pp.add(del_emp);
		del_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEmp();
			}
		});
		incr_sal.setContentAreaFilled(false);
		incr_sal.setBounds(249, 331, 470, 53);
		pp.add(incr_sal);
		incr_sal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increaseEmpSal();
			}
		});

		decr_sal.setContentAreaFilled(false);
		decr_sal.setBounds(249, 405, 470, 53);
		pp.add(decr_sal);
		decr_sal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decreaseEmpSal();
			}
		});

		add_bns.setContentAreaFilled(false);
		add_bns.setBounds(249, 483, 470, 51);
		pp.add(add_bns);
		add_bns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmp();
				saveCustTxt();
				saveEmpTxt();
			}
		});

		chng_pos.setContentAreaFilled(false);
		chng_pos.setBounds(249, 556, 470, 53);
		pp.add(chng_pos);
		chng_pos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePos();
			}
		});

		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBankManage();
			}
		});

		pp.add(page);
		repaint();
	}

	public void addEmp() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("addemp.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		emp[emp_range] = new empp();
		JTextField emp_id, emp_position, emp_name, emp_salary;
		emp_id = new JTextField();
		emp_position = new JTextField();
		emp_name = new JTextField();
		emp_salary = new JTextField();
		emp_id.setBackground(Color.white);
		emp_position.setBackground(Color.white);
		emp_name.setBackground(Color.white);
		emp_salary.setBackground(Color.white);
		emp_id.setFont(f);
		emp_position.setFont(f);
		emp_name.setFont(f);
		emp_salary.setFont(f);
		emp_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_position.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_salary.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_id.setBounds(339, 180, 456, 48);
		emp_name.setBounds(339, 256, 456, 48);
		emp_salary.setBounds(339, 330, 456, 48);
		emp_position.setBounds(339, 404, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					eid = emp_id.getText();
					epos = emp_position.getText();
					ename = emp_name.getText();
					esal = emp_salary.getText();

					if ((!eid.isEmpty()) && (!epos.isEmpty()) && (!ename.isEmpty()) && (!esal.isEmpty())) {
						emp[emp_range].id = Integer.parseInt(eid);
						emp[emp_range].name = ename;
						emp[emp_range].salary = Double.parseDouble(esal);
						emp[emp_range].position = epos;
						JOptionPane.showMessageDialog(null,
								"------------------------------------------------------\nEmploye Added Successfully\n====================================\nEmployee Name : "
										+ emp[emp_range].name + "\nEmployee ID : " + emp[emp_range].id
										+ "\nEmployee Salary : " + emp[emp_range].salary + "\nEmployee Position : "
										+ emp[emp_range].position + "\n====================================\n");
						emp_range++;
						addEmp();
					} else {
						JOptionPane.showMessageDialog(null, "Enter All Employee Credentials To Add Employee!");
						addEmp();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(emp_id);
		pp.add(emp_position);
		pp.add(emp_name);
		pp.add(emp_salary);
		pp.add(page);
		repaint();
	}

	public void removeEmp() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("emprem.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		emp[emp_range] = new empp();
		JTextField emp_id, emp_position;
		emp_id = new JTextField();
		emp_position = new JTextField();

		emp_id.setBackground(Color.white);
		emp_position.setBackground(Color.white);
		emp_id.setFont(f);
		emp_position.setFont(f);
		emp_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_position.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_id.setBounds(339, 256, 456, 48);
		emp_position.setBounds(339, 330, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String emid = emp_id.getText();
					String emres = emp_position.getText();
					boolean found = false;
					int emidint;

					if ((!emid.isEmpty()) && (!emres.isEmpty())) {
						emidint = Integer.parseInt(emid);
						for (i = 0; i < emp_range; i++) {
							if (emidint == emp[i].id) {
								emidint = i;
								found = true;
								break;
							}
						}

						if ((!found))
							JOptionPane.showMessageDialog(null, "Employee ID Not Found");
						else {
							JOptionPane.showMessageDialog(null,
									"------------------------------------------------------\nEmploye Removed Successfully\n====================================\nRemoved Employee Name : "
											+ emp[emidint].name + "\nRemoved Employee ID : " + emp[emidint].id
											+ "\nRemoved Employee Salary : " + emp[emidint].salary
											+ "\nRemoved Employee Position : " + emp[emidint].position
											+ "\n====================================\n");
							emp[emidint].id = -1;
							emp[emidint].name = "null";
							emp[emidint].salary = 0;
							emp[emidint].position = "null";
						}
						removeEmp();
					} else {
						JOptionPane.showMessageDialog(null, "Enter All Employee Credentials To Remove Employee!");
						removeEmp();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(emp_id);
		pp.add(emp_position);
		pp.add(page);
		repaint();
	}

	public void increaseEmpSal() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("incemp.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		emp[emp_range] = new empp();
		JTextField emp_id, emp_position;
		emp_id = new JTextField();
		emp_position = new JTextField();

		emp_id.setBackground(Color.white);
		emp_position.setBackground(Color.white);
		emp_id.setFont(f);
		emp_position.setFont(f);
		emp_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_position.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_id.setBounds(338, 255, 461, 48);
		emp_position.setBounds(338, 329, 461, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(229, 488, 369, 47);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String emid = emp_id.getText();
					String emres = emp_position.getText();
					boolean found = false;
					int emidint;
					double incr;

					if ((!emid.isEmpty()) && (!emres.isEmpty())) {
						emidint = Integer.parseInt(emid);
						incr = Double.parseDouble(emres);
						for (i = 0; i < emp_range; i++) {
							if (emidint == emp[i].id) {
								emidint = i;
								found = true;
								break;
							}
						}

						if ((!found))
							JOptionPane.showMessageDialog(null, "Employee ID Not Found");
						else {
							JOptionPane.showMessageDialog(null,
									"------------------------------------------------------\nEmploye Salary Increased\n====================================\nEmployee Name : "
											+ emp[emidint].name + "\nEmployee ID : " + emp[emidint].id
											+ "\nEmployee Previous Salary : " + emp[emidint].salary
											+ "\nEmployee Current Salary : " + (emp[emidint].salary + incr)
											+ "\n====================================\n");
							emp[emidint].salary = emp[emidint].salary + incr;
						}
						increaseEmpSal();
					} else {
						JOptionPane.showMessageDialog(null, "Enter All Employee Credentials To Increase Salary!");
						increaseEmpSal();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(52, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 345, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(emp_id);
		pp.add(emp_position);
		pp.add(page);
		repaint();
	}

	public void decreaseEmpSal() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("decemp.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		emp[emp_range] = new empp();
		JTextField emp_id, emp_position;
		emp_id = new JTextField();
		emp_position = new JTextField();

		emp_id.setBackground(Color.white);
		emp_position.setBackground(Color.white);
		emp_id.setFont(f);
		emp_position.setFont(f);
		emp_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_position.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emp_id.setBounds(338, 255, 461, 48);
		emp_position.setBounds(338, 329, 461, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(229, 488, 369, 47);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String emid = emp_id.getText();
				String emres = emp_position.getText();
				boolean found = false;
				int emidint;
				double incr;

				if ((!emid.isEmpty()) && (!emres.isEmpty())) {
					emidint = Integer.parseInt(emid);
					incr = Double.parseDouble(emres);
					for (i = 0; i < emp_range; i++) {
						if (emidint == emp[i].id) {
							emidint = i;
							found = true;
							break;
						}
					}

					if ((!found))
						JOptionPane.showMessageDialog(null, "Employee ID Not Found");
					else {
						JOptionPane.showMessageDialog(null,
								"------------------------------------------------------\nEmploye Salary Increased\n====================================\nEmployee Name : "
										+ emp[emidint].name + "\nEmployee ID : " + emp[emidint].id
										+ "\nEmployee Previous Salary : " + emp[emidint].salary
										+ "\nEmployee Current Salary : " + (emp[emidint].salary - incr)
										+ "\n====================================\n");
						emp[emidint].salary = emp[emidint].salary - incr;
					}
					decreaseEmpSal();
				} else {
					JOptionPane.showMessageDialog(null, "Enter All Employee Credentials To Increase Salary!");
					decreaseEmpSal();
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(52, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 345, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(emp_id);
		pp.add(emp_position);
		pp.add(page);
		repaint();
	}

	public void searchEmp() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("searchemp.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac;
		ac = new JTextField();

		ac.setBackground(Color.white);

		ac.setFont(f);

		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		ac.setBounds(339, 180, 456, 48);

		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sss = ac.getText();
					int ideee = Integer.parseInt(sss);
					int index = -1;

					if (ac.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter Employee ID Correctly!");
					} else {
						for (i = 0; i < emp_range; i++) {
							if (emp[i].id == ideee) {
								index = i;
								break;
							}
						}
						if (index != -1)
							JOptionPane.showMessageDialog(null,
									"============================\nEmployee Name : " + emp[index].name
											+ "\nEmployee ID : " + emp[i].id + "\nEmployee Position : "
											+ emp[i].position + "\nEmployee Salary : " + emp[i].salary
											+ "\n====================================");
						else
							JOptionPane.showMessageDialog(null, "Employee Not Found!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter Employee ID Correctly!");
				}
				searchEmp();
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(page);
		repaint();
	}

	public void changePos() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("changepos.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac, nm;
		ac = new JTextField();
		nm = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String idd = ac.getText();
					String ppooss = nm.getText();
					int ideee = Integer.parseInt(idd);
					int index = -1;

					if (ac.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter Employee ID Correctly!");
					} else {
						for (i = 0; i < emp_range; i++) {
							if (emp[i].id == ideee) {
								index = i;
								break;
							}
						}
						if (index != -1) {
							JOptionPane.showMessageDialog(null,
									"============================\nEmployee Name : " + emp[index].name
											+ "\nEmployee ID : " + emp[i].id + "\nEmployee Previous Position : "
											+ emp[i].position + "\nEmployee New Position : " + ppooss
											+ "\nEmployee Salary : " + emp[i].salary
											+ "\n====================================");
							emp[index].position = ppooss;
						} else
							JOptionPane.showMessageDialog(null, "Employee Not Found!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter Employee ID Correctly!");
				}
				changePos();
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmpMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void createCstMng() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		JButton add_cst, del_cst, add_bl, del_bl, trans_bl, mm;
		add_cst = new JButton();
		del_cst = new JButton();
		add_bl = new JButton();
		del_bl = new JButton();
		trans_bl = new JButton();
		mm = new JButton();
		ImageIcon img = new ImageIcon(getClass().getResource("customermng.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		add_cst.setContentAreaFilled(false);
		add_cst.setBounds(247, 175, 470, 53);
		pp.add(add_cst);
		add_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		del_cst.setContentAreaFilled(false);
		del_cst.setBounds(247, 253, 470, 53);
		pp.add(del_cst);
		del_cst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});
		add_bl.setContentAreaFilled(false);
		add_bl.setBounds(247, 330, 470, 53);
		pp.add(add_bl);
		add_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBalance();
			}
		});

		del_bl.setContentAreaFilled(false);
		del_bl.setBounds(247, 406, 470, 53);
		pp.add(del_bl);
		del_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBalance();
			}
		});

		trans_bl.setContentAreaFilled(false);
		trans_bl.setBounds(247, 482, 470, 53);
		pp.add(trans_bl);
		trans_bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferBalance();
			}
		});

		mm.setBounds(247, 556, 470, 53);
		mm.setIcon(new ImageIcon(getClass().getResource("searchcustbt.png")));
		pp.add(mm);
		mm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCst();
			}
		});

		JButton back = new JButton();
		back.setBounds(10, 10, 80, 43);
		ImageIcon img2 = new ImageIcon(getClass().getResource("back.png"));
		back.setIcon(img2);
		pp.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBankManage();
			}
		});

		pp.add(page);
		repaint();
	}

	public void addCustomer() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("newcust.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		cust[cust_range] = new user();
		JTextField ac, bl, nm, ps;
		ac = new JTextField();
		bl = new JTextField();
		nm = new JTextField();
		ps = new JTextField();
		ac.setBackground(Color.white);
		bl.setBackground(Color.white);
		nm.setBackground(Color.white);
		ps.setBackground(Color.white);
		ac.setFont(f);
		bl.setFont(f);
		nm.setFont(f);
		ps.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		bl.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ps.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		ps.setBounds(339, 330, 456, 48);
		bl.setBounds(339, 404, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acn = ac.getText();
					bln = bl.getText();
					usr = nm.getText();
					pss = ps.getText();

					if ((!acn.isEmpty()) && (!bln.isEmpty()) && (!usr.isEmpty()) && (!pss.isEmpty())) {
						cust[cust_range].ac_num = Integer.parseInt(acn);
						cust[cust_range].name = usr;
						cust[cust_range].pass = pss;
						cust[cust_range].balance = Double.parseDouble(bln);
						JOptionPane.showMessageDialog(null,
								"------------------------------------------------------\nAccount Creation Successful\n====================================\nAccount Name : "
										+ cust[cust_range].name + "\nAccount Number : " + cust[cust_range].ac_num
										+ "\nAccount Balance : " + cust[cust_range].balance
										+ "\n====================================\n");
						cust_range++;
						addCustomer();
					} else {
						JOptionPane.showMessageDialog(null, "Enter All Account Credentials To Create Account!");
						addCustomer();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(bl);
		pp.add(nm);
		pp.add(ps);
		pp.add(page);
		repaint();
	}

	public void deleteCustomer() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("deletecust.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);

		JTextField ac;
		ac = new JTextField();
		ac.setBackground(Color.white);
		ac.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(188, 304, 456, 48);

		JButton del = new JButton();
		del.setContentAreaFilled(false);
		del.setBounds(231, 490, 367, 46);
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acn = ac.getText();
					if ((!acn.isEmpty())) {
						boolean f = false;
						int acnum = Integer.parseInt(acn);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == acnum) {
								f = true;
								cust[i].ac_num = -1;
								cust[i].name = "null";
								cust[i].pass = "null";
								cust[i].balance = 0.0;
								break;
							}
						}
						if (f) {
							JOptionPane.showMessageDialog(null,
									"Account Number : " + acnum + "\nAccount Delete Successful");
							deleteCustomer();
						} else
							JOptionPane.showMessageDialog(null, "Sorry Account Not Found\nPlease Try Again!");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Account Number to delete Account!");
						deleteCustomer();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Correctly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(del);
		pp.add(ac);
		pp.add(page);
		repaint();
	}

	public void addBalance() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("addbal.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JTextField ac, nm;
		ac = new JTextField();
		nm = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);

		JButton addblnc = new JButton();
		addblnc.setContentAreaFilled(false);
		addblnc.setBounds(231, 490, 367, 46);

		addblnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acc = ac.getText();
					bbl = nm.getText();
					if ((!acc.isEmpty()) && (!bbl.isEmpty())) {
						boolean f = false;
						int acnum = Integer.parseInt(acc);
						double adding = Double.parseDouble(bbl);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == acnum) {
								f = true;
								JOptionPane.showMessageDialog(null,
										"==============================\nAccount Balance Add Successful\n===+==========================\nAccount Name : "
												+ cust[i].name + "\nAccount Number : " + Integer.toString(acnum)
												+ "\nPrevious Balance : " + Double.toString(cust[i].balance)
												+ "\nAdded Balance : " + Double.toString(adding)
												+ "\nCurrent Balance : " + Double.toString(cust[i].balance + adding));
								cust[i].balance += adding;
								break;
							}
						}
						if (f) {
							addBalance();
						} else
							JOptionPane.showMessageDialog(null, "Sorry Account Not Found\nPlease Try Again!");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Account Number And Amount To Add Balance!");
						addBalance();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});

		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(addblnc);
		pp.add(ac);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void deleteBalance() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("delbal.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		JTextField ac, nm;
		ac = new JTextField();
		nm = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);

		JButton addblnc = new JButton();
		addblnc.setContentAreaFilled(false);
		addblnc.setBounds(231, 490, 367, 46);

		addblnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acc = ac.getText();
					bbl = nm.getText();
					if ((!acc.isEmpty()) && (!bbl.isEmpty())) {
						boolean f = false;
						int acnum = Integer.parseInt(acc);
						double adding = Double.parseDouble(bbl);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == acnum) {
								f = true;
								JOptionPane.showMessageDialog(null,
										"==============================\nAccount Balance Withdraw Successful\n===+==========================\nAccount Name : "
												+ cust[i].name + "\nAccount Number : " + Integer.toString(acnum)
												+ "\nPrevious Balance : " + Double.toString(cust[i].balance)
												+ "\nWithdrawn Balance : " + Double.toString(adding)
												+ "\nCurrent Balance : " + Double.toString(cust[i].balance - adding));
								cust[i].balance -= adding;
								break;
							}
						}
						if (f) {
							deleteBalance();
						} else
							JOptionPane.showMessageDialog(null, "Sorry Account Not Found\nPlease Try Again!");
					} else {
						JOptionPane.showMessageDialog(null, "Enter Account Number And Amount To Withdraw Balance!");
						deleteBalance();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});

		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(addblnc);
		pp.add(ac);
		pp.add(nm);
		pp.add(page);
		repaint();
	}

	public void transferBalance() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("transbal.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac, nm, ps;
		ac = new JTextField();
		nm = new JTextField();
		ps = new JTextField();
		ac.setBackground(Color.white);
		nm.setBackground(Color.white);
		ps.setBackground(Color.white);
		ac.setFont(f);
		nm.setFont(f);
		ps.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ps.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);
		nm.setBounds(339, 256, 456, 48);
		ps.setBounds(339, 330, 456, 48);
		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					trac1 = ac.getText();
					trac2 = nm.getText();
					trbl = ps.getText();

					if (trac1.compareTo(trac2) == 0 && (!trac1.isEmpty()) && (!trac2.isEmpty())) {
						JOptionPane.showMessageDialog(null, "You Can Not Transfer Balance To Same Account!");
					} else if ((!trac1.isEmpty()) && (!trac2.isEmpty()) && (!trbl.isEmpty())) {
						int ac1 = Integer.parseInt(trac1);
						int ac2 = Integer.parseInt(trac2);
						double trb = Double.parseDouble(trbl);
						boolean c1 = false, c2 = false, amm = false;

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == ac1) {
								index1 = i;
								c1 = true;
								if (cust[i].balance >= trb) {
									amm = true;
								}
							}
							if (cust[i].ac_num == ac2) {
								index2 = i;
								c2 = true;
							}
						}

						if (c1 && c2 && amm) {
							JOptionPane.showMessageDialog(null,
									"==============================\nTransfer Successful\n==============================\nFrom Account: "
											+ Integer.toString(ac1) + "\nTo Account : " + Integer.toString(ac2)
											+ "\nTransfered Amount : " + trbl + "\nSenders Previous Balance : "
											+ Double.toString(cust[index1].balance) + "\nSenders Current Balance : "
											+ Double.toString(cust[index1].balance - trb)
											+ "\nReciever's Previous Balance : " + Double.toString(cust[index2].balance)
											+ "\nReciever's Current Balance :"
											+ Double.toString(cust[index2].balance + trb));
						} else if (c1 && c2 && (!amm)) {
							JOptionPane.showMessageDialog(null,
									"==============================\nTransfer Unsuccessful\nReason : NOT ENOUGH BALANCE\n==============================\nFrom Account: "
											+ Integer.toString(ac1) + "\nTo Account : " + Integer.toString(ac2)
											+ "\nUntransfered Amount : " + trbl + "\nSenders Previous Balance : "
											+ Double.toString(cust[index1].balance) + "\nSenders Current Balance : "
											+ Double.toString(cust[index1].balance) + "\nReciever's Previous Balance : "
											+ Double.toString(cust[index2].balance) + "\nReciever's Current Balance :"
											+ Double.toString(cust[index2].balance));
						} else if ((!c1) && c2) {
							JOptionPane.showMessageDialog(null, "First Account(FROM) Not Found!");
						} else if (c1 && (!c2)) {
							JOptionPane.showMessageDialog(null, "Second Account(TO) Not Found!");
						} else if ((!c1) && (!c2)) {
							JOptionPane.showMessageDialog(null, "None Of The Accounts Are Found!");
						}
						transferBalance();
					} else {
						JOptionPane.showMessageDialog(null, "Enter All Account Credentials To Transfer Balance!");
						transferBalance();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(nm);
		pp.add(ps);
		pp.add(page);
		repaint();
	}

	public void searchCst() {
		saveCustTxt();
		saveEmpTxt();
		pp.removeAll();
		ImageIcon img = new ImageIcon(getClass().getResource("searchcst.png"));
		JLabel page = new JLabel(img);
		page.setBounds(0, 0, 965, 640);
		page.setOpaque(true);
		cust[cust_range] = new user();
		JTextField ac;
		ac = new JTextField();
		ac.setBackground(Color.white);
		ac.setFont(f);
		ac.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ac.setBounds(339, 180, 456, 48);

		JButton add = new JButton();
		add.setContentAreaFilled(false);
		add.setBounds(231, 490, 367, 46);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String accn = ac.getText();
					if (accn.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Enter Account Number.");
					} else {
						int index = -1;
						int accnn = Integer.parseInt(accn);

						for (i = 0; i < cust_range; i++) {
							if (cust[i].ac_num == accnn) {
								index = i;
								break;
							}
						}
						if (index == -1) {
							JOptionPane.showMessageDialog(null, "Account Not Found.");
						} else {
							JOptionPane.showMessageDialog(null,
									"Account Found\nAccount Name : " + cust[index].name + "\nAccount Number : "
											+ cust[index].ac_num + "\nAccount Balance : " + cust[index].balance);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter Credentials Properly.");
				}
				searchCst();
			}
		});
		JButton back = new JButton();
		back.setContentAreaFilled(false);
		back.setBounds(55, 570, 365, 49);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCstMng();
			}
		});
		JButton main = new JButton();
		main.setContentAreaFilled(false);
		main.setBounds(455, 570, 342, 49);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHomePage();
			}
		});
		pp.add(back);
		pp.add(main);
		pp.add(add);
		pp.add(ac);
		pp.add(page);
		repaint();
	}

	public void returnMain() {
		pp.removeAll();
		createHomePage();
		repaint();
	}

	public void printCst() {

		String text = "";

		for (i = 0; i < cust_range; i++) {
			text = text + "(" + (i + 1) + ")"
					+ "\n=====================================================\n=====================================================\nCustomer Name : "
					+ cust[i].name + "\nCustomer Account Number : " + cust[i].ac_num + "\nCustomer Balance : "
					+ cust[i].balance
					+ "\n=====================================================\n=====================================================\n";

		}

		System.out.println(text);

		try (PrintStream out = new PrintStream(new FileOutputStream("Print Customer List.txt"))) {
			out.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printEmp() {

		String text = "";

		for (i = 0; i < emp_range; i++) {
			text = text + "(" + (i + 1) + ")"
					+ "\n=====================================================\n=====================================================\nEmployee Name : "
					+ emp[i].name + "\nEmployee ID : " + emp[i].id + "\nEmployee Position : " + emp[i].position
					+ "\nEmployee Salary : " + emp[i].salary
					+ "\n=====================================================\n=====================================================\n";
		}
		System.out.println(text);

		try (PrintStream out = new PrintStream(new FileOutputStream("Print Employee List.txt"))) {
			out.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveCustTxt() {

		String text = "";

		for (i = 0; i < cust_range; i++) {
			if (i < (cust_range) && i > 0)
				text = text + "\n";

			text = text + cust[i].name + "\n" + cust[i].ac_num + "\n" + cust[i].balance + "\n" + cust[i].pass;
			// text =
			// text+"\n=====================================================\n=====================================================\nCustomer
			// Name : "+cust[i].name+"\nCustomer Account Number :
			// "+cust[i].ac_num+"\nCustomer Balance :
			// "+cust[i].balance+"\n=====================================================\n=====================================================\n";
		}

		try (PrintStream out = new PrintStream(new FileOutputStream("XeroBankDatabaseCustomers.txt"))) {

			out.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveEmpTxt() {

		String text = "";

		for (i = 0; i < emp_range; i++) {
			if (i < (emp_range) && i > 0)
				text = text + "\n";

			text = text + emp[i].name + "\n" + emp[i].id + "\n" + emp[i].salary + "\n" + emp[i].position;
			// text =
			// text+"\n=====================================================\n=====================================================\nCustomer
			// Name : "+cust[i].name+"\nCustomer Account Number :
			// "+cust[i].ac_num+"\nCustomer Balance :
			// "+cust[i].balance+"\n=====================================================\n=====================================================\n";
		}

		try (PrintStream out = new PrintStream(new FileOutputStream("XeroBankDatabaseEmployee.txt"))) {

			out.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Main();

		System.out.println("Arnab");

	}

}
