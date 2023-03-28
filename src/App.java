import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Faz conexão http
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(req, BodyHandlers.ofString());

        String body = response.body();

        // System.out.println(body);
        // TODO: Extrair dados

        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);
        // System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes);

        for (Map<String, String> map : listaDeFilmes) {
            System.out.println(map.get("title"));
        }
    }
}
