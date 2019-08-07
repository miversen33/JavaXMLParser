package XMLUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class XMLWriter implements XMLStrings{

    private final static String ENCODING = "utf-8";
    private final static String XML_VERSION = "<?xml version = \"1.0\"?>";

    private int indentationCount = 0;
    private StringBuilder indent = new StringBuilder();

    private final FileOutputStream fos;
    private final OutputStreamWriter osw;
    private final Writer writer;

    private XMLWriter(final File outputFile) throws IOException {
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }
        if(!outputFile.exists()){
            System.out.println("Unable to create new output file. Exception incoming");
            throw new FileNotFoundException();
        }

        fos = new FileOutputStream(outputFile);
        osw = new OutputStreamWriter(fos, ENCODING);
        writer = new BufferedWriter(osw);
        writer.write(XML_VERSION);
    }

    private final void write(final List<XMLHeader> headers) throws IOException {
        for(final XMLHeader header : headers){
            final String title = createHeaderTitle(header);
            writer.write(title);
            final boolean hasChildren = (header.getChildren() != null && !header.getChildren().isEmpty());
            if(hasChildren){
                indent();
                write(header.getChildren());
                deIndent();
            }
            final String closeTitle = createHeaderCloser(header, hasChildren);
            writer.write(closeTitle);
        }
    }

    private final void closeStreams() throws IOException {
        writer.close();
        fos.close();
        osw.close();
    }

    private final String createHeaderTitle(final XMLHeader header){
        if(header.getAttributes() == null || header.getAttributes().isEmpty()) return LINE_BREAK+indent+LEFT_ANCHOR+header.getName()+ RIGHT_ANCHOR;
        StringBuilder headerTitle = new StringBuilder(LINE_BREAK+indent+LEFT_ANCHOR+header.getName());
        int count = 0;
        int attrPerLineLimit = 2;
        boolean indentAttrs = false;
        for(final XMLAttribute attribute : header.getAttributes()){
            if(count >= attrPerLineLimit){
                headerTitle.append(LINE_BREAK).append(indent);
                indentAttrs = true;
                count = 0;
            }
            count ++;
            if(indentAttrs) {
                headerTitle.append(INDENTATION);
            } else {
                headerTitle.append(SPACE);
            }
            headerTitle.append(attribute.getName()).append(SPACE).append(EQUALS).append(SPACE).append(QUOTATION).append(attribute.getAttribute()).append(QUOTATION);
        }

        headerTitle.append(RIGHT_ANCHOR);
        return headerTitle.toString();
    }

    private final void indent(){
        indentationCount ++;
        indent.append(INDENTATION);
    }

    private final void deIndent(){
        if(indentationCount == 0){
//            TODO Use some form of actual logging here. This doesn't need to be seen by everyone unless they are looking for it
            System.out.println("Unable to remove further indentation");
            return;
        }
        indentationCount --;
        indent.delete(indent.length() - INDENTATION.length(), indent.length());

    }

    private final String createHeaderCloser(final XMLHeader header, final boolean indentCloseTag){
        if(indentCloseTag) return LINE_BREAK+indent+formatContent(header)+LEFT_CLOSE_ANCHOR+header.getName()+RIGHT_ANCHOR;
        return formatContent(header)+LEFT_CLOSE_ANCHOR+header.getName()+ RIGHT_ANCHOR;
    }

    private final String formatContent(final XMLHeader header){
        if(header.getContent() == null) return "";
        return header.getContent();
    }


    public final static void Write(final File outputFile, final XMLHeader ... headers) throws IOException {
        Write(outputFile, Arrays.asList(headers));
    }

    public final static void Write(final File outputFile, final List<XMLHeader> headers) throws IOException {
        final XMLWriter writer = new XMLWriter(outputFile);
        writer.write(headers);
        writer.closeStreams();
    }
}
