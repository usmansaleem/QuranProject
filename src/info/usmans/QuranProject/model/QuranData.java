package info.usmans.QuranProject.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Quran meta-data. This will be populated from quran-data.xml
 * 
 * @author usman
 * 
 */
@XStreamAlias("quran")
public class QuranData {
	@XStreamAsAttribute
	private String type;

	@XStreamAsAttribute
	private String version;

	@XStreamAsAttribute
	private String copyright;

	@XStreamAsAttribute
	private String license;

	@XStreamAlias("suras")
	private SurasData suras;

	@XStreamAlias("juzs")
	private JuzsData juzs;

	@XStreamAlias("hizbs")
	private HizbsData hizbs;

	@XStreamAlias("manzils")
	private ManzilsData manzils;

	@XStreamAlias("rukus")
	private RukusData rukus;

	@XStreamAlias("pages")
	private PagesData pages;

	@XStreamAlias("sajdas")
	private SajdasData sajdas;

	public QuranData(String type, String version, String copyright,
			String license, SurasData suras, JuzsData juzs, HizbsData hizbs,
			ManzilsData manzils, RukusData rukus, PagesData pages, SajdasData sajdas) {
		super();
		this.type = type;
		this.version = version;
		this.copyright = copyright;
		this.license = license;
		this.suras = suras;
		this.juzs = juzs;
		this.hizbs = hizbs;
		this.manzils = manzils;
		this.rukus = rukus;
		this.pages = pages;
		this.sajdas = sajdas;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getLicense() {
		return license;
	}

	public SurasData getSuras() {
		return suras;
	}

	public JuzsData getJuzs() {
		return juzs;
	}

	public HizbsData getHizbs() {
		return hizbs;
	}

	public ManzilsData getManzils() {
		return manzils;
	}

	public RukusData getRukus() {
		return rukus;
	}

	public PagesData getPages() {
		return pages;
	}

	public SajdasData getSajdas() {
		return sajdas;
	}

}
