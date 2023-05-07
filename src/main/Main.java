package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0])).useLocale(Locale.US);  //args[0]
        InputHelper iH = new InputHelper();
        iH.help(in);
        PrintStream out = new PrintStream(new File(args[1]));    //args[1]
        OutputHelper oH = new OutputHelper();
        oH.finalize(out,iH);
    }
}
