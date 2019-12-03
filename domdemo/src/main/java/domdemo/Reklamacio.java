package domdemo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Reklamacio {

	 private int szam;
	 private int ugyid;
	 private String operatorid;
	 
	 private Ugy ugy;
	 private Operator operator;
	 
	 
	 public static Reklamacio create(Node node) {
		 Reklamacio reklamacio = new Reklamacio();

	        Element element = (Element) node;
	        reklamacio.szam = Integer.parseInt(element.getAttribute("szam"));
	        reklamacio.ugyid = Integer.parseInt(element.getAttribute("ugyszam"));
	        reklamacio.operatorid = element.getAttribute("operator");
	        return reklamacio;
	    }
	 
	 
	public int getSzam() {
		return szam;
	}
	public void setSzam(int szam) {
		this.szam = szam;
	}
	public int getUgyid() {
		return ugyid;
	}
	public void setUgyid(int ugy) {
		this.ugyid = ugy;
	}
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operator) {
		this.operatorid = operator;
	}


	public Ugy getUgy() {
		return ugy;
	}


	public void setUgy(Ugy ugy) {
		this.ugy = ugy;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	
	
	 
	 
}
