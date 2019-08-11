package JavaXMLUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.stream.XMLStreamConstants;

import java.util.ArrayList;
import java.lang.RuntimeException;

import org.xml.sax.helpers.DefaultHandler;

public final class XMLParser extends DefaultHandler{

    private final File xmlFile;
    private final List<XMLHeader> children = new ArrayList<>();
    private final List<XMLParent> openChildren = new ArrayList<>();
    private final static String LINE_BREAK = "\n";

    private XMLParser(final File inputFile) throws FileNotFoundException {
        if(!inputFile.exists()) throw new FileNotFoundException();
        xmlFile = inputFile;
    }

    private final void parse() throws FileNotFoundException, XMLStreamException {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        final XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(xmlFile));
        while(eventReader.hasNext()){
            handleNextEvent(eventReader.nextEvent());
        }
    }

    private final void handleNextEvent(final XMLEvent event){
        switch(event.getEventType()){
            case XMLStreamConstants.START_ELEMENT:
                startElement(event.asStartElement());
                break;

            case XMLStreamConstants.END_ELEMENT:
                endElement(event.asEndElement());
                break;

            case XMLStreamConstants.CHARACTERS:
                handleCharacters(event.asCharacters());
                break;
        }
    }

    private final void startElement(final StartElement start){
        final String tag = start.getName().getLocalPart();
        final XMLParent parent = new XMLParent(tag);
        final Iterator<Attribute> attributes = start.getAttributes();
        while(attributes.hasNext()){
            Attribute attribute = attributes.next();
            parent.addAttribute(attribute.getName().getLocalPart(), attribute.getValue());

        }
        openChildren.add(parent);
    }

    private final void endElement(final EndElement end) throws MalformedXMLException {
        final XMLParent child = openChildren.get(openChildren.size() - 1);
        final String tag = end.getName().getLocalPart();
        if(!tag.equalsIgnoreCase(child.getName())) throw new MalformedXMLException();
        openChildren.remove(openChildren.size() - 1);
        if(openChildren.size() > 0){
            final XMLParent parent = openChildren.get(openChildren.size() - 1);
            parent.addChild(child);
        } else {
            children.add(child);
        }
    }

    private final void handleCharacters(final Characters chars){
        if(chars.getData().contains(LINE_BREAK)) return;
        if(openChildren.isEmpty()) throw new MalformedXMLException("Invalid location for content");
        final XMLParent child = openChildren.get(openChildren.size() - 1);
        child.setContent(chars.getData());
    }



    private final List<XMLHeader> getChildren(){
        return children;
    }

    private class MalformedXMLException extends RuntimeException{
        private static final long serialVersionUID = 1L;

        private MalformedXMLException(){
            super();
        }

        private MalformedXMLException(final String message){
            super(message);
        }
    }

    public final static List<XMLHeader> Parse(final File file) throws FileNotFoundException, XMLStreamException {
        final XMLParser parser = new XMLParser(file);
        parser.parse();
        return parser.getChildren();
    }

    /**
     * Use this if you dont plan on handling the exceptions that may arise. You should not trust any
     * file you are putting in however.
     */
    public final static List<XMLHeader> DefaultParse(final File file){
        try {
            return Parse(file);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

}
