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
 * Collection of 'Juz' or 'part' or 'سپارہ'
 * 
 * @author usman
 * 
 */
@XStreamAlias("juzs")
public class JuzsData {
	@XStreamAsAttribute
	private String alias;
	@XStreamImplicit
	private List<JuzData> juz;
	@XStreamOmitField
	private Map<Integer, JuzData> juzMap;
	@XStreamOmitField
	private Map<String, JuzData> suraAyaJuzMap;

	public JuzsData(String alias, JuzData... juzs) {
		this.alias = alias;
		this.juz = Arrays.asList(juzs);
	}

	public String getAlias() {
		return alias;
	}

	public List<JuzData> getJuzList() {
		return Collections.unmodifiableList(juz);
	}

	public Map<Integer, JuzData> getJuzMap() {
		if (juzMap == null) {
			juzMap = new TreeMap<Integer, JuzData>();
			for (JuzData _juz : juz) {
				juzMap.put(_juz.getIndex(), _juz);
			}
			juzMap = Collections.unmodifiableMap(juzMap);
		}
		return juzMap;
	}

	/**
	 * Helper method to determine display of chapter number using sura, aya
	 * combination
	 * 
	 * @return Map with String combination of sura and aya as key, chapter as
	 *         value
	 */
	public Map<String, JuzData> getSuraAyaJuzMap() {
		if (suraAyaJuzMap == null) {
			suraAyaJuzMap = new TreeMap<String, JuzData>();
			for (JuzData _juz : juz) {
				suraAyaJuzMap.put(
						String.valueOf(_juz.getSura())
								+ String.valueOf(_juz.getAya()), _juz);
			}
			suraAyaJuzMap = Collections.unmodifiableMap(suraAyaJuzMap);
		}
		return suraAyaJuzMap;
	}

}
