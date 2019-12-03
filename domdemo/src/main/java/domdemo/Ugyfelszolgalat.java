package domdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Ugyfelszolgalat {
	private static final String UGYFELSZ_TAG = "ugyfelszolgalat";
	private static final String CSOPVEZ_TAG = "csopvez";
	private static final String OPERATOR_TAG = "operator";
	private static final String UGYFEL_TAG = "ugyfel";
	private static final String REKLAMACIO_TAG = "reklamacio";
	private static final String UGY_TAG = "ugy";
	
	
	private Document root;
	private List<Csopvez> csopvezs;
	private List<Operator> operators;
	private List<Ugyfel> ugyfels;
	private List<Reklamacio> reklamacios;
	private List<Ugy> ugys;
	
	private Ugyfelszolgalat(Document root, List<Csopvez> csopvezs, List<Operator> operators, List<Ugyfel> ugyfels,List<Reklamacio> reklamacios, List<Ugy> ugys) {
	        this.root = root;
	        this.csopvezs = csopvezs;
	        this.operators = operators;
	        this.ugyfels = ugyfels;
	        this.reklamacios = reklamacios;
	        this.ugys = ugys;
	        
	    }

	public static Ugyfelszolgalat create(Document document) {
		Element root = document.getDocumentElement();
		if (!root.getNodeName().equals(UGYFELSZ_TAG)) {
			throw new IllegalArgumentException("nem ügyfélszolgálatos a gyökérelem");
		}

		NodeList nodeList = root.getElementsByTagName(CSOPVEZ_TAG);
		List<Csopvez> csopvezs = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			csopvezs.add(Csopvez.create(node));
		}

		nodeList = root.getElementsByTagName(OPERATOR_TAG);
		List<Operator> operators = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			operators.add(Operator.create(node));
		}

		
		nodeList = root.getElementsByTagName(UGYFEL_TAG);
		List<Ugyfel> ugyfels = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			ugyfels.add(Ugyfel.create(node));
		}
		
		nodeList = root.getElementsByTagName(REKLAMACIO_TAG);
		List<Reklamacio> reklamacios = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			reklamacios.add(Reklamacio.create(node));
		}
		
		nodeList = root.getElementsByTagName(UGY_TAG);
		List<Ugy> ugys = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			ugys.add(Ugy.create(node));
		}
		

		Ugyfelszolgalat ugyfelszolgalat = new Ugyfelszolgalat(document, csopvezs, operators,  ugyfels, reklamacios, ugys);

		ugys.forEach(ugy -> ugy.setUgyfelsz(ugyfelszolgalat.getUgyfelbyId(ugy.getUgyfel())));
		reklamacios.forEach(rekalmacio -> rekalmacio.setUgy((ugyfelszolgalat.getUgyById(rekalmacio.getUgyid()))));
		reklamacios.forEach(rekalmacio -> rekalmacio.setOperator(ugyfelszolgalat.getOperatorbyId(rekalmacio.getOperatorid())));

		return ugyfelszolgalat;
	}
	
	public Ugy getUgyById(int ugyszam) {
		return ugys.stream().filter(ugy -> ugy.getUgyszam() == ugyszam).findFirst().orElseGet(Ugy::new);
	}
	

	public Ugyfel getUgyfelbyId(String ugyfelszam) {
		return ugyfels.stream().filter(ugyfel -> ugyfel.getUgyfelszam().equals(ugyfelszam)).findFirst().orElseGet(Ugyfel::new);
	}
	
	public Operator getOperatorbyId(String felh) {
		return operators.stream().filter(operator -> operator.getFelh() == felh).findFirst()
				.orElseGet(Operator::new);
	}

	public void addUgy(Ugy ugy) {
		Element element = root.createElement(UGY_TAG);
		element.setAttribute("ugyszam", Integer.toString(ugy.getUgyszam()));
		element.setAttribute("ugyfelszam", ugy.getUgyfel());
		element.setAttribute("tipus", ugy.getTipus());
		element.setAttribute("megjegy", ugy.getMegjegy());

		root.getDocumentElement().appendChild(element);
		ugys.add(ugy);
	}
	
	public Boolean checkUgyId(String ugyszam) {
		for (Ugy ugy : ugys) {
		
			if (Integer.toString(ugy.getUgyszam()).equals(ugyszam)) {
				
				return false;
			}
		}
		return true;
	}
	

	public Ugy keresesUgyUgyszam(String ugyszam) {
		for (Ugy ugy : ugys) {
			if (Integer.toString(ugy.getUgyszam()) == ugyszam) {
				return ugy;
			}
		}
		return null;
	}
	
	public Reklamacio keresesRekId(int ugyszam) {
		for (Reklamacio reklamacio : reklamacios) {
			if (reklamacio.getSzam() == ugyszam){
				return reklamacio;
			}
		}
		return null;
	}
	
	public Ugy keresesUgyId(int ugyszam) {
		for (Ugy ugy : ugys) {
			if (ugy.getUgyszam() == ugyszam) {
				return ugy;
			}
		}
		return null;
	}


	public Ugyfel keresesUgyfelId(String ugyfelszam) {
		for (Ugyfel ugyfel : ugyfels) {
			if (ugyfel.getUgyfelszam().equals(ugyfelszam)) {
				return ugyfel;
			}
		}
		return null;
	}
	
	public Ugyfel keresesUgyfelUgy(Ugy ugy) {
		for (Ugyfel ugyfel : ugyfels) {
			if (ugyfel.equals(ugy.getUgyfelsz())) {
				return ugyfel;
			}
		}
		return null;
	}
	
	public List<Ugy> keresesUgyByUgyfel(Ugyfel ugyfel) {
		List<Ugy> ugys1 = new ArrayList<>();
		for (Ugy ugy : ugys) {
			if (ugyfel.equals(ugy.getUgyfelsz())) {
				ugys1.add(ugy);
			}
		}
		return ugys1;
	}

	
	
	

	public void persist(String pathname) throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource source = new DOMSource(root);
		StreamResult result = new StreamResult(new File(pathname));
		transformer.transform(source, result);
	}
}
