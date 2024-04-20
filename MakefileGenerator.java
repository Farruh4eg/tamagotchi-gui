import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakefileGenerator {

  public static void main(String[] args) {
    generateMakefile();
  }

  public static void generateMakefile() {
    File directory = new File(".");
    File[] javaFiles = directory.listFiles((dir, name) -> name.endsWith(".java"));

    List<String> javaFileNames = new ArrayList<>();
    for (File javaFile : javaFiles) {
      javaFileNames.add(javaFile.getName());
    }

    try (FileWriter writer = new FileWriter("Makefile")) {
      writer.write("JC = javac\n\n");
      writer.write(".SUFFIXES: .java .class\n\n");
      writer.write(".java.class:\n");
      writer.write("\t$(JC) $<\n\n");

      StringBuilder classes = new StringBuilder();
      for (String fileName : javaFileNames) {
        classes.append(fileName.replace(".java", ".class")).append(" ");
      }

      writer.write("CLASSES = " + classes.toString() + "\n\n");
      writer.write("all: $(CLASSES)\n\n");
      writer.write("clean:\n\t$(RM) *.class\n\n");
      writer.write("build: all\n\n");

      System.out.println("Makefile generated successfully!");
    } catch (IOException e) {
      System.err.println("Failed to generate Makefile: " + e.getMessage());
    }
  }
}
