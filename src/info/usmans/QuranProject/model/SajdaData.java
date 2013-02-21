package info.usmans.QuranProject.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("sajda")
public class SajdaData {
	@XStreamAsAttribute
	private int index;
	@XStreamAsAttribute
	private int sura;
	@XStreamAsAttribute
	private int aya;
	@XStreamAsAttribute
	private String type;

	public SajdaData(int index, int sura, int aya, String type) {
		super();
		this.index = index;
		this.sura = sura;
		this.aya = aya;
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public int getSura() {
		return sura;
	}

	public int getAya() {
		return aya;
	}

	public String getType() {
		return type;
	}

}
