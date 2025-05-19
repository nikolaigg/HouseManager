package org.example.entity.company;

import jakarta.persistence.*;
import org.example.entity.building.Building;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tax_receipt")
public class TaxReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long receiptId;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @ManyToOne
    @JoinColumn(name = "house_manager_id", nullable = false)
    private HouseManager houseManager;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate dateOfPayment;

    public TaxReceipt() {

    }

    public TaxReceipt(Building building, HouseManager houseManager, BigDecimal amount, LocalDate dateOfPayment) {
        this.building = building;
        this.houseManager = houseManager;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public HouseManager getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return dateOfPayment;
    }

    public void setDate(LocalDate date) {
        this.dateOfPayment = date;
    }

    @Override
    public String toString() {
        return "TaxReceipt{" +
                "receiptId=" + receiptId +
                ", building=" + building +
                ", houseManager=" + houseManager +
                ", amount=" + amount +
                ", date=" + dateOfPayment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxReceipt that = (TaxReceipt) o;

        return receiptId != null && receiptId.equals(that.receiptId);
    }

    @Override
    public int hashCode() {
        return receiptId != null ? receiptId.hashCode() : 0;
    }

}
