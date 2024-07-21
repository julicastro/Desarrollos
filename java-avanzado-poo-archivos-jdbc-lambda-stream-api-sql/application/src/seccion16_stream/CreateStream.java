package seccion16_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) {

        // 1: CREAR UN STREAM VACÍO

        Stream<String> streamVacio = Stream.empty();

        // 2. CREAR UN STREAM A PARTIR DE UNA COLECCIÓN

        // ejemplo A
        List<String> list = new ArrayList<>();
        list.add("objeto1");
        list.add("objeto2");
        list.add("objeto3");
        streamOfAList(list);

        // ejemplo B
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamCollection = collection.stream();

        // 3. STREAM DE ARRAYS

        // forma 1
        Stream<String> stream1 = Stream.of("a", "b", "c");

        // forma 2
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        Stream<String> stream2 = Arrays.stream(array);

        // forma 3
        Stream<String> stream3 = Arrays.stream(array, 1, 3); // va de la posicion 1 a la 3

        // 4. STREAM BUILDER
        Stream<String> streamBuilder = Stream.<String>builder()
                .add("a")
                .add("b")
                .add("c")
                .build();

        streamBuilder.forEach(System.out::println);




    }

    public static Stream<String> streamOfAList(List<String> list){
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }




}
