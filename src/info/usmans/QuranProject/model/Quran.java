package info.usmans.QuranProject.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("quran")
public class Quran {
	@XStreamImplicit
	private List<Sura> sura;

	@XStreamOmitField
	private Map<Integer, Sura> suraMap = null;

	public Quran(Sura... suras) {
		this.sura = Arrays.asList(suras);
	}

	public List<Sura> getSuraList() {
		return Collections.unmodifiableList(this.sura);
	}

	public Map<Integer, Sura> getSuraMap() {
		// lazy initialize hashtable for quick access of sura
		if (suraMap == null) {
			suraMap = new TreeMap<Integer, Sura>();
			for (Sura _sura : this.sura) {
				suraMap.put(_sura.getIndex(), _sura);
			}
			this.suraMap = Collections.unmodifiableMap(this.suraMap);
		}
		return this.suraMap;
	}
}
