package info.usmans.QuranProject.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Meta-data related to specific Sura.
 * 
 * @author usman
 * 
 */
@XStreamAlias("sura")
public class SuraData {
	@XStreamAsAttribute
	private int index;
	/**
	 * Number of ayas
	 */
	@XStreamAsAttribute
	private int ayas;
	
	/**
	 * Starting index os aya
	 */
	@XStreamAsAttribute
	private int start;
	/**
	 * Name in Arabic
	 */
	@XStreamAsAttribute
	private String name;
	/**
	 * Name in English with Arabic pronounciation
	 */
	@XStreamAsAttribute
	private String tname;
	/**
	 * Name translated in English
	 */
	@XStreamAsAttribute
	private String ename;
	/**
	 * Where this Sura was revealed. Meccan or Medinan
	 */
	@XStreamAsAttribute
	private String type;
	/**
	 * Revelation order
	 */
	@XStreamAsAttribute
	private int order;
	/**
	 * Number of Rukus
	 */
	@XStreamAsAttribute
	private int rukus;

	public SuraData(int index, int ayas, int start, String name, String tname,
			String ename, String type, int order, int rukus) {
		super();
		this.index = index;
		this.ayas = ayas;
		this.start = start;
		this.name = name;
		this.tname = tname;
		this.ename = ename;
		this.type = type;
		this.order = order;
		this.rukus = rukus;
	}

	public int getIndex() {
		return index;
	}

	public int getAyas() {
		return ayas;
	}

	public int getStart() {
		return start;
	}

	public String getName() {
		return name;
	}

	public String getTname() {
		return tname;
	}

	public String getEname() {
		return ename;
	}

	public String getType() {
		return type;
	}

	public int getOrder() {
		return order;
	}

	public int getRukus() {
		return rukus;
	}

}
