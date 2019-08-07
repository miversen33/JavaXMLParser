package XMLUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class XMLParent implements XMLHeader{
    private final String name;
    private String content = null;
    private final List<XMLHeader> children = new ArrayList<>();
    private final Map<String, XMLAttribute> attributes = new HashMap<>();

    public XMLParent(final String name){
        this.name = name;
    }

    @Override
    public final String getName(){
        return name;
    }

    @Override
    public final String getContent(){
        return content;
    }

    @Override
    public List<XMLHeader> getChildren() {
        return children;
    }

    public final XMLAttribute getAttribute(final String attribute){
        return attributes.get(attribute);
    }

    @Override
    public final List<XMLAttribute> getAttributes(){
        final List<XMLAttribute> attrs = new ArrayList<>();
        for(final String key : attributes.keySet()){
            attrs.add(attributes.get(key));
        }

        return attrs;
    }

    protected final void addChild(final XMLParent child){
        children.add(child);
    }

    protected final void addAttribute(final String attributeName, final String attribute){
        addAttribute(new XMLAttribute(attributeName, attribute));
    }

    protected final void addAttribute(final XMLAttribute attribute){
        attributes.put(attribute.getName(), attribute);
    }

    protected final void setContent(final String content){
        this.content = content;
    }
}
