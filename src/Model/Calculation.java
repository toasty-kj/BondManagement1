package Controller;

import java.math.BigDecimal;

public class Calculation {
    public BigDecimal calPurchasedPrice(BigDecimal currentAmount, BigDecimal bookPrice,BigDecimal purchasedAmount, BigDecimal purchasedPrice, int buysell){
        BigDecimal averagePrice = new BigDecimal("0");

        if (buysell == 1){
            averagePrice = ((currentAmount.multiply(bookPrice)).add((purchasedPrice).multiply(currentAmount))).divide(currentAmount.add(purchasedAmount));
        }else if (buysell == 0){
            averagePrice = bookPrice;
        }return averagePrice;
    }
    public BigDecimal calRemain(BigDecimal currentAmount, BigDecimal purchasedAmount, int buysell){
        BigDecimal remaining = new BigDecimal("0");
        if (buysell == 0){
            remaining = currentAmount.add(purchasedAmount);
        }
        else if (buysell == 1){
            remaining = currentAmount.subtract(purchasedAmount);
        }return remaining;
    }
}
