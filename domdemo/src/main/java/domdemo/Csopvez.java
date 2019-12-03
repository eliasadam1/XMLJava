package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Csopvez {
	private String ugyfelszam;
	private String nev;
	
	public static Csopvez create(Node node) {
		Csopvez csopvez = new Csopvez();

		Element element = (Element) node;
		csopvez.ugyfelszam = element.getAttribute("ugyfelszam");
		csopvez.nev = element.getAttribute("nev");
		return csopvez;
	}
	
	
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

}
