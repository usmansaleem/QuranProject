package info.usmans.QuranProject.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("manzil")
public class ManzilData {
	@XStreamAsAttribute
	private int index;
	@XStreamAsAttribute
	private int sura;
	@XStreamAsAttribute
	private int aya;

	public ManzilData(int index, int sura, int aya) {
		super();
		this.index = index;
		this.sura = sura;
		this.aya = aya;
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
}
