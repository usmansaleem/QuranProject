package info.usmans.QuranProject.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("aya")
public class Aya {
	@XStreamAsAttribute
	private int index;
	@XStreamAsAttribute
	private String text;
	@XStreamAsAttribute
	private String bismillah;

	public int getIndex() {
		return index;
	}

	public String getText() {
		return text;
	}

	public String getBismillah() {
		return bismillah;
	}
	
	@Override
	public String toString() {
		return text;
	}

}
