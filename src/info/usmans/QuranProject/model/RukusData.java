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
 * Collections of Rukus or sections
 * 
 * @author usman
 * 
 */
@XStreamAlias("rukus")
public class RukusData {
	@XStreamAsAttribute
	private String alias;
	@XStreamImplicit
	private List<RukuData> ruku;
	@XStreamOmitField
	private Map<Integer, RukuData> rukuMap;

	public RukusData(String alias, RukuData... rukus) {
		this.alias = alias;
		this.ruku = Arrays.asList(rukus);
	}

	public String getAlias() {
		return alias;
	}

	public List<RukuData> getRukuList() {
		return Collections.unmodifiableList(ruku);
	}

	public Map<Integer, RukuData> getRukuMap() {
		if (rukuMap == null) {
			rukuMap = new TreeMap<>();
			for (RukuData _ruku : ruku) {
				rukuMap.put(_ruku.getIndex(), _ruku);
			}
			rukuMap = Collections.unmodifiableMap(rukuMap);
		}
		return rukuMap;
	}
}
