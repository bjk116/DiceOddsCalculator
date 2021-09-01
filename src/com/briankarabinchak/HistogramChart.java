package com.briankarabinchak;

import net.miginfocom.swing.MigLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class HistogramChart extends JFrame {
    public HistogramChart(double[] data) {
//        will eventually want this as a paramter -> HashMap<Integer, Integer> data
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Results");

        //Layout and panel to house components
        MigLayout layout = new MigLayout("wrap 1", "[fill, grow]", "");
        //Making Chart
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Negated Damage", data,8);
        JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram",
                "Negated Damage", "Frequency", dataset);
        ChartPanel panel = new ChartPanel(histogram);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.white);
        
        add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
//        HistogramChart t = new HistogramChart();
    }
}
