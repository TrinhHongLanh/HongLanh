package taofile;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class ReadFile extends JFrame {

	private JPanel contentPane;
	protected JTextArea taContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadFile frame = new ReadFile();
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
	public ReadFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(141, 11, 268, 166);
		contentPane.add(textArea);
		
		JButton btOpen = new JButton("Open");
		btOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				FileReader fr = null;
				BufferedReader br = null;
				String s="";
				try {
					fc = new JFileChooser();
					int openDialog = fc.showOpenDialog(null);
					if(openDialog==JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						fr = new FileReader(path);
						br = new BufferedReader(fr);
						while((s=br.readLine())!=null) {
							textArea.append(s+"\n");
						}
					}
					br.close();
					fr.close();
				}
					catch (Exception e2) {
					// TODO: handle exception
						System.out.print("Error"+e2.getMessage());
				}
			}
		});
		btOpen.setBounds(10, 204, 89, 23);
		contentPane.add(btOpen);
		
		JButton btSaveAs = new JButton("Save AS");
		btSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				FileWriter fw=null;
				BufferedWriter bw=null;
				try {
					fc = new JFileChooser();
					int openDialog = fc.showOpenDialog(null);
					if(openDialog==JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						fw = new FileWriter(path);
						bw = new BufferedWriter(fw);
						bw.write(textArea.getText());
						bw.flush();
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.print("Error"+e2.getMessage());
				}
			}
		});
		btSaveAs.setBounds(109, 204, 89, 23);
		contentPane.add(btSaveAs);
		
		JButton btdelete = new JButton("Delete");
		btdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {		
						JFileChooser fc = new JFileChooser();
						int openDialog = fc.showOpenDialog(null);
						if(openDialog==JFileChooser.APPROVE_OPTION) {
							String path = fc.getSelectedFile().getAbsolutePath();
							File xoaFile = new File(path);
							if(xoaFile.delete()) {
								JOptionPane.showMessageDialog(null, "Xoá file thành công !");
							} else {
								JOptionPane.showMessageDialog(null, "Xoá file thất bại !");
							}
						}	
			} catch (Exception ex) {
				System.out.print(ex.getMessage());
			}
			}
		});
		btdelete.setBounds(220, 204, 89, 23);
		contentPane.add(btdelete);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(319, 204, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 108, 166);
		contentPane.add(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setColumnHeaderView(tree);
	}
}
