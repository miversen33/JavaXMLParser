package JavaXMLUtility;

public final class XMLAttribute{
    private final String name;
    private final String attribute;

    public XMLAttribute(final String name, final String attribute){
        this.name = name;
        this.attribute = attribute;
    }

    public final String getName(){
        return name;
    }

    public final String getAttribute(){
        return attribute;
    }
}