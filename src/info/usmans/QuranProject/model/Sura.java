package info.usmans.QuranProject.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("sura")
public class Sura {
	@XStreamAsAttribute
	private int index;
	@XStreamAsAttribute
	private String name;

	@XStreamImplicit
	private List<Aya> aya;

	@XStreamOmitField
	private Map<Integer, Aya> ayaMap = null;

	public Sura(int index, String name, Aya... ayas) {
		this.index = index;
		this.name = name;
		this.aya = Arrays.asList(ayas);
	}

	public List<Aya> getAyaList() {
		return aya;
	}

	public Map<Integer, Aya> getAyaMap() {
		// lazy initialization of hashtable for quick access of ayas
		if (ayaMap == null) {
			ayaMap = new TreeMap<>();
			for (Aya _aya : this.aya) {
				ayaMap.put(_aya.getIndex(), _aya);
			}
		}
		return ayaMap;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
