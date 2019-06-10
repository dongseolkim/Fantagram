package com.instagram.downloader;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Instragram Downloader V1.0.0
 * 
 * @author Dongseol Kim
 * @version 2019.05.24
 */

public class InstagramDownloaderApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static DefaultTableModel tableModel;

	private static JTextField hashField, nameField, linkField;
	private JButton instaBtn, allBtn, insertBtn, searchBtn, updateBtn, deleteBtn, downBtn, openBtn;
	private JLabel instaLabel, nameLabel, linkLabel, pathLabel;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollpane;
	private JMenuItem path, reset, version, helper;
	private ImageIcon icon;
//	public static String downloadPath = "C:/Users/user/Desktop";
	public static String downloadPath = "";
	private String columnName[] = { "No", "Name", "Link", "Type" };
	private int choiceNo;
	private String choiceName = "", choiceLink = "", choiceType = "";
	private static int lastNo = 0;

	private String imagePath = "C:/Users/user/Desktop/Dev/workspace/java-project/src/com/instagram/downloader/insta_icon.png";
	private String gitURL = "https://github.com/dongseolkim/java-project";

	private static Vector<String> userRow;

	public InstagramDownloaderApp(String title) {

		// Title
		super(title);
		icon = new ImageIcon(imagePath);
		setIconImage(icon.getImage());

		// Bar
		JMenuBar bar = new JMenuBar();
		JMenu setting = new JMenu("설정(S)");
		JMenu help = new JMenu("도움말(H)");
		help.setMnemonic('H');
		setting.setMnemonic('S');

		path = new JMenuItem("경로설정(P)", 'P');
		reset = new JMenuItem("초기화(R)", 'R');
		version = new JMenuItem("버전정보(V)", 'V');
		helper = new JMenuItem("도움말(H) - Git", 'H');

		path.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		version.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		helper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

		setting.add(path);
		setting.addSeparator();
		setting.add(reset);
		bar.add(setting);

		help.add(version);
		help.addSeparator();
		help.add(helper);
		bar.add(help);
		setJMenuBar(bar);

		// button
		instaBtn = new JButton("해시태그 검색");
		instaBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		allBtn = new JButton("전체보기");
		allBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		insertBtn = new JButton("저장");
		insertBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		updateBtn = new JButton("수정");
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		downBtn = new JButton("다운로드");
		downBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		openBtn = new JButton("링크 열기");
		openBtn.setFont(new Font("굴림", Font.PLAIN, 12));

		panel = new JPanel();
		panel.setLayout(null);
		panel.add(instaBtn);
		panel.add(allBtn);
		panel.add(insertBtn);
		panel.add(searchBtn);
		panel.add(updateBtn);
		panel.add(deleteBtn);
		panel.add(downBtn);
		panel.add(openBtn);

		// Insta Label, Field
		panel.add(instaLabel = new JLabel("HashTag: "));
		instaLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		instaLabel.setSize(64, 30);
		instaLabel.setLocation(5, 6);
		panel.add(hashField = new JTextField(""));
//		panel.add(hashField = new JTextField("yoga"));
		hashField.setSize(431, 30);
		hashField.setLocation(69, 6);

		// Name Label, Field
		panel.add(nameLabel = new JLabel("Name: "));
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		nameLabel.setSize(48, 30);
		nameLabel.setLocation(10, 358);
		panel.add(nameField = new JTextField(""));
//		panel.add(nameField = new JTextField("yoga"));
		nameField.setSize(565, 30);
		nameField.setLocation(60, 358);

		// Link Label, Field
		panel.add(linkLabel = new JLabel("link: "));
		linkLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		linkLabel.setSize(48, 30);
		linkLabel.setLocation(10, 390);
		panel.add(linkField = new JTextField(""));
//		panel.add(linkField = new JTextField("https://www.instagram.com/p/Bxp8f9LAQZ1/"));
		linkField.setSize(565, 30);
		linkField.setLocation(60, 390);

		// DownPath Label
		panel.add(pathLabel = new JLabel("path:      " + downloadPath));
		pathLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		pathLabel.setSize(615, 30);
		pathLabel.setLocation(10, 420);

		instaBtn.setSize(120, 30);
		instaBtn.setLocation(505, 6);
		allBtn.setSize(120, 30);
		allBtn.setLocation(5, 453);
		insertBtn.setSize(120, 30);
		insertBtn.setLocation(130, 453);
		searchBtn.setSize(120, 30);
		searchBtn.setLocation(255, 453);
		updateBtn.setSize(120, 30);
		updateBtn.setLocation(380, 453);
		deleteBtn.setSize(120, 30);
		deleteBtn.setLocation(505, 453);
		downBtn.setSize(308, 30);
		downBtn.setLocation(5, 488);
		openBtn.setSize(308, 30);
		openBtn.setLocation(317, 488);

		path.addActionListener(this);
		reset.addActionListener(this);
		version.addActionListener(this);
		helper.addActionListener(this);
		instaBtn.addActionListener(this);
		allBtn.addActionListener(this);
		insertBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		downBtn.addActionListener(this);
		openBtn.addActionListener(this);

//		String columnName[] = { "No.", "Name", "Type" };
//		String rowData[][] = { { "", "", "" } };
		addJTable();

		getContentPane().add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 300, 645, 593);
		setVisible(true);

		dataAll();
	}

	// JTable
	private void addJTable() {
		tableModel = new DefaultTableModel(columnName, 0) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.setAutoscrolls(true);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					TableMouseClicked(e);
					nameField.setText(choiceName);
					linkField.setText(choiceLink);

					InstagramDTO instagramDTO = new InstagramDTO();
					instagramDTO.setName(choiceName);
					instagramDTO.setLink(choiceLink);
				}
			}
		});

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		scrollpane = new JScrollPane(table);
		scrollpane.setLocation(5, 45);
		scrollpane.setSize(620, 303);
		panel.add(scrollpane);
	}

	private void TableMouseClicked(java.awt.event.MouseEvent evt) {

		int index = table.getSelectedRow();

		TableModel model = table.getModel();

		choiceNo = Integer.parseInt(model.getValueAt(index, 0).toString());
		choiceName = model.getValueAt(index, 1).toString();
		choiceLink = model.getValueAt(index, 2).toString();
		choiceType = model.getValueAt(index, 3).toString();

		System.out.println("choiceNo: " + choiceNo);
		System.out.println("choiceName: " + choiceName);
		System.out.println("choiceLink: " + choiceLink);
		System.out.println("choiceType: " + choiceType);
		System.out.println("================================================");
//        jtRowData.setVisible(true);
//        jtRowData.pack();
//        jtRowData.setLocationRelativeTo(null);
//        jtRowData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        
//        jtRowData.jTextField_Id.setText(no);
//        jtRowData.jTextField_FirstName.setText(name);
//        jtRowData.jTextField_LastName.setText(link);
//        jtRowData.jTextField_Age.setText(type);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object eventSource = e.getSource();
		if (eventSource instanceof JMenuItem) {
			if (eventSource == reset) {
				qryDataResetTable();
			} else if (eventSource == path) {
				pathSet();
			} else if (eventSource == version) {
				getVersion();
			} else if (eventSource == helper) {
				helpConn(gitURL);
			}
		} else if (eventSource == instaBtn) {
			hashSearch(hashField.getText());
		} else if (eventSource == allBtn) {
			tableModel.setNumRows(0);
			dataAll();
		} else if (eventSource == insertBtn) {
			dataInsert();
			tableModel.setNumRows(0);
			dataAll();
		} else if (eventSource == searchBtn) {
			tableModel.setNumRows(0);
			dataSearch();
		} else if (eventSource == updateBtn) {
			tableModel.setNumRows(0);
			dataUpdate();
			dataAll();
		} else if (eventSource == deleteBtn) {
			tableModel.setNumRows(0);
			dataDelete();
			dataAll();
		} else if (eventSource == downBtn) {
			dataDown();
		} else if (eventSource == openBtn) {
			linkOpen();
		}
	}

	public static void main(String[] args) {
		pathGet();
		new InstagramDownloaderApp("Instagram Downloader - Fantagram");
	}

	// instaBtn
	private void hashSearch(String hash) {
		if (hash.isEmpty()) {
			JOptionPane.showMessageDialog(null, "[해시태그] 를 입력하세요", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(hash.contains("#")) {
			JOptionPane.showMessageDialog(null, "[#]를 제외하고 문자열만 입력하세요. ex)요가 ", "Error", JOptionPane.ERROR_MESSAGE);			
		} else {
			String URL = "https://www.instagram.com/explore/tags/" + hash;
			try {
				Desktop.getDesktop().browse(new URI(URL));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	// helper
	private void helpConn(String URL) {
		try {
			Desktop.getDesktop().browse(new URI(URL));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	// version
	private void getVersion() {
		InstagramDAO.getInstagramDAO().qryVersionSelect();
	}

	private void qryDataResetTable() {
		int n = JOptionPane.showConfirmDialog(null, "모든 데이터를 삭제하시겠습니까?", "초기화", JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			InstagramDAO.getInstagramDAO().qryResetTable();
		}
	}

	// pathSet
	private static void pathSet() {
		downloadPath = JOptionPane.showInputDialog("저장 경로를 입력하세요.");
		try {
			String check = InstagramUrlCheck.validatePath(downloadPath.replaceAll("\\\\", "/"));
			if (downloadPath.isEmpty()) {
				JOptionPane.showMessageDialog(null, "저장 경로를 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (!check.equals("success")) {
				JOptionPane.showMessageDialog(null, check, "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				InstagramDTO instagramDTO = new InstagramDTO();
				instagramDTO.setPath(downloadPath);
				InstagramDAO.getInstagramDAO().qryPathUpdate(instagramDTO);
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	// pathGet
	private static void pathGet() {
		InstagramDAO.getInstagramDAO().qryPathSelect();
	}

	// allBtn
	private void dataAll() {
		List<InstagramDTO> rows = InstagramDAO.getInstagramDAO().qryAllSelect();

		lastNo = rows.size();
		if (rows != null) {
			for (InstagramDTO i : rows) {
				try {
//					no = i.getNo();
					userRow = new Vector<String>();
//					userRow.addElement(Integer.toString(++num));
					userRow.addElement(Integer.toString(i.getNo()));
					userRow.addElement(i.getName());
					userRow.addElement(i.getLink());
					userRow.addElement(i.getType());
					tableModel.addRow(userRow);

					System.out.println("no: " + i.getNo());
					System.out.println("name: " + i.getName());
					System.out.println("link: " + i.getLink());
					System.out.println("type: " + i.getType());
					System.out.println("====================================");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println("Last Number: " + lastNo);
	}

	// insertBtn
	private void dataInsert() {

		String check = InstagramUrlCheck.validateURL(linkField.getText());

		if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "파일명(name)을 입력하시기 바랍니다.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (!check.equals("success")) {
			JOptionPane.showMessageDialog(null, check, "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			// type
			String type = InstagramUrlCheck.mediaExtension(linkField.getText());
			System.out.println(type);
			InstagramDTO instagramDTO = new InstagramDTO();
			instagramDTO.setNo(++lastNo);
			instagramDTO.setName(nameField.getText());
			instagramDTO.setLink(linkField.getText());
			instagramDTO.setType(type);
			InstagramDAO.getInstagramDAO().qryDataInsert(instagramDTO);
		}
	}

	// searchBtn
	private void dataSearch() {
		InstagramDTO instagramDTO = new InstagramDTO();

		if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "파일명[name]을 입력하시기 바랍니다.", "Error", JOptionPane.ERROR_MESSAGE);
			dataAll();
		} else {
			instagramDTO.setName(nameField.getText());
			List<InstagramDTO> rows = InstagramDAO.getInstagramDAO().qryDataSearch(nameField.getText());

			if (rows.size() == 0) {
				JOptionPane.showMessageDialog(null, "저장된 링크가 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
				dataAll();
			} else {
				for (InstagramDTO i : rows) {
					userRow = new Vector<String>();
//					userRow.addElement(Integer.toString(++num));
					userRow.addElement(Integer.toString(i.getNo()));
					userRow.addElement(i.getName());
					userRow.addElement(i.getLink());
					userRow.addElement(i.getType());
					tableModel.addRow(userRow);

					System.out.println("no: " + i.getNo());
					System.out.println("name: " + i.getName());
					System.out.println("link: " + i.getLink());
					System.out.println("type: " + i.getType());
					System.out.println("====================================");
				}
			}
		}
	}

	// updateBtn
	private void dataUpdate() {
		if (choiceNo == 0) {
			JOptionPane.showMessageDialog(null, "[수정] 항목을 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "파일명[name]을 입력하시기 바랍니다.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(linkField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "링크[link]를 입력하시기 바랍니다.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			InstagramDTO instagramDTO = new InstagramDTO();
			instagramDTO.setNo(choiceNo);
			instagramDTO.setName(nameField.getText());
			instagramDTO.setLink(linkField.getText());
			InstagramDAO.getInstagramDAO().qryDataUpdate(instagramDTO);
		}
	}

	// deleteBtn
	private void dataDelete() {
		if (linkField.getText().isEmpty() && linkField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "[삭제] 항목을 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			InstagramDTO instagramDTO = new InstagramDTO();
			instagramDTO.setNo(choiceNo);
			InstagramDAO.getInstagramDAO().qryDataDelete(instagramDTO);
		}
	}

	// downBtn
	private void dataDown() {
		if (choiceLink.isEmpty()) {
			JOptionPane.showMessageDialog(null, "[다운로드] 링크를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			InstagramDownloader.getDownloadUrl(choiceLink, downloadPath, choiceName);
			JOptionPane.showMessageDialog(null, "다운로드가 완료되었습니다.");
		}
	}

	// openBtn
	private void linkOpen() {
		if (choiceLink.isEmpty()) {
			JOptionPane.showMessageDialog(null, "[열기] 링크를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				Desktop.getDesktop().browse(new URI(choiceLink));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
}
