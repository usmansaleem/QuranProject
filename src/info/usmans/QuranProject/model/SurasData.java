package info.usmans.QuranProject.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Collection of Suras (chapters) meta data.
 * 
 * @author usman
 * 
 */
@XStreamAlias("suras")
public class SurasData {
	@XStreamAsAttribute
	private String alias;

	@XStreamImplicit
	private List<SuraData> sura;

	@XStreamOmitField
	private Map<Integer, SuraData> suraMap = null;

	public SurasData(String alias, SuraData... suras) {
		super();
		this.alias = alias;
		this.sura = Arrays.asList(suras);
	}

	public String getAlias() {
		return this.alias;
	}

	public List<SuraData> getSuraList() {
		return Collections.unmodifiableList(sura);
	}

	public Map<Integer, SuraData> getSuraMap() {
		if (suraMap == null) {
			suraMap = new TreeMap<>();
			for (SuraData _sura : this.sura) {
				suraMap.put(_sura.getIndex(), _sura);
			}
			suraMap = Collections.unmodifiableMap(suraMap);
		}

		return suraMap;

	}

}
