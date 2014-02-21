package ui.patient.chart.view;

import database.dao.DaoFactory;
import database.dao.PatientCardDao;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import patient.PatientCard;
import patient.Survey;
import ui.components.FillTitleBorder;
import ui.patient.chart.controller.PatientCardController;
import util.Const;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RightChartPanel extends JPanel {
    PatientCardController presenter = PatientCardController.getInstance();
    private PatientCard patientCard;

    public RightChartPanel(PatientCard patientCard) {
        this.patientCard = patientCard;
        setUp();
        initComponents();
    }

    private void setUp() {
        presenter.setRightChartPanel(this);
        setBorder(new FillTitleBorder("Lista pacjentów"));
        setBackground(Const.Colors.BACKGROUND_PANEL);
    }

    private void initComponents() {
        JFreeChart chart = createOverlaidChart();

        JPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    private JFreeChart createOverlaidChart() {

        final DateAxis domainAxis = new DateAxis("Data");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        final ValueAxis rangeAxis = new NumberAxis("Temperatura");
        rangeAxis.setRange(32, 45);

        Calendar cal = Calendar.getInstance();
        Date todayDate = cal.getTime();
        cal.add(Calendar.DATE, -30);
        Date oneMonthBeforeDate = cal.getTime();

        domainAxis.setRange(oneMonthBeforeDate, todayDate);

        final XYDataset data2 = createDataset();
        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
        final XYPlot plot = new XYPlot(data2, domainAxis, rangeAxis, renderer2);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        JFreeChart jFreeChart = new JFreeChart("Wykres temperatur pacjenta", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        jFreeChart.setBackgroundPaint(Const.Colors.FILL_BORDER);
        return jFreeChart;

    }

    private XYDataset createDataset() {
        PatientCardDao patientCardDao = new PatientCardDao(new DaoFactory());
        List<Survey> surveysFromLastMonth = patientCardDao.getSurveysFromLastMonth(patientCard);

        TimeSeries seria = new TimeSeries("Temperatura z ostatniego miesiąca", Day.class);

        Calendar cal = Calendar.getInstance();

        for (Survey survey : surveysFromLastMonth) {
            cal.setTime(survey.getDate());
            seria.addOrUpdate(new Day(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR)), survey.getTemperature());
        }

        final TimeSeriesCollection tsc = new TimeSeriesCollection(seria);
        return tsc;
    }

    public void refreshChart() {
        removeAll();
        revalidate();

        JFreeChart chart = createOverlaidChart();
        chart.removeLegend();
        JPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);

        repaint();
    }
}