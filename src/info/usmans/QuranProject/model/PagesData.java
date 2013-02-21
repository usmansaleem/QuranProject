package info.usmans.QuranProject.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Collection of pages.
 * 
 * @author usman
 * 
 */
@XStreamAlias("pages")
public class PagesData {
	
	@XStreamImplicit
	private List<PageData> page;
	@XStreamOmitField
	private Map<Integer, PageData> pageMap;
	@XStreamOmitField
	private Map<String, PageData> suraAyaPageMap;

	public PagesData(PageData... pages) {
		this.page = Arrays.asList(pages);
	}

	public List<PageData> getPageList() {
		return Collections.unmodifiableList(page);
	}

	public Map<Integer, PageData> getPageMap() {
		if (pageMap == null) {
			pageMap = new TreeMap<>();
			for (PageData _page : page) {
				pageMap.put(_page.getIndex(), _page);
			}
			pageMap = Collections.unmodifiableMap(pageMap);
		}
		return pageMap;
	}
	
	/**
	 * Helper method to be use to cross reference Page from Chapters etc.
	 * @return Map with combination of sura and aya as a key, and PageData as value.
	 */
	public Map<String, PageData> getSuraAyaPageMap() {
		if (suraAyaPageMap == null) {
			suraAyaPageMap = new HashMap<>();
			for (PageData _page : page) {
				suraAyaPageMap.put(String.valueOf(_page.getSura())+String.valueOf(_page.getAya()), _page);
			}
			suraAyaPageMap = Collections.unmodifiableMap(suraAyaPageMap);
		}
		return suraAyaPageMap;
	}
	
	
}
