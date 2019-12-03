package domdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class DomDemo {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/main/resources/ugyfelszolgalat.xml");

		Ugyfelszolgalat ugyfelszolgalat = Ugyfelszolgalat.create(document);

		Scanner in = new Scanner(System.in);
			System.out.println("1. Uj ugy hozzaadasa");	
			System.out.println("2. Reklamacio keresese");
			System.out.println("3. Ugy keresese ugyfel alapjan");
			System.out.println("4. Ugyfel keresese ugy alapjan");
			System.out.println("0. Kilepes");

		boolean quit = false;
		int menuItem;

		do {
			System.out.print("Válassz a menübõl: ");
			
			menuItem = in.nextInt();
			switch (menuItem) {
			case 1:
				System.out.println("Valasztott menupont: 1");
				Ugy ugy = new Ugy();
				String[] ugyadat = {"ugyszam(6 szam)", "ugyfel(2 betu es 6 szam)", "tipus(pl.:számla,hiba)", "megjegy"};
				List<String> lista = new ArrayList<>();
				String ugyhozzaad = in.nextLine();
				for(String adat : ugyadat) {
					System.out.println(" ");
					System.out.println("Adja meg az alabbi adatot: " + adat);
					String ujadat = in.nextLine();
					lista.add(ujadat);
				}
				while(!ugyfelszolgalat.checkUgyId(lista.get(0))) {
						System.out.println("Ilyen szamu ugy mar letezik!");
						String ujadat =in.nextLine();
						lista.set(0, ujadat);
				}
				
				ugy.setUgyszam(Integer.parseInt(lista.get(0)));
				ugy.setUgyfel(lista.get(1));
				ugy.setTipus(lista.get(2));
				ugy.setMegjegy(lista.get(3));
				ugyfelszolgalat.addUgy(ugy);
				ugyfelszolgalat.persist("src/main/resources/vegeredmeny.xml");
				System.out.println("Ugy hozzaadva");
				System.out.println(" ");
				break;

			case 2:

				System.out.println("Valasztott menupont: 2");

				String rekkeres = in.nextLine();
				System.out.println("Adja meg a reklamacio szamat: (pl.:3584) ");
				int rekid = in.nextInt();
				Reklamacio reklamacio = new Reklamacio();
				reklamacio = ugyfelszolgalat.keresesRekId(rekid);
				System.out.println("Reklamacio adatai:");
				System.out.println(" ");
				System.out.println("Ugy szama: " + reklamacio.getUgyid());
				System.out.println("Operator neve: " + reklamacio.getOperatorid());
				System.out.println(" ");
				break;

			case 3:

				System.out.println("Valasztott menupont: 3");

				String ugykeres = in.nextLine();
				System.out.println("Kerem az ugyek ugyfelszamat (pl.:MT163033): ");
				String ugyid = in.nextLine();
				List<Ugy> ugys;
				ugys = ugyfelszolgalat.keresesUgyByUgyfel(ugyfelszolgalat.getUgyfelbyId(ugyid));
				System.out.println("Ugyek adatai:");
				for(Ugy ugyek : ugys) {
					System.out.println(" ");
					System.out.println("Ugyszam: " + ugyek.getUgyszam());
					System.out.println("Ugyfel: " + ugyek.getUgyfel());
					System.out.println("Tipus: " + ugyek.getTipus());
					System.out.println("Megjegyzes: " + ugyek.getMegjegy());
					System.out.println(" ");
				}

				
				break;

			case 4:

				System.out.println("Valasztott menupont: 4");

				String ugyfelkeres = in.nextLine();
				System.out.println("Ugyfel keresese ugyszam alapjan: (pl.:6454629)");
				int ugyid1 = in.nextInt();
				Ugyfel ugyfel = new Ugyfel();
				ugyfel = ugyfelszolgalat.keresesUgyfelUgy(ugyfelszolgalat.keresesUgyId(ugyid1));
				System.out.println("Ugyfel adatai:");
				System.out.println(" ");
				System.out.println("Ugyfel szama: " + ugyfel.getUgyfelszam());
				System.out.println("Ugyfel neve: " + ugyfel.getNev());
				System.out.println(" ");
				break;
				
			case 0:
				quit = true;
				break;
			default:
				System.out.println("Rossz input.");
			}
		} while (!quit);
		System.out.println("Program kilép...");

	}

}
