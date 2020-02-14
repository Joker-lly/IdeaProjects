package Tools.ExcelUtil;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook createExcel(String[][] excel, String sheetName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		int sheetNum = 1;// 工作薄sheet编号
		// sheet创建一个工作页
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 760);
		HSSFCellStyle style = wb.createCellStyle();// 设置边框及对齐方式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);


		int sjhs = 1;
		int hs = 0;
		for (int i = sjhs; i < excel.length; i++) {
			// HSSFRow,对应一行
			HSSFRow row = sheet.createRow(hs);
			if (hs == 0) {
				for (int j = 0; j < excel[0].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(excel[0][j]);
					cell.setCellStyle(style);
				}
				i = i - 1;
			} else {
				for (int j = 0; j < excel[i].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(excel[i][j]);
					cell.setCellStyle(style);
				}
				if (hs % 50000 == 0) {// 每个工作薄显示50000条数据
					sheet = null;
					sheetNum++;// 工作薄编号递增1
					sheet = wb.createSheet(sheetName);// 创建一个新的工作薄
					sheet.setDefaultColumnWidth((short) 15);
					sheet.setDefaultRowHeight((short) 760);
					hs = -1;
					sjhs++;
				}
			}
			hs++;
		}
		return wb;
	}

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook createExcel(String[][] excel, String sheetName, String[][] tjexcel, String tjsheetName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		int sheetNum = 1;// 工作薄sheet编号
		// sheet创建一个工作页
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 760);
		HSSFCellStyle style = wb.createCellStyle();// 设置边框及对齐方式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);

		int sjhs = 1;
		int hs = 0;
		for (int i = sjhs; i < excel.length; i++) {
			// HSSFRow,对应一行
			HSSFRow row = sheet.createRow(hs);
			if (hs == 0) {
				for (int j = 0; j < excel[0].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(excel[0][j]);
					cell.setCellStyle(style);
				}
				i = i - 1;
			} else {
				for (int j = 0; j < excel[i].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(excel[i][j]);
					cell.setCellStyle(style);
				}
				if (hs % 50000 == 0) {// 每个工作薄显示50000条数据
					sheet = null;
					sheetNum++;// 工作薄编号递增1
					sheet = wb.createSheet(sheetName + sheetNum);// 创建一个新的工作薄
					sheet.setDefaultColumnWidth((short) 15);
					sheet.setDefaultRowHeight((short) 760);
					hs = -1;
					sjhs++;
				}
			}
			hs++;
		}
		sheet = null;
		sheetNum++;
		sheet = wb.createSheet(tjsheetName + sheetNum);// 创建一个新的工作薄
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 760);
		int tjsjhs = 1;
		int tjhs = 0;
		for (int i = tjsjhs; i < tjexcel.length; i++) {
			// HSSFRow,对应一行
			HSSFRow row = sheet.createRow(tjhs);
			if (tjhs == 0) {
				for (int j = 0; j < tjexcel[0].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(tjexcel[0][j]);
					cell.setCellStyle(style);
				}
				i = i - 1;
			} else {
				for (int j = 0; j < tjexcel[i].length; j++) {
					// HSSFCell对应一格
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(tjexcel[i][j]);
					cell.setCellStyle(style);
				}
				if (tjhs % 50000 == 0) {// 每个工作薄显示50000条数据
					sheet = null;
					sheetNum++;// 工作薄编号递增1
					sheet = wb.createSheet(tjsheetName);// 创建一个新的工作薄
					sheet.setDefaultColumnWidth((short) 15);
					sheet.setDefaultRowHeight((short) 760);
					tjhs = -1;
					tjsjhs++;
				}
			}
			tjhs++;
		}
		return wb;
	}

	/**
	 * 创建Excel
	 * 
	 * @param excel
	 * @param sheetName
	 * @param titles
	 * @param columns
	 * @param data
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static HSSFWorkbook createExcel(HSSFWorkbook excel, String sheetName, String[][] titles, String[] columns,
			List<?> data) throws IllegalArgumentException, IllegalAccessException {
		// 如果excel是null，则初始化
		if (null == excel) {
			excel = new HSSFWorkbook();
		}

		// 设置表头字体
		HSSFFont titleFont = excel.createFont();
		titleFont.setFontHeightInPoints((short) 14); // 字号
		titleFont.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		titleFont.setFontName("宋体"); // 字体
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度
		titleFont.setItalic(false); // 是否使用斜体
		titleFont.setStrikeout(false); // 是否使用划线

		// 设置数据列表字体
		HSSFFont dataFont = excel.createFont();
		dataFont.setFontHeightInPoints((short) 10); // 字体高度
		dataFont.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		dataFont.setFontName("宋体"); // 字体
		dataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 宽度
		dataFont.setItalic(false); // 是否使用斜体
		dataFont.setStrikeout(false); // 是否使用划线

		// 数据格式化
		HSSFDataFormat format = excel.createDataFormat();

		HSSFCellStyle titleStyle = excel.createCellStyle();
		// 设置字体
		titleStyle.setFont(titleFont);
		// 设置边框及对齐方式
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 自动换行
		titleStyle.setWrapText(false);
		// 文本
		titleStyle.setDataFormat(format.getFormat("@"));

		HSSFCellStyle dataStyle = excel.createCellStyle();
		// 设置字体
		dataStyle.setFont(dataFont);
		// 设置边框及对齐方式
		dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 自动换行
		dataStyle.setWrapText(false);
		// 文本
		dataStyle.setDataFormat(format.getFormat("@"));

		// 判断数据不为空
		if (null != data && !data.isEmpty()) {
			// 数据总条数
			int total = data.size();
			// 每个sheet的数据条数
			double sheetSize = 50000.00;
			// 数据需要的sheet的数量
			int sheetNum = (int) Math.ceil(total / sheetSize);
			// 存放所有单元格的合并信息的集合
			Map<String, int[]> map = null;
			// 行
			HSSFRow row = null;
			// 单元格
			HSSFCell cell = null;
			// 标题
			String[] tit = null;

			// 每個sheet存放50000條數據，超出則新增sheet
			for (int sn = 0; sn < sheetNum; sn++) {
				map = new HashMap<String, int[]>();
				// 创建sheet
				HSSFSheet sheet = excel.createSheet(sheetName + (sn == 0 ? "" : sn + 1));
				// 输出表头
				for (int i = 0, tl = titles.length; i < tl; i++) {
					row = sheet.createRow(i);
					// 表头行高
					row.setHeightInPoints(24L);
					tit = titles[i];
					for (int c = 0, cl = tit.length; c < cl; c++) {
						// 创建单元格
						cell = row.createCell(c);
						// 设置单元格样式
						cell.setCellStyle(titleStyle);
						// 填充数据
						cell.setCellValue(tit[c]);
						// 将单元格信息存放到的合并信息的集合
						putCellInMap(map, i, c, tit[c]);
					}
				}
				// 合并单元格
				mergeExcelCell(sheet, map);

				// 开始index
				int startIndex = (int) (sn * sheetSize);
				// 结束index
				int endIndex = (int) (total < (sn + 1) * sheetSize ? total : (sn + 1) * sheetSize);

				// 循环导出数据
				for (int i = startIndex, index = titles.length - 1; i < endIndex; i++, index++) {
					// System.out.println(i);
					// 創建行
					row = sheet.createRow(index + 1);
					row.setHeightInPoints(18L);
					// 通过反射取到属性
					Field[] fields = data.get(i).getClass().getDeclaredFields();
					String value = "";
					// 循环列取值
					for (int c = 0, cl = columns.length; c < cl; c++) {
						cell = row.createCell(c);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						for (Field field : fields) {
							// 设置对象的访问权限，保证对private的属性的访
							field.setAccessible(true);
							// 判斷名字是否一致
							if (columns[c].equals(field.getName())) {
								if (field.getType().getName().indexOf("Double") != -1) {
									if (field.get(data.get(i)) == null || data.get(i) == null) {
										value = "0";
										continue;
									}
									// Double类型数字太大会自动转成科学计数法，所以要单独转换
									value = formatDouble(Double.valueOf(field.get(data.get(i)).toString()));
								} else {
									// 得到数据
									value = String.valueOf(field.get(data.get(i)));
									if ("null".equals(value)) {
										value = "0";
									}
								}
								break;
							}
						}
						// 将数据填充到單元格中
						cell.setCellValue(value);
						// 设置样式
						cell.setCellStyle(dataStyle);
					}
				}
				// 根据单元格内容自动设置列宽
				autoSizeColumn(sheet);
			}
		}
		return excel;
	}

	private static String formatDouble(Double param) {
		if (param != null) {
			if (param.doubleValue() != 0.00) {
				DecimalFormat df = new DecimalFormat("0.00");
				return df.format(param.doubleValue());
			} else {
				return "0.00";
			}
		}
		return "";
	}

	/**
	 * 将单元格合并信息放如map集合
	 * 
	 * @param map
	 * @param row
	 * @param col
	 * @param colValue
	 * @author 路新
	 */
	public static void putCellInMap(Map<String, int[]> map, int row, int col, String colValue) {
		// 是否需要新增,ture需要，false不需要
		boolean flag = true;
		String key = "";
		int[] mergeCell = new int[4];
		// 循环集合，判断此单元格是否与集合中的单元格相邻
		for (Entry<String, int[]> entry : map.entrySet()) {
			key = entry.getKey();
			key = key.substring(0, key.indexOf("$$"));
			// 判断集合中是否包含当前单元格内容
			if (key.equals(colValue)) {
				// 包含，则判断此单元格是否与集合中的单元格相邻
				mergeCell = entry.getValue();
				if ((mergeCell[0] == row && mergeCell[2] == col - 1) // 在源单元格右边
						|| (mergeCell[0] == row - 1 && mergeCell[2] == col)// 在源单元格下边
						|| (mergeCell[1] == row && mergeCell[3] == col - 1)// 在目标单元格右边
						|| (mergeCell[1] == row - 1 && mergeCell[3] == col)// 在目标单元格下边.
				) {
					// 相邻，则将此单元格与集合中的目标单元格合并
					mergeCell[1] = row;
					mergeCell[3] = col;
					// 已找到相邻单元格，不需要新增
					flag = false;
				}
			}
			// 已找到相邻单元格，跳出循环
			if (!flag) {
				break;
			}
		}
		// 未找到相邻单元格，则新增
		if (flag) {
			// map的key格式为：单元格的值 + && + 行数 + . + 列数，例如：第一列&&0.1
			key = colValue + "$$" + row + "." + col;
			// map的value格式为：{ 开始行数 , 结束行数 , 开始列数 , 结束列数 }
			mergeCell = new int[] { row, row, col, col };
			map.put(key, mergeCell);
		}
	}

	/**
	 * 合并单元格
	 * @param sheet
	 */
	public static void mergeExcelCell(HSSFSheet sheet) {
		// 存放所有单元格的合并信息的集合
		Map<String, int[]> map = new HashMap<String, int[]>();
		// 定义行
		HSSFRow row = null;
		// 循环行
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			// 循环列
			for (int c = 0; c < row.getLastCellNum(); c++) {
				putCellInMap(map, i, c, row.getCell(c).getStringCellValue());
			}
		}

		mergeExcelCell(sheet, map);
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param map中的int[]{firstRow,lastRow,firstCol,lastCol}
	 * @author 路新
	 */
	public static void mergeExcelCell(HSSFSheet sheet, Map<String, int[]> map) {
		// 单元格的合并信息，int[]{firstRow,lastRow,firstCol,lastCol}
		int[] cellMerge = new int[4];

		for (Entry<String, int[]> entry : map.entrySet()) {
			// 获取单元格合并信息
			cellMerge = entry.getValue();
			// 判断源单元格和目标单元格不是同一个才合并
			if (cellMerge[0] != cellMerge[1] || cellMerge[2] != cellMerge[3]) {
				sheet.addMergedRegion(new CellRangeAddress(cellMerge[0], cellMerge[1], cellMerge[2], cellMerge[3]));
			}
		}
	}

	/**
	 * 设置单元格根据内容自动列宽
	 * 
	 * @param sheet
	 */
	public static void autoSizeColumn(HSSFSheet sheet) {
		// Excel中最大行数
		int maxRowNum = sheet.getLastRowNum();
		// Excel中最大列数
		int maxColNum = 0;

		// 每一行的单元格数量
		int lastCellNum = 0;
		// Excel行数据
		HSSFRow row = null;
		// 循环每一行，计算Excel中最大列数
		for (int r = 0; r < maxRowNum; r++) {
			row = sheet.getRow(r);
			lastCellNum = row.getLastCellNum();
			if (maxColNum < lastCellNum) {
				maxColNum = lastCellNum;
			}
		}

		// 列的单元格内容最大宽度，默认10
		int maxCellValueLength = 10;
		// 单元格内容宽度
		int cellValueLength = 0;
		// 单元格
		HSSFCell cell = null;
		// 单元格的值
		String cellValue = null;
		// 循环计算每一列
		for (int c = 0; c < maxColNum; c++) {
			// 循环计算列中的每一个单元格
			for (int r = 0; r < maxRowNum; r++) {
				// 单元格
				cell = sheet.getRow(r).getCell(c);
				if (null != cell) {
					// 取到单元格的值
					cellValue = sheet.getRow(r).getCell(c).getStringCellValue();
					// 单元格不为null
					if (null != cellValue) {
						cellValueLength = cellValue.getBytes().length;
						if (maxCellValueLength < cellValueLength) {
							maxCellValueLength = cellValueLength;
						}
					}
				}
			}
			// 依据列的单元格内容最大宽度设置列宽
			sheet.setColumnWidth(c, maxCellValueLength * 256);
			// 初始化列的单元格内容最大宽度，默认10
			maxCellValueLength = 10;
		}

	}
}
