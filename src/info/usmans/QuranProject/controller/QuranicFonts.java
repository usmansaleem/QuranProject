package info.usmans.QuranProject.controller;

public enum QuranicFonts {

	FONT_ME_QURAN {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/fonts/me_quran_volt_newmet.ttf";
		}

		@Override
		public float getDefaultSize() {
			return 24f;
		}
	},
	FONT_HUSSAINI_NASTALEEQ {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/fonts/hussaini-nastaleeq.ttf";
		}
		
		@Override
		public float getDefaultSize() {
			return 14f;
		}

	},
	FONT_KFGQPC_TN {
		@Override
		public String getResourcePath() {
			return "/info/usmans/QuranProject/resources/fonts/UthmanTN1Ver10.otf";
		}
		
		@Override
		public float getDefaultSize() {
			return 18f;
		}
	};

	public abstract String getResourcePath();

	public abstract float getDefaultSize();

}
