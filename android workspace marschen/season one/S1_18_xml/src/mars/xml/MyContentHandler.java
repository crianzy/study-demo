package mars.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {
	String hisname, address, money, sex, status;
	String tagName;
	
	public MyContentHandler(){
		super();
		System.out.println("new MyContentHandler");
	}

	public void startDocument() throws SAXException {
		System.out.println("````````begin````````");
	}

	public void endDocument() throws SAXException {
		System.out.println("````````end````````");
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		tagName = localName;
		if (localName.equals("worker")) {
			//��ȡ��ǩ��ȫ������
			for (int i = 0; i < attr.getLength(); i++) {
				System.out.println(attr.getLocalName(i) + "=" + attr.getValue(i));
			}
		}
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		//��workr��ǩ������֮�󣬻��ӡ�����еõ�������
		tagName = "";
		if (localName.equals("worker")) {
			this.printout();
		}
	}
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tagName.equals("name"))
			hisname = new String(ch, start, length);
		else if (tagName.equals("sex"))
			sex = new String(ch, start, length);
		else if (tagName.equals("status"))
			status = new String(ch, start, length);
		else if (tagName.equals("address"))
			address = new String(ch, start, length);
		else if (tagName.equals("money"))
			money = new String(ch, start, length);
	}

	private void printout() {
		System.out.print("name: ");
		System.out.println(hisname);
		System.out.print("sex: ");
		System.out.println(sex);
		System.out.print("status: ");
		System.out.println(status);
		System.out.print("address: ");
		System.out.println(address);
		System.out.print("money: ");
		System.out.println(money);
		System.out.println();
	}

}