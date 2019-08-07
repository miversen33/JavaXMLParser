package XMLParser;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class XMLParent{
    private final String name;
    private String content = null;
    private final List<XMLParent> children = new ArrayList<XMLParent>();
    private final Map<String, XMLAttribute> attributes = new HashMap<>();

    public XMLParent(final String name){
        this.name = name;
    }

    public final String getName(){
        return name;
    }
    
    public final String getContent(){
        return content;
    }

    public final List<XMLParent> getChildren(){
        return children;
    }

    public final XMLAttribute getAttribute(final String attribute){
        return attributes.get(attribute);
    }

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
