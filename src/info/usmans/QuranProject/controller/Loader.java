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
	private Quran meFontQuran;
	private Quran otherFontsQuran;
	private QuranData quranData;
	private Font me_quran_font;

	/**
	 * tanween to be used by me_quran font.
	 */
	private static final String quran_uthmani_different_tanween_xml_path = "../resources/quran-uthmani-different-tanween.xml";
	private static final String quran_uthmani_normal_tanween_xml_path = "../resources/quran-uthmani.xml";
	private static final String quran_data_xml_path = "../resources/quran-data.xml";
	private static final String me_quran_font_path = "../resources/me_quran_volt_newmet.ttf";

	private static Loader loader = new Loader();

	private Loader() {
		this.meFontQuran = loadQuranText(true);
		this.otherFontsQuran = loadQuranText(false);
		loadQuranData();
		loadQuranFonts();
	}

	public static Loader getInstance() {
		return loader;
	}

	public Quran getQuranTextForMEFont() {
		return this.meFontQuran;
	}

	public Quran getQuranTextForOtherFonts() {
		return this.otherFontsQuran;
	}

	public QuranData getQuranData() {
		return this.quranData;
	}

	public Font getMeQuranFont() {
		return this.me_quran_font;
	}

	private Quran loadQuranText(boolean differentTanween) {
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(Quran.class);
		xstream.autodetectAnnotations(true);
		try (InputStream is = Loader.class
				.getResourceAsStream(differentTanween ? quran_uthmani_different_tanween_xml_path
						: quran_uthmani_normal_tanween_xml_path)) {
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

	}
}
