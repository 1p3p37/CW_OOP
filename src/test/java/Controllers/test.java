//public class DeviationReport  Runnable {
//    private Thread t;
//    private Shell shell;
//    private String outputFileName;
//    private MainController m_c;
//
//    private static final Logger lggr = Logger.getLogger(DeviationReport.class.getSimpleName());
//
//    public DeviationReport(MainController mc, Shell shl) {
//        m_c = mc;
//        shell = shl;
//    }
//
//    public void run() {
//        if (outputFileName != null && outputFileName.matches(".*\\.pdf")) {
//            /* List to hold Items */
//            ArrayList<ScheduleDeviation> listItems = m_c.getBusesController().getReportDeviations();
//
//            /* Convert List to JRBeanCollectionDataSource */
//            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
//
//            /* Map to hold Jasper report Parameters */
//            Map<String, Object> parameters = new HashMap<String, Object>();
//            parameters.put("CollectionBeanParam", itemsJRBean);
//
//            try {
//// read jrxml file and creating jasperdesign object
//                InputStream input = new FileInputStream(new File(
//                        "C:\\dev\\repos\\HomeWork\\Учеба\\ООП\\Maven_Java_Project\\src\\main\\resources\\JasperFiles\\Buses_Schedule_Deviation.jrxml"));
//
//                JasperDesign jasperDesign = JRXmlLoader.load(input);
//
//                /* compiling jrxml with help of JasperReport class */
//                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//                /* Using jasperReport object to generate PDF */
//                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
//                        new JREmptyDataSource());
//
//                OutputStream outputStream = new FileOutputStream(new File(outputFileName));
//
//                /* Write content to PDF file */
//                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//                lggr.info("Report successfully created!");
//                Display.getDefault().asyncExec(new Runnable() {
//                    public void run() {
//                        MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
//                        messageBox.setMessage("Report Successfully created");
//                        messageBox.open();
//                    }
//                });
//            } catch (JRException jre) {
//                Display.getDefault().asyncExec(new Runnable() {
//                    public void run() {
//                        ErrorMassageBox.show(lggr, shell, "An error occurred while generating the report");
//                    }
//                });
//                lggr.error(jre);
//            } catch (FileNotFoundException fnfe) {
//                Display.getDefault().asyncExec(new Runnable() {
//                    public void run() {
//                        ErrorMassageBox.show(lggr, shell,
//                                "Can't find tamplate for report");
//                    }
//                });
//                lggr.error(fnfe);
//            }
//        }
//    }
//
//    public void start() {
//        /* Output file location to create report in pdf form */
//        FileDialog dialog = new FileDialog(shell);
//        String[] ext = { "*.pdf" };
//        dialog.setFilterExtensions(ext); // Windows specific
//        dialog.setFileName("report.pdf");
//        outputFileName = dialog.open();
//        lggr.info("Report file path: " + outputFileName);
//        if (t == null) {
//            t = new Thread(this);
//            t.start();
//        }
//
//    }
//}