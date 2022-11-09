package com.cieep;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;
        String signo = "ERROR";
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime la operación: ");
        String operacion = sc.nextLine();
        ProcessBuilder proceso = null;

        try {
            if (operacion.contains("+")) {
                signo = "+";
                num1 = Integer.parseInt(operacion.split("\\+")[0]);
                num1 = Integer.parseInt(operacion.split("\\+")[1]);
                proceso = new ProcessBuilder("java", ".jar", "Suma.jar");
            } else if (operacion.contains("-")) {
                signo = "-";
                num1 = Integer.parseInt(operacion.split("-")[0]);
                num1 = Integer.parseInt(operacion.split("-")[1]);
                proceso = new ProcessBuilder("java", ".jar", "Resta.jar");
            } else if (operacion.contains("*")) {
                signo = "*";
                num1 = Integer.parseInt(operacion.split("\\*")[0]);
                num1 = Integer.parseInt(operacion.split("\\*")[1]);
                proceso = new ProcessBuilder("java", ".jar", "Multiplicacion.jar");
            } else if (operacion.contains("/")) {
                signo = "/";
                num1 = Integer.parseInt(operacion.split("/")[0]);
                num1 = Integer.parseInt(operacion.split("/")[1]);
                proceso = new ProcessBuilder("java", ".jar", "Division.jar");
            } else {
                System.out.println("La operación es inrealizable.");
            }

            if (!signo.equals("ERROR")) {
                float resultado = creaHijoCalculadora(proceso, num1, num2);
                System.out.println(operacion + " = " + resultado);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Escribe bien, subnormal. ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static float creaHijoCalculadora(ProcessBuilder proceso, int num1, int num2) throws IOException {
        proceso.redirectErrorStream(true);
        Process hijo = proceso.start();

        OutputStream outputStream = hijo.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = hijo.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);

        printStream.println(String.valueOf(num1));
        printStream.flush();
        printStream.println(String.valueOf(num2));
        printStream.flush();

        return Float.parseFloat(br.readLine());
    }
}