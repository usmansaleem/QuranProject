package info.usmans.QuranProject.controller;

public enum QuranTextType {
	USMANI_SPECIALTANWEEN {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/qurantext/quran-uthmani-different-tanween.xml";
		}
	},
	USMANI_STANDARD {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/qurantext/quran-uthmani.xml";
		}
	},
	SIMPLE {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/qurantext/quran-simple-enhanced.xml";
		}
	};

	public abstract String getResourcePath();

}
