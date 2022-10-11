package lesson4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)// non null - если какое то значение будет null мы не передаем его в json
@JsonPropertyOrder({// определяет порядок  последовательности частей  в json
        "cuisine",
        "cuisines",
        "confidence"
})
@Data
public class Response {

    @JsonProperty("cuisine")// возможность указать наименование json переменной , которая отличается от наименования переменной класса
    private String cuisine;
    @JsonProperty("cuisines")
    private List<String> cuisines = null;
    @JsonProperty("confidence")
    private Double confidence;
    @JsonIgnore// атрибуты которые не участвуют в процессе сериализации и дессиарилизации
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
