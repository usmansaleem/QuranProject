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
 * Collection of manzil. Manzil group Quran text in seven sections.
 * 
 * @author usman
 * 
 */
@XStreamAlias("manzils")
public class ManzilsData {
	@XStreamAsAttribute
	private String alias;
	@XStreamImplicit
	private List<ManzilData> manzil;
	@XStreamOmitField
	private Map<Integer, ManzilData> manzilMap;

	public ManzilsData(String alias, ManzilData... manzils) {
		this.alias = alias;
		this.manzil = Arrays.asList(manzils);
	}

	public String getAlias() {
		return alias;
	}

	public List<ManzilData> getManzilList() {
		return Collections.unmodifiableList(manzil);
	}

	public Map<Integer, ManzilData> getManzilMap() {
		if (manzilMap == null) {
			manzilMap = new TreeMap<Integer, ManzilData>();
			for (ManzilData _manzil : manzil) {
				manzilMap.put(_manzil.getIndex(), _manzil);
			}
			manzilMap = Collections.unmodifiableMap(manzilMap);
		}
		return manzilMap;
	}
}
