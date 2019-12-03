package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Ugyfel {

	private String ugyfelszam;
	private String nev;
	
	
	
	
	public String getUgyfelszam() {
		return ugyfelszam;
	}
	public void setUgyfelszam(String ugyfelszam) {
		this.ugyfelszam = ugyfelszam;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	

	public static Ugyfel create(Node node) {
		Ugyfel ugyfel = new Ugyfel();

		Element element = (Element) node;
		ugyfel.nev = element.getAttribute("nev");
		ugyfel.ugyfelszam = element.getAttribute("ugyfelszam");
		return ugyfel;
	}
	
	
}
