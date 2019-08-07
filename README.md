A Simple XML Parser Written in Java

Using this is simple enough. See below.

* Option 1 (If you feel like handling the exceptions yourself)
```java
File inputFile = new File("Insert Your File Location Here");
List<XMLParent> parsedXML = XMLParser.Parse(inputFile);
```


* Option 2 (If you feel like letting the parser handle the exceptions)
```java
File inputFile = new File("Insert Your File Location Here");
List<XMLParent> parsedXML = XMLParser.DefaultParse(inputFile);
```


Your XMLParent will have 4 things in it, as follows
* A name
* A List of its children (also XMLParents)
* A Map of its attributes
* Any content that was between its open and close brackets