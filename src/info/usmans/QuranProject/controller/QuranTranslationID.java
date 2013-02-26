package info.usmans.QuranProject.controller;

public enum QuranTranslationID {

	UR_MAUDUDI {
		@Override
		public String getResourcePath() {
			return "../resources/trans/ur.maududi.xml";
		}
	},
	UR_AHMED_RAZA_KHAN {

		@Override
		public String getResourcePath() {
			return "../resources/trans/ur.kanzuliman.xml";
		}

	},
	UR_JALANDHRY {

		@Override
		public String getResourcePath() {
			return "../resources/trans/ur.jalandhry.xml";
		}
		
	};

	public abstract String getResourcePath();

	@Override
	public String toString() {
		switch (this) {
		case UR_MAUDUDI:
			return "ابوالاعلی مودودی";
		case UR_AHMED_RAZA_KHAN:
			return "احمد رضا خان";
		case UR_JALANDHRY:
			return "جالندہری";

		}
		return super.toString();
	}

}
