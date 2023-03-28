import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".\\[(.+)].*");
    private static final Pattern REGEX_ATRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid JSON: ");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {
            Map<String, String> attributosItem = new HashMap<>();

            Matcher matcherAttributosItem = REGEX_ATRIBUTES_JSON.matcher(item);
            while (matcherAttributosItem.find()) {
                String chave = matcherAttributosItem.group(1);
                String valor = matcherAttributosItem.group(2);

                attributosItem.put(chave, valor);

            }
            dados.add(attributosItem);
        }

        return dados;

    }

}
