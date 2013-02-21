package info.usmans.QuranProject.application;

import info.usmans.QuranProject.controller.Loader;
import info.usmans.QuranProject.model.Aya;
import info.usmans.QuranProject.model.JuzData;
import info.usmans.QuranProject.model.PageData;
import info.usmans.QuranProject.model.Quran;
import info.usmans.QuranProject.model.QuranData;
import info.usmans.QuranProject.model.Sura;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -735356187246028596L;
	private Quran quranText = Loader.getInstance().getQuranTextForMEFont();
	private QuranData quranData = Loader.getInstance().getQuranData();
	private Font quranFont = Loader.getInstance().getMeQuranFont();
	private static final char ArabicComma = '\u060c';
	private JPanel topPanel;
	private JLabel lblSuraName;
	private JPanel bottomPanel;
	private JSpinner spinner;
	private JLabel lblPage;
	private JLabel lblOf;
	private JTextPane txtpnData;
	private StyledDocument doc;
	private Style base;
	private JLabel lblChapterNumber;
	private int[] startPages;

	public Main() {
		setBackground(Color.WHITE);
		setTitle("Quran Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		topPanel = new JPanel();
		topPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		topPanel.setLayout(new BorderLayout(0, 0));

		bottomPanel = new JPanel();

		spinner = new JSpinner();
		spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				Object obj = spinner.getModel().getValue();
				if (obj instanceof Integer) {
					// load page
					loadPage((Integer) obj);
				}
			}
		});

		lblPage = new JLabel("Page");
		spinner.setModel(new SpinnerNumberModel(1, 1, quranData.getPages()
				.getPageList().size(), 1));

		lblOf = new JLabel("of " + quranData.getPages().getPageList().size());
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
								.addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		txtpnData = new JTextPane();
		txtpnData.setBackground(new Color(238, 232, 170));
		txtpnData.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtpnData.setEditable(false);
		scrollPane.setViewportView(txtpnData);

		lblSuraName = new JLabel();
		lblSuraName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblSuraName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuraName.setFont(quranFont.deriveFont(16f));
		topPanel.add(lblSuraName, BorderLayout.WEST);
		
		lblChapterNumber = new JLabel("New label");
		topPanel.add(lblChapterNumber, BorderLayout.EAST);
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(gl_bottomPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_bottomPanel
						.createSequentialGroup()
						.addGap(180)
						.addComponent(lblPage)
						.addGap(3)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblOf).addGap(171)));
		gl_bottomPanel
				.setVerticalGroup(gl_bottomPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_bottomPanel
										.createSequentialGroup()
										.addContainerGap(16, Short.MAX_VALUE)
										.addGroup(
												gl_bottomPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																spinner,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPage)
														.addComponent(lblOf))));
		bottomPanel.setLayout(gl_bottomPanel);
		getContentPane().setLayout(groupLayout);
		
		initializeStartPagesofChapters();
		loadPage(1);

	}

	/**
	 * Add ayas to be displayed on a page
	 * 
	 * @param page
	 * @return
	 */
	private void loadPage(int pageNum) {
		initDocStyles();
		StringBuilder suraLabelText = new StringBuilder();
		
		int totalPages = quranData.getPages().getPageList().size();
		if (pageNum <= 0 || pageNum > totalPages) {
			throw new IllegalArgumentException("Invalid Page Number ["
					+ pageNum + "] not in range [" + "1-" + totalPages + "]");
		}
		// StringBuilder pageContents = new StringBuilder();

		PageData page = quranData.getPages().getPageMap().get(pageNum);
		int currentPageStartSura = page.getSura();
		int currentPageStartAya = page.getAya();
		Sura currentPageSura = quranText.getSuraMap().get(currentPageStartSura);

		//is it time to update chapter number in its label?		
		this.lblChapterNumber.setText(String.valueOf(getChapterOfPage(page.getIndex())));

		
		// special rule for page 1 - Surah Fatiha
		if (pageNum == 1) {
			try {
				int offset = doc.getLength();
				//add Bismillah
				doc.insertString(doc.getLength(), currentPageSura.getAyaMap()
						.get(1).getText()
						+ "\n", doc.getStyle("BismillahStyle"));
				doc.setParagraphAttributes(offset, doc.getLength()-offset, doc.getStyle("BismillahStyle"), false);

				
				for (int i = 2; i <= 7; i++) {
					Aya aya = currentPageSura.getAyaMap().get(i);
					StringBuilder sb = new StringBuilder();
					sb.append(aya.getText()).append(getArabicAyaNumbering(i));
					doc.insertString(doc.getLength(), sb.toString(), base);
				}
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			suraLabelText.append(currentPageSura.getName());
			
		} else if (pageNum < totalPages) {
			// logic for all other pages, except last page
			PageData nextPage = quranData.getPages().getPageMap()
					.get(pageNum + 1);
			int nextPageStartSura = nextPage.getSura();
			int nextPageStartAya = nextPage.getAya();

			// page contains text from same sura
			if (currentPageStartSura == nextPageStartSura) {
				suraLabelText.append(currentPageSura.getName());
				for (int i = currentPageStartAya; i < nextPageStartAya; i++) {
					updateAyaText(currentPageSura, i);
				}
			} else if (currentPageStartSura < nextPageStartSura) {
				// page either ends sura on this page, or contain multiple
				// suras.

				// add rest of ayas of current sura
				for (int i = currentPageStartAya; i <= currentPageSura
						.getAyaMap().size(); i++) {
					updateAyaText(currentPageSura, i);
				}
				suraLabelText.append(currentPageSura.getName());

				// add other suras on current page (if required)
				for (int i = currentPageStartSura + 1; i < nextPageStartSura; i++) {
					Sura otherSura = quranText.getSuraMap().get(i);
					suraLabelText.append(ArabicComma).append(otherSura.getName());
					for (int j = 1; j <= otherSura.getAyaMap().size(); j++) {
						if (j == 1) {
							// start of Bismillah in middle of page
							addLineFeed();
						}
						updateAyaText(otherSura, j);
					}
				}

				// does nextPageStartSura starts on this page?
				if (nextPageStartAya > 1) {
					Sura nextPageSura = quranText.getSuraMap().get(
							nextPageStartSura);
					suraLabelText.append(ArabicComma).append(nextPageSura.getName());
					for (int i = 1; i < nextPageStartAya; i++) {
						if (i == 1) {
							// start of Bismillah in middle of page
							addLineFeed();
						}
						updateAyaText(nextPageSura, i);
					}
				}

			}

		} else {
			// logic for last page
			for (int i = currentPageStartSura; i <= 114; i++) {
				Sura lastPageSura = quranText.getSuraMap().get(i);
				suraLabelText.append(lastPageSura.getName());
				if(i != 114) {
					suraLabelText.append(ArabicComma);
				}
				for (int j = 1; j <= lastPageSura.getAyaMap().size(); j++) {
					if (i > currentPageStartSura && j == 1) {
						// start of bismillah in middle of page
						addLineFeed();
					}
					updateAyaText(lastPageSura, j);
				}
			}

		}

		this.txtpnData.setCaretPosition(0);
		this.lblSuraName.setText(suraLabelText.toString());
	}
	
	private void addLineFeed() {
		try {
			doc.insertString(doc.getLength(), "\n", base);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void updateAyaText(Sura sura, int i) {
		Aya aya = sura.getAyaMap().get(i);
		try {
			if (i == 1 && aya.getBismillah() != null) {

				int offset = doc.getLength();
				doc.insertString(doc.getLength(), aya.getBismillah() + "\n",
						doc.getStyle("BismillahStyle"));
				doc.setParagraphAttributes(offset, doc.getLength()-offset, doc.getStyle("BismillahStyle"), false);

			}

			StringBuilder sb = new StringBuilder();
			sb.append(aya.getText()).append(getArabicAyaNumbering(i));
			doc.insertString(doc.getLength(), sb.toString(), base);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private String getArabicAyaNumbering(int number) {
		StringBuilder sb = new StringBuilder();
		int _num = number;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		while (_num > 0) {
			stack.push(_num % 10);
			_num = _num / 10;
		}
		while (!stack.isEmpty()) {
			int unicode = stack.pop() + 0x660;
			sb.append(Character.toChars(unicode));
		}
		return sb.toString();
	}

	private void initDocStyles() {
		doc = new DefaultStyledDocument();
		base = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontFamily(base, "me_quran");
		StyleConstants.setFontSize(base, 24);
		
		Style bismillahStyle = doc.addStyle("BismillahStyle", base);
		StyleConstants.setBold(bismillahStyle, true);
		StyleConstants.setSpaceBelow(bismillahStyle, 10f);
		StyleConstants.setAlignment(bismillahStyle, StyleConstants.ALIGN_CENTER);
		
		this.txtpnData.setStyledDocument(doc);
	}
	
	private void initializeStartPagesofChapters() {
		startPages = new int[30];
		int i=0;
		for(JuzData jd: quranData.getJuzs().getJuzList()) {
			PageData pd = quranData.getPages().getSuraAyaPageMap().get(String.valueOf(jd.getSura()) + String.valueOf(jd.getAya()));
			System.out.println(pd.getIndex() +":"+String.valueOf(jd.getSura()) +","+ String.valueOf(jd.getAya()));
			startPages[i++] = pd.getIndex();
		}
		Arrays.sort(startPages);
	}
	
	private int getChapterOfPage(int page) {
		for(int j=29; j>=0;j--) {
			if(page >= startPages[j]) {
				return j+1;
			}
		}
		return 0; //not meant to happen
	}

	public static void main(String a[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main m = new Main();
				m.pack();
				m.setVisible(true);
			}
		});
	}
}
