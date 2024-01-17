package br.com.poupex.starters.api.commons.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Setter
@Getter
@Schema(name = "Page")
public class PageDTO<T> {

    private List<T> content;
    private List<SortOrder> sorts;

    private int size;
    private int number;
    private long totalElements;

    private boolean last;
    private boolean first;
    private boolean empty;

    @Getter
    public static class SortOrder {

        private final String dir;
        private final String prop;

        public SortOrder(Sort.Order order) {
            this.prop = order.getProperty();
            this.dir = order.getDirection().name().toLowerCase();
        }

    }

}
