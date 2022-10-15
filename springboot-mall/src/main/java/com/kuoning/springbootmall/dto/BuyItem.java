package com.kuoning.springbootmall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel("訂購的商品")
public class BuyItem {

    @ApiModelProperty("訂購商品的productId(從前端接收)")
    @NotNull
    private Integer productId;

    @ApiModelProperty("訂購商品的數量(從前端接收)")
    @NotNull
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
