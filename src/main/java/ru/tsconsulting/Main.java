package ru.tsconsulting;

import ru.tsconsulting.Excel.*;
import ru.tsconsulting.Logic.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Необходимо передать программе 2 аргумента:" + "\n"
                    + "Путь к исходному файлу;\n"
                    + "Имя исходного файла.\n");
            System.exit(1);
        }

        TransferSearch transferSearch = new TransferSearch();

        Path sourceDirectoryPath = Paths.get(args[0], args[1]);
        if (sourceDirectoryPath.toFile().exists()) {
            SourceExcelSheet sourceExcelSheet = new SourceExcelSheet();
            sourceExcelSheet.readFromExcel
                    (sourceDirectoryPath.toString(), transferSearch.getDepartmentHashMap());
        } else {
            System.out.println("Программе переданы неверные аргументы.");
            System.exit(1);
        }

        transferSearch.searchForTransfers();

        ResultExcelSheet resultExcelSheet = new ResultExcelSheet();
        Path resultDirectoryPath = Paths.get(args[0], "Result File.xlsx");
        resultExcelSheet.writeResultToFile
                (resultDirectoryPath.toString(), transferSearch.getTransferLinkedList());
    }
}