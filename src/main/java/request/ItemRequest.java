package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private int siteId;
    private int categoryId;
    private String title;
    private String condition;
    private double  price;
    private int quantity;
    private List<String> imageURLs;
    private Map<String, String> itemSpecifics;
    private String description;
}
