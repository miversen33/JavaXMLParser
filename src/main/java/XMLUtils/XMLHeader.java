package XMLUtils;

import java.util.List;

public interface XMLHeader {
    String getName();
    String getContent();
    List<XMLAttribute> getAttributes();
    List<XMLHeader> getChildren();
}
