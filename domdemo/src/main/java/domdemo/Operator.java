package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Operator {
	 private String felh;
	 private String nev;
	 private String szgep;

	    public static Operator create(Node node) {
	    	Operator operator = new Operator();

	        Element element = (Element) node;
	        operator.felh = element.getAttribute("felh");
	        operator.nev = element.getAttribute("nev");
	        operator.szgep = element.getAttribute("szgep");
	        return operator;
	    }

	    public String getFelh() {
	        return felh;
	    }

	    public void setFelh(String felh) {
	        this.felh = felh;
	    }

	    public String getNev() {
	        return nev;
	    }

	    public void setNev(String nev) {
	        this.nev = nev;
	    }
	    
	    public String getSzgep() {
	        return szgep;
	    }

	    public void setSzgep(String szgep) {
	        this.nev = szgep;
	    }


}
