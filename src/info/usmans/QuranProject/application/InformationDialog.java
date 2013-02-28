package info.usmans.QuranProject.application;

import info.usmans.QuranProject.controller.Loader;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.ComponentOrientation;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class InformationDialog extends JDialog {

	private static final long serialVersionUID = 7731501976033179699L;

	/**
	 * Create the dialog.
	 */
	public InformationDialog(Frame frame, boolean modal) {
		super(frame, modal);
		setTitle("Information");
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 451);
		getContentPane().setLayout(new BorderLayout());
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				tabbedPane.addTab("Details", null, panel, null);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						JTextArea textArea = new JTextArea();
						scrollPane.setViewportView(textArea);
						textArea.setEditable(false);
						textArea.setText(getReadMeContents());
						textArea.setCaretPosition(0);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InformationDialog.this.dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private String getReadMeContents() {
		InputStream is = null;
		is = Loader.class.getResourceAsStream("/info/usmans/QuranProject/resources/infos/README.md");
		if (is == null) {
			JOptionPane.showMessageDialog(this, "Unable to acquire InputStream for reading README file","null InputStream", JOptionPane.ERROR_MESSAGE);
			return "";
		}
		
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		String data = s.hasNext() ? s.next() : "";
		try {
			is.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Unable to close InputStream for README file","Error InputStream close", JOptionPane.ERROR_MESSAGE);
		}
		return data;
	}

}
