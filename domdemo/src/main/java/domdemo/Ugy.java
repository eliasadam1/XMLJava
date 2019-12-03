package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Ugy {
	private int ugyszam;
	private String ugyfel;
	private String tipus;
	private String megjegy;
	
	private Ugyfel ugyfelsz;

	
	
	public int getUgyszam() {
		return ugyszam;
	}

	public void setUgyszam(int ugyszam) {
		this.ugyszam = ugyszam;
	}

	public String getUgyfel() {
		return ugyfel;
	}

	public void setUgyfel(String ugyfel) {
		this.ugyfel = ugyfel;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getMegjegy() {
		return megjegy;
	}

	public void setMegjegy(String megjegy) {
		this.megjegy = megjegy;
	}
	
	public Ugyfel getUgyfelsz() {
		return ugyfelsz;
	}

	public void setUgyfelsz(Ugyfel ugyfelsz) {
		this.ugyfelsz = ugyfelsz;
	}

	public static Ugy create(Node node) {
		Ugy ugy = new Ugy();

		Element element = (Element) node;
		ugy.ugyszam = Integer.parseInt(element.getAttribute("ugyszam"));
		ugy.tipus = element.getAttribute("tipus");
		ugy.megjegy = element.getAttribute("megjegy");
		ugy.ugyfel = element.getAttribute("ugyfel");
		return ugy; 
	}

}
