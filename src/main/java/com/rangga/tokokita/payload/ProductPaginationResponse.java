package com.rangga.tokokita.payload;

import com.rangga.tokokita.payload.common.PaginationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductPaginationResponse extends PaginationResponse {

    private List<ProductListResponse> content = new ArrayList<>();

    public void setContent(List<ProductListResponse> content) {
        this.content = content;
    }

    public List<ProductListResponse> getContent() {
        return content;
    }
}
