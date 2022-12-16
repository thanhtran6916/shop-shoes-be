package com.adidas.shopshoes.dto.request;

import com.adidas.shopshoes.model.Category;
import com.adidas.shopshoes.model.CustomerType;
import com.adidas.shopshoes.model.ImageProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoesRequest {

    private String id;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    private String productName;

    @NotNull(message = "Danh mục sản phẩm không được để trống!")
    private Category category;

    @NotNull(message = "Loại khách hàng không được để trống!")
    private CustomerType customerType;

//    @NotEmpty(message = "Ảnh sản phẩm không được để trống!")
    private List<MultipartFile> imageProductFiles;

    private Integer size;

    private String color;

    private Integer quantitySale;

    private Integer reviews;
}
