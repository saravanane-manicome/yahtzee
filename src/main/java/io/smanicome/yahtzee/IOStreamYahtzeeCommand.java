package io.smanicome.yahtzee;

import io.smanicome.yahtzee.category.*;
import io.smanicome.yahtzee.exception.ImpossibleCommandException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IOStreamYahtzeeCommand implements YahtzeeCommand {
    private final BufferedReader bufferedReader;
    private final PrintStream printStream;
    private static final Map<String, Category> categoryByLabel = Map.ofEntries(
            Map.entry("1", new SpecificValueCategory(1)),
            Map.entry("2", new SpecificValueCategory(2)),
            Map.entry("3", new SpecificValueCategory(3)),
            Map.entry("4", new SpecificValueCategory(4)),
            Map.entry("5", new SpecificValueCategory(5)),
            Map.entry("6", new SpecificValueCategory(6)),
            Map.entry("2K", new NKindCategory(2)),
            Map.entry("3K", new NKindCategory(3)),
            Map.entry("4K", new NKindCategory(4)),
            Map.entry("2P", new TwoPairCategory()),
            Map.entry("SS", new SmallStraightCategory()),
            Map.entry("LS", new LargeStraightCategory()),
            Map.entry("FH", new FullHouseCategory()),
            Map.entry("Y", new YahtzeeCategory()),
            Map.entry("C", new ChanceCategory())
    );

    public IOStreamYahtzeeCommand(InputStream inputStream, OutputStream outputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        printStream = new PrintStream(outputStream);
    }

    @Override
    public boolean askReroll() throws ImpossibleCommandException {
        printStream.println("Do you want to try another roll ? Y / N (default)");
        try {
            final var answer = bufferedReader.readLine();
            return answer.equals("Y");
        } catch (IOException e) {
            throw new ImpossibleCommandException();
        }
    }

    @Override
    public void notifyRoll(List<Integer> roll) {
        final var line = roll.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        printStream.println(line);
    }

    private void printCategories() {
        printStream.println(
            """
            Select a category:
                Specific value       : 1 | 2 | 3 | 4 | 5 | 6
                Kind                 : 2K | 3K | 4K
                Pair                 : 2P
                Small/Large Straight : SS | LS
                Full House           : FH
                Yahtzee              : Y
                Chance               : C
            """
        );
    }

    @Override
    public Category askCategory() throws ImpossibleCommandException {
        Category category = null;

        while(category == null) {
            printCategories();
            try {
                final var selection = bufferedReader.readLine();
                category = categoryByLabel.get(selection);

                if(category == null) {
                    printStream.printf("'%s' is note a valid category\n", selection);
                }
            } catch (IOException e) {
                throw new ImpossibleCommandException();
            }
        }

        return category;
    }
}
