package info.usmans.QuranProject.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Collection of sajdas
 * @author usman
 *
 */
@XStreamAlias("sajdas")
public class SajdasData {
	@XStreamImplicit
	private List<SajdaData> sajda;
	@XStreamOmitField
	private Map<Integer, SajdaData> sajdaMap;

	public SajdasData(SajdaData... sajdas) {
		this.sajda = Arrays.asList(sajdas);
	}

	public List<SajdaData> getSajdaList() {
		return Collections.unmodifiableList(sajda);
	}

	public Map<Integer, SajdaData> getSajdaMap() {
		if (sajdaMap == null) {
			sajdaMap = new TreeMap<Integer, SajdaData>();
			for (SajdaData _sajda : sajda) {
				sajdaMap.put(_sajda.getIndex(), _sajda);
			}
			sajdaMap = Collections.unmodifiableMap(sajdaMap);
		}
		return sajdaMap;
	}
}
