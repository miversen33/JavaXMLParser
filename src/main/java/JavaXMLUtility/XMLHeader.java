package JavaXMLUtility;

import java.util.List;

public interface XMLHeader {
    String getName();
    String getContent();
    List<XMLAttribute> getAttributes();
    XMLAttribute getAttribute(String attribute);
    List<XMLHeader> getChildren();
}
