package info.usmans.QuranProject.controller;

public enum QuranTextType {
	USMANI_SPECIALTANWEEN {
		@Override
		public String getResourcePath() {
			return "../resources/qurantext/quran-uthmani-different-tanween.xml";
		}
	},
	USMANI_STANDARD {
		@Override
		public String getResourcePath() {
			return "../resources/qurantext/quran-uthmani.xml";
		}
	},
	SIMPLE {
		@Override
		public String getResourcePath() {
			return "../resources/qurantext/quran-simple-enhanced.xml";
		}
	};

	public abstract String getResourcePath();

}
