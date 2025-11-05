package pl.obrona.managementapi.report.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.statistics.dto.ReportStatisticsDto;

import java.io.ByteArrayOutputStream;

@Service
public class PdfReportService {

    public byte[] generatePdf(String title, ReportStatisticsDto stats) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph pTitle = new Paragraph(title, titleFont);
            pTitle.setAlignment(Element.ALIGN_CENTER);
            pTitle.setSpacingAfter(10);
            document.add(pTitle);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);
            table.setSpacingBefore(10);

            addCell(table, "Highest Order Value", toStr(stats.getHighestOrderValue()));
            addCell(table, "Average Order Value", toStr(stats.getAverageOrderValue()));
            addCell(table, "Cash Income", toStr(stats.getCashIncome()));
            addCell(table, "Card Income", toStr(stats.getCardIncome()));
            addCell(table, "Total Revenue", toStr(stats.getTotalRevenue()));
            addCell(table, "Ingredient Costs", toStr(stats.getIngredientCosts()));
            addCell(table, "Fixed Costs", toStr(stats.getFixedCosts()));
            addCell(table, "Total Expense", toStr(stats.getTotalExpense()));
            addCell(table, "Total Profit", toStr(stats.getTotalProfit()));
            addCell(table, "Transaction Count", toStr(stats.getTransactionCount()));
            addCell(table, "First Transaction Time", stats.getFirstTransactionTime() != null ? stats.getFirstTransactionTime().toString() : "N/A");
            addCell(table, "Last Transaction Time", stats.getLastTransactionTime() != null ? stats.getLastTransactionTime().toString() : "N/A");
            addCell(table, "Average Margin Per Transaction", toStr(stats.getAverageMarginPerTransaction()));
            addCell(table, "Average Items Per Transaction", toStr(stats.getAverageItemsPerTransaction()));

            document.add(table);

            if (stats.getRevenuePerProduct() != null && !stats.getRevenuePerProduct().isEmpty()) {
                Paragraph revenueHeader = new Paragraph("Revenue Per Product", headerFont);
                revenueHeader.setSpacingBefore(10);
                revenueHeader.setSpacingAfter(10);
                document.add(revenueHeader);

                PdfPTable revenueTable = new PdfPTable(2);
                revenueTable.setWidthPercentage(70);
                addCell(revenueTable, "Product", "Revenue");
                stats.getRevenuePerProduct().forEach((product, revenue) -> addCell(revenueTable, product, revenue.toString()));
                document.add(revenueTable);
            }

            if (stats.getUnitsSoldPerProduct() != null && !stats.getUnitsSoldPerProduct().isEmpty()) {
                Paragraph unitsHeader = new Paragraph("Units Sold Per Product", headerFont);
                unitsHeader.setSpacingBefore(10);
                unitsHeader.setSpacingAfter(10);
                document.add(unitsHeader);

                PdfPTable unitsTable = new PdfPTable(2);
                unitsTable.setWidthPercentage(70);
                addCell(unitsTable, "Product", "Units Sold");
                stats.getUnitsSoldPerProduct().forEach((product, units) -> addCell(unitsTable, product, units.toString()));
                document.add(unitsTable);
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    private void addCell(PdfPTable table, String key, String value) {
        PdfPCell cellKey = new PdfPCell(new Phrase(key));
        PdfPCell cellValue = new PdfPCell(new Phrase(value));
        cellKey.setPadding(5);
        cellValue.setPadding(5);
        table.addCell(cellKey);
        table.addCell(cellValue);
    }

    private String toStr(Object obj) {
        return obj != null ? obj.toString() : "N/A";
    }
}
