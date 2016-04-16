package ba.ciel5.featureExtractor.features;

import ba.ciel5.featureExtractor.utils.AbstractSyntaxTreeUtil;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created on 15.04.2016.
 *
 * @author tobias.meier
 */
public class LengthOfNamesFeatureGroupTest {
    protected static String testCode = "//\n" +
            "// Source code recreated from a .class file by IntelliJ IDEA\n" +
            "// (powered by Fernflower decompiler)\n" +
            "//\n" +
            "\n" +
            "import java.util.Arrays;\n" +
            "\n" +
            "public class MyClass {\n" +
            "    // this is a single line comment\n" +
            "    private String name;\n" +
            "    private int id;\n" +
            "    boolean[] primes = new boolean[10000]; // quite a big array!\n" +
            "\n" +
            "    public MyClass(String name) {\n" +
            "        this.name = name;\n" +
            "        this.updateId();\n" +
            "    }\n" +
            "\n" +
            "   /**\n" +
            "    * This method reads and parses command line arguments\n" +
            "    *\n" +
            "    * @param args command line arguments.\n" +
            "    * @throws ParseException On input error.\n" +
            "    * @see ParseException\n" +
            "    */\n" +
            "    protected boolean updateId() {\n" +
            "        boolean didUpdate = false;\n" +
            "        int newId = this.id;\n" +
            "        if(this.name.equals(\"foo\")) {\n" +
            "            newId = 42;\n" +
            "        } else if(this.name.startsWith(\"test\")) {\n" +
            "            String testName = this.name.replaceFirst(\"test\", \"\");\n" +
            "            newId = testName.hashCode() * 100;\n" +
            "        } else {\n" +
            "            newId = this.name.hashCode();\n" +
            "        }\n" +
            "\n" +
            "        if(newId != this.id) {\n" +
            "            this.id = newId;\n" +
            "            didUpdate = true;\n" +
            "        }\n" +
            "\t   \t\n" +
            "        return didUpdate;\n" +
            "    }\n" +
            "\t\n" +
            "    public void fillSieve() {\n" +
            "        Arrays.fill(this.primes, true);\n" +
            "        this.primes[0] = this.primes[1] = false;\n" +
            "\n" +
            "        for(int i = 2; i < this.primes.length; ++i) {\n" +
            "            if(this.primes[i]) {\n" +
            "                for(int j = 2; i * j < this.primes.length; ++j) {\n" +
            "                    this.primes[i * j] = false;\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "         \n" +
            "    }\n" +
            "                       \n" +
            "    public boolean isPrime(int n) {\n" +
            "        return this.primes[n];\n" +
            "    }\n" +
            "\n" +
            "    public int getId() {\n" +
            "        return this.id;\n" +
            "    }\n" +
            "\n" +
            "    public String getName() {\n" +
            "        return this.name;\n" +
            "    }\n" +
            "\n" +
            "    public void setName(String name) {\n" +
            "        this.name = name;\n" +
            "        this.updateId();\n" +
            "    }\n" +
            "}\n" +
            "public class SecondClass {\n" +
            "}\n" +
            "";


    @org.junit.Test
    public void extract() throws Exception {
        LengthOfNamesFeatureGroup feature = new LengthOfNamesFeatureGroup();
        char[] code = testCode.toCharArray();
        CompilationUnit ast = AbstractSyntaxTreeUtil.parse(code);
        Map<String, Double> result = feature.extract(ast, code);

        assertEquals(1.0, result.get("MINVAR"), 0.0);
        assertEquals(9.0, result.get("MAXVAR"), 0.0);
        assertEquals(4.5, result.get("MEDVAR"), 0.0);

        assertEquals(5.0, result.get("MINMET"), 0.0);
        assertEquals(9.0, result.get("MAXMET"), 0.0);
        assertEquals(7.0, result.get("MEDMET"), 0.0);

        assertEquals(7.0, result.get("MINCLS"), 0.0);
        assertEquals(11.0, result.get("MAXCLS"), 0.0);
        assertEquals(9.0, result.get("MEDCLS"), 0.0);
    }
}