package info.usmans.QuranProject.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import info.usmans.QuranProject.model.Quran;
import info.usmans.QuranProject.model.QuranData;

/**
 * A singelton loader of Quran and QuranData from XML files.
 * 
 * @author usman
 * 
 */
public class Loader {
	private QuranData quranData;

	private EnumMap<QuranTextType, Quran> quranTextMap = new EnumMap<QuranTextType, Quran>(
			QuranTextType.class);
	private EnumMap<QuranTranslationID, Quran> quranTranslationMap = new EnumMap<QuranTranslationID, Quran>(
			QuranTranslationID.class);
	private EnumMap<QuranicFonts, Font> quranicFonts = new EnumMap<QuranicFonts, Font>(
			QuranicFonts.class);

	private static final String quran_data_xml_path = "../resources/qurantext/quran-data.xml";

	/**
	 * Singleten instance
	 */
	private static Loader loader = new Loader();

	/**
	 * Private constructor. Use getInstance to get the instance of this class
	 * 
	 * The constructor pre-initializes Quran Meta Data and Quranic Fonts. Quran
	 * text and Quran translations are lazy loaded
	 */
	private Loader() {
		loadQuranData();
		loadQuranFonts();
	}

	/**
	 * Singelton getInstance
	 * 
	 * @return Loader singleton reference
	 */
	public static Loader getInstance() {
		return loader;
	}

	/**
	 * Returns Quran Meta Data
	 * 
	 * @return QuranData representing Quran Meta data
	 */
	public QuranData getQuranData() {
		return this.quranData;
	}

	/**
	 * Although we register the fonts so they should be available by their
	 * family name, exposing following method if a direct reference of Font is
	 * require.
	 * 
	 * @return Font
	 */
	public Font getQuranicFont(QuranicFonts qf) {
		return this.quranicFonts.get(qf);
	}

	/**
	 * Lazy-load Quran text.
	 * 
	 * @param type
	 *            QuranTextType
	 * @return Quran representing Quran text
	 */
	public Quran getQuranText(QuranTextType type) {
		Quran _quran = this.quranTextMap.get(type);
		if (_quran == null) {
			XStream xstream = new XStream(new StaxDriver());
			xstream.processAnnotations(Quran.class);
			xstream.autodetectAnnotations(true);
			InputStream is = null;
			is = Loader.class.getResourceAsStream(type.getResourcePath());
			if (is == null) {
				System.err
						.println("Unable to acquire InputStream for Quran Text: "
								+ type.getResourcePath());
				return null;
			}
			_quran = (Quran) xstream.fromXML(is);
			this.quranTextMap.put(type, _quran);
			try {
				is.close();
			} catch (IOException e) {
				System.err
						.println("Unable to close InputStream for Quran Text: "
								+ e.getMessage());
			}
		}

		return _quran;
	}

	/**
	 * Load Quran Translation text and add it to local Map for faster retrieval
	 * later on.
	 * 
	 * @param translationID
	 *            QuranTranslationID for which translation is required
	 * @return Quran containing translation
	 */
	public Quran getQuranTranslation(QuranTranslationID translationID) {
		Quran _quran = quranTranslationMap.get(translationID);
		if (_quran == null) {
			XStream xstream = new XStream(new StaxDriver());
			xstream.processAnnotations(Quran.class);
			xstream.autodetectAnnotations(true);
			InputStream is = null;
			is = Loader.class.getResourceAsStream(translationID
					.getResourcePath());
			if (is == null) {
				System.err
						.println("Unable to acquire InputStream for Quran Translation: "
								+ translationID.getResourcePath());
				return null;
			}
			_quran = (Quran) xstream.fromXML(is);
			quranTranslationMap.put(translationID, _quran);
			try {
				is.close();
			} catch (IOException e) {
				System.err
						.println("Unable to close InputStream for Quran Translation: "
								+ e.getMessage());
			}
		}

		return _quran;
	}

	/**
	 * Load Quran Meta Data
	 */
	private void loadQuranData() {
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(QuranData.class);
		xstream.autodetectAnnotations(true);
		InputStream is = null;
		is = Loader.class.getResourceAsStream(quran_data_xml_path);
		if (is != null) {
			this.quranData = (QuranData) xstream.fromXML(is);
			try {
				is.close();
			} catch (IOException e) {
				System.err
						.println("Unable to close InputStream for Quran Data resource: "
								+ e.getMessage());
			}
		} else {
			System.err
					.println("Unable to acquire InputStream for Quran Data resource: "
							+ quran_data_xml_path);
		}
	}

	/**
	 * Loads Quranic fonts that we are bundeling
	 * 
	 */
	private void loadQuranFonts() {

		for (QuranicFonts qf : QuranicFonts.values()) {
			InputStream is = null;
			is = Loader.class.getResourceAsStream(qf.getResourcePath());
			if (is != null) {
				Font f = null;
				try {
					f = Font.createFont(Font.TRUETYPE_FONT, is);
				} catch (FontFormatException e) {
					System.out
							.println("Error Creating Font: " + e.getMessage());
				} catch (IOException e) {
					System.out
							.println("Error Creating Font: " + e.getMessage());
				}
				if (f != null) {
					this.quranicFonts
							.put(qf, f.deriveFont(qf.getDefaultSize()));
					GraphicsEnvironment.getLocalGraphicsEnvironment()
							.registerFont(f);
				}

				try {
					is.close();
				} catch (IOException e) {
					System.err
							.println("Error closing InputStream for font resources: "
									+ e.getMessage());
				}
			} else {
				System.err
						.println("Unable to acquire InputStream for font resource: "
								+ qf.getResourcePath());
			}
		}
	}
}
