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

---
### License

Copyright (c) 2019 Bladebeat Productions

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

