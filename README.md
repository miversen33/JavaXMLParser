A Simple XML Utility Written in Java

Using this is simple enough. See below.

Download the jar, make it a dependency for your project via your preferred method.


## The JavaXMLUtility has 2 Abilities (currently)

---
### Parsing XML

* Option 1 (If you feel like handling the exceptions yourself)
```java
File inputFile = new File("Insert Your File Location Here");
List<XMLHeader> parsedXML = XMLParser.Parse(inputFile);
```


* Option 2 (If you feel like letting the parser handle the exceptions)
```java
File inputFile = new File("Insert Your File Location Here");
List<XMLHeader> parsedXML = XMLParser.DefaultParse(inputFile);
```
---
### Writing XML
Usage
* Option 1 (List of XML Headers)
```java
File inputFile = new File("Insert Your Output File Location Here");
XMLWriter.Write(outputFile, xmlParents);
```
* Option 2 (Multiple XML Headers)
```java
File inputFile = new File("Insert Your Output File Location Here");
XMLWriter.Write(outputFile, xmlParent1, xmlParent2, ... xmlParentZ);
```
---

Your XMLParent will have 4 things in it, as follows
* A name
* A List of its children (which will be XMLHeaders)
* A Map of its attributes
* Any content that was between its open and close brackets
