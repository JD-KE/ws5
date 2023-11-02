package sdf.day05.boardgames;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static final int COL_NAME = 1;
    public static final int COL_YEARPUBLISHED = 2;
    public static final int COL_MINPLAYTIME = 6;
    public static final int COL_MAXPLAYTIME = 7;
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.err.println("Missing book CSV");
            System.exit(1);
        }

        System.out.printf("Processing %s%n", args[0]);
        // String csv = "boardgames1.csv";


        try (FileReader fr = new FileReader(args[0])) {
            BufferedReader br = new BufferedReader(fr);

            Map<String,List<BoardGame>> groupByPlayTime = br.lines()
                .skip(1)
                .limit(100)
                .map (line -> line.trim().split(","))
                .map (fields -> new BoardGame(fields[COL_NAME], fields[COL_YEARPUBLISHED], Integer.parseInt(fields[COL_MINPLAYTIME]),
                Integer.parseInt(fields[COL_MAXPLAYTIME])))
                .collect(Collectors.groupingBy(boardgame -> {
                    int time = boardgame.getMaxPlayTime() - boardgame.getMinPlayTime();
                    if (time >= 0 && time < 30) {
                        return "0-30";
                    } else if (time >= 30 && time < 60) {
                        return "30-60";
                    } else if (time >= 60 && time < 120) {
                        return "60-120";
                    }  else if (time >= 120 && time < 180) {
                        return "120 - 180";
                    } else if (time >= 180 ) {
                        return ">180";
                    }
                    return "Invalid playtime"; 

                }))
                ;

            Map<String,List<BoardGame>> sortByPlayTime = new TreeMap<>(groupByPlayTime);
            
            for (String timeRange : sortByPlayTime.keySet()) {
                if (!timeRange.equals("Invalid playtime")) {
                    System.out.printf("%s%n",timeRange);
                    for(BoardGame boardGame : groupByPlayTime.get(timeRange)) {
                        System.out.printf("\t%s %s%n", boardGame.getName(), boardGame.getYear());
                    }
                }
            }
            


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

    }
}