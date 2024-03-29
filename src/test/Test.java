package test;

import json.parser.Parser;
import json.parser.Scanner;
import json.parser.Token;
import json.dataTypes.JSONData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("testData.json"));
//        System.out.println(content);

        List<Token> list;
        Scanner sc = new Scanner(content);
        list = sc.scanTokens();

//        for (Token t : list) {
//            System.out.println(t);
//        }

        Parser p = new Parser(list);
        JSONData parse = p.parse();
//        System.out.println(parse);
//        System.out.println(parse.get("Deaths").getData());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(">>> ");
            String str = reader.readLine();
//            System.out.println(str);
            System.out.println(parse.get(str).getData());
        }
    }
}
