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
 * Collections of quarters (i.e. each 'part' can be divided in quarter)
 * 
 * @author usman
 * 
 */
@XStreamAlias("hizbs")
public class HizbsData {
	@XStreamAsAttribute
	private String alias;
	@XStreamImplicit
	private List<QuarterData> quarter;
	@XStreamOmitField
	private Map<Integer, QuarterData> quarterMap;

	public HizbsData(String alias, QuarterData... quarters) {
		this.alias = alias;
		this.quarter = Arrays.asList(quarters);
	}

	public String getAlias() {
		return alias;
	}

	public List<QuarterData> getQuarterList() {
		return Collections.unmodifiableList(quarter);
	}

	public Map<Integer, QuarterData> getQuarterMap() {
		if (quarterMap == null) {
			quarterMap = new TreeMap<Integer, QuarterData>();
			for (QuarterData _quarter : quarter) {
				quarterMap.put(_quarter.getIndex(), _quarter);
			}
			quarterMap = Collections.unmodifiableMap(quarterMap);
		}
		return quarterMap;
	}

}
