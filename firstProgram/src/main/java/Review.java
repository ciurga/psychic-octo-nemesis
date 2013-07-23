

public class Review {
	private String beer_name;
	private long beer_beerId;
	private long beer_brewerId;
	private float beer_ABV;
	private String beer_style;
	private String review_appereance;
	private String review_aroma;
	private String review_palate;
	private String review_taste;
	private String review_overall;
	private String review_time;			// forse questo può essere trattato come Time type
	private String review_profilName;
	private String review_text;
	

	public Review(String beer_name, long beer_beerId, long beer_brewerId,
			float beer_ABV, String beer_style, String review_appereance,
			String review_aroma, String review_palate, String review_taste,
			String review_overall, String review_time,
			String review_profilName, String review_text) {
		super();
		this.beer_name = beer_name;
		this.beer_beerId = beer_beerId;
		this.beer_brewerId = beer_brewerId;
		this.beer_ABV = beer_ABV;
		this.beer_style = beer_style;
		this.review_appereance = review_appereance;
		this.review_aroma = review_aroma;
		this.review_palate = review_palate;
		this.review_taste = review_taste;
		this.review_overall = review_overall;
		this.review_time = review_time;
		this.review_profilName = review_profilName;
		this.review_text = review_text;
	}




	public Review() {
		this.beer_name = "";
		this.beer_beerId = -1;
		this.beer_brewerId = -1;
		this.beer_ABV = -1;
		this.beer_style = "";
		this.review_appereance = "";
		this.review_aroma = "";
		this.review_palate = "";
		this.review_taste = "";
		this.review_overall = "";
		this.review_time = "";
		this.review_profilName = "";
		this.review_text = "";
	}




	public String getBeer_name() {
		return beer_name;
	}
	public void setBeer_name(String beer_name) {
		this.beer_name = beer_name;
	}
	public long getBeer_beerId() {
		return beer_beerId;
	}
	public void setBeer_beerId(long beer_beerId) {
		this.beer_beerId = beer_beerId;
	}
	public long getBeer_brewerId() {
		return beer_brewerId;
	}
	public void setBeer_brewerId(long beer_brewerId) {
		this.beer_brewerId = beer_brewerId;
	}
	public float getBeer_ABV() {
		return beer_ABV;
	}
	public void setBeer_ABV(float beer_ABV) {
		this.beer_ABV = beer_ABV;
	}
	public String getBeer_style() {
		return beer_style;
	}
	public void setBeer_style(String beer_style) {
		this.beer_style = beer_style;
	}
	public String getReview_aroma() {
		return review_aroma;
	}
	public void setReview_aroma(String review_aroma) {
		this.review_aroma = review_aroma;
	}
	public String getReview_appereance() {
		return review_appereance;
	}
	public void setReview_appereance(String review_appereance) {
		this.review_appereance = review_appereance;
	}
	public String getReview_palate() {
		return review_palate;
	}
	public void setReview_palate(String review_palate) {
		this.review_palate = review_palate;
	}
	public String getReview_taste() {
		return review_taste;
	}
	public void setReview_taste(String review_taste) {
		this.review_taste = review_taste;
	}
	public String getReview_overall() {
		return review_overall;
	}
	public void setReview_overall(String review_overall) {
		this.review_overall = review_overall;
	}
	public String getReview_time() {
		return review_time;
	}
	public void setReview_time(String review_time) {
		this.review_time = review_time;
	}
	public String getReview_profilName() {
		return review_profilName;
	}
	public void setReview_profilName(String review_profilName) {
		this.review_profilName = review_profilName;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	
	
}
