package info.usmans.QuranProject.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

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
	private Font me_quran_font;
	private Font hussaini_nastaleeq_font;
	private Font KFGQPC_font;
	private Quran quranText[] = new Quran[3];

	/**
	 * tanween to be used by me_quran font.
	 */
	private static final String quran_uthmani_different_tanween_xml_path = "../resources/qurantext/quran-uthmani-different-tanween.xml";
	private static final String quran_uthmani_standard_xml_path = "../resources/qurantext/quran-uthmani.xml";
	private static final String quran_simple_enhanced_xml_path = "../resources/qurantext/quran-simple-enhanced.xml";
	private static final String quran_data_xml_path = "../resources/qurantext/quran-data.xml";
	private static final String me_quran_font_path = "../resources/fonts/me_quran_volt_newmet.ttf";
	private static final String hussaini_nastaleeq_font_path = "../resources/fonts/hussaini-nastaleeq.ttf";
	private static final String KFGQPC_font_path = "../resources/fonts/UthmanTN1Ver10.otf";

	private static Loader loader = new Loader();

	private Loader() {
		loadQuranData();
		loadQuranFonts();
	}

	public static Loader getInstance() {
		return loader;
	}

	public QuranData getQuranData() {
		return this.quranData;
	}

	public Font getMeQuranFont() {
		return this.me_quran_font;
	}

	public Font getHussainiNastaleeqFont() {
		return this.hussaini_nastaleeq_font;
	}

	public Font getKFGQPCHafsFont() {
		return this.KFGQPC_font;
	}

	public Quran getQuranText(QuranTextType type) {

		switch (type) {
		case USMANI_SPECIALTANWEEN:
			if (quranText[0] == null) {
				quranText[0] = loadQuranTextFromXML(quran_uthmani_different_tanween_xml_path);

			}
			return quranText[0];
		case USMANI_STANDARD:
			if (quranText[1] == null) {
				quranText[1] = loadQuranTextFromXML(quran_uthmani_standard_xml_path);
			}
			return quranText[1];
		case SIMPLE:
			if (quranText[2] == null) {
				quranText[2] = loadQuranTextFromXML(quran_simple_enhanced_xml_path);
			}
			return quranText[2];
		}
		return null;
	}

	private Quran loadQuranTextFromXML(String path) {
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(Quran.class);
		xstream.autodetectAnnotations(true);

		try (InputStream is = Loader.class.getResourceAsStream(path)) {
			return (Quran) xstream.fromXML(is);

		} catch (IOException ioe) {
			System.err.println("Error loading Quran text: " + ioe.getMessage());
		}
		return null;
	}

	private void loadQuranData() {
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(QuranData.class);
		xstream.autodetectAnnotations(true);
		QuranData _quranData = null;
		try (InputStream is = Loader.class
				.getResourceAsStream(quran_data_xml_path)) {
			_quranData = (QuranData) xstream.fromXML(is);

		} catch (IOException ioe) {
			System.err.println("Error loading Quran Data: " + ioe.getMessage());
		}
		assert _quranData != null;
		this.quranData = _quranData;
	}

	/**
	 * Loads me_quran font
	 * 
	 * @return
	 */
	private void loadQuranFonts() {
		Font f = null;
		try (InputStream is = Loader.class
				.getResourceAsStream(me_quran_font_path)) {
			f = Font.createFont(Font.TRUETYPE_FONT, is);
			this.me_quran_font = f.deriveFont(24f); // have to specify float
			// also register font with system
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
		} catch (IOException | FontFormatException e) {
			System.err.println("Error loading and creating custom font: "
					+ e.getMessage());
		}

		try (InputStream is = Loader.class
				.getResourceAsStream(hussaini_nastaleeq_font_path)) {
			f = Font.createFont(Font.TRUETYPE_FONT, is);
			this.hussaini_nastaleeq_font = f.deriveFont(14f); // have to specify
																// float
			// also register font with system
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
		} catch (IOException | FontFormatException e) {
			System.err.println("Error loading and creating custom font: "
					+ e.getMessage());
		}

		try (InputStream is = Loader.class
				.getResourceAsStream(KFGQPC_font_path)) {
			f = Font.createFont(Font.TRUETYPE_FONT, is);
			this.KFGQPC_font = f.deriveFont(18f); // have to specify
													// float
			// also register font with system
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
		} catch (IOException | FontFormatException e) {
			System.err.println("Error loading and creating custom font: "
					+ e.getMessage());
		}

	}
}
