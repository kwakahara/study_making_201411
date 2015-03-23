package jp.ktsystem.kadai201411.k_wakahara;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import jp.ktsystem.kadai201411.common.ErrorCode;
import jp.ktsystem.kadai201411.common.KadaiException;

/**
 * 集計クラス
 * 
 * @author wakahara
 *
 */
public class TotalizationOrder {
	private static final int RECEIVE_ID = 0;
	private static final int CUSTOMER_NAME = 1;
	private static final int MANUFACTURE_NAME = 2;
	private static final int ORDER_NUM = 3;
	private static final int PAYMENT_DATE = 4;
	
	private static Pattern PATTERN_QUANTITY = Pattern.compile("^[0-9]+$");
	private static Pattern PATTERN_DELIVERY_DATE = Pattern.compile("^[0-9]+$");
	
	/**
	 * データを集計するメソッド
	 * 
	 * @param anOrderFileList
	 *            　:　集計するファイルデータ
	 * @return : 商品ごとの集計Map
	 * @throws KadaiException
	 */
	public static Map<String, String> totalizationOrderData(
			List<List<String>> anOrderFileList) throws KadaiException {

		// 引数nullチェック
		if (null == anOrderFileList) {
			// ファイルが存在しない場合はファイル入力エラー
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}

		Map<String, String[]> orderMap = deleteSameData(anOrderFileList);

		// 同じ製品名のデータを加算
		Map<String, String> totalizationMap = new HashMap<String, String>();
		for (Entry<String, String[]> order : orderMap.entrySet()) {
			// 製品名、数量を格納
			if (totalizationMap.containsKey(order.getValue()[RECEIVE_ID])) {
				// すでに同じ製品名が存在しているならば加算
				int orderNumber = calcSum(
						totalizationMap.get(order.getValue()[RECEIVE_ID]),
						order.getValue()[CUSTOMER_NAME]);
				totalizationMap.put(order.getValue()[RECEIVE_ID],
						String.valueOf(orderNumber));
			} else {
				totalizationMap.put(order.getValue()[RECEIVE_ID], order.getValue()[CUSTOMER_NAME]);
			}
		}
		return totalizationMap;
	}

	/**
	 * 数字(文字列)を受け取り、和を返すメソッド
	 * 
	 * @param aBeforeOrder
	 * @param anAddOrder
	 * @return
	 * @throws KadaiException
	 */
	private static int calcSum(String aBeforeOrder, String anAddOrder)
			throws KadaiException {
		try {
			return Integer.parseInt(aBeforeOrder)
					+ Integer.parseInt(anAddOrder);
		} catch (NumberFormatException e) {
			// 数値でなければファイルのフォーマットエラー
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
		}
	}

	/**
	 * 重複するものを排除してMAPに格納するメソッド
	 * 
	 * @param aCheckList
	 *            　チェック対象のリスト
	 * @return　Map<String,List<String>>　：重複無しのMAP
	 * @throws KadaiException
	 */
	private static Map<String, String[]> deleteSameData(
			List<List<String>> aCheckList) throws KadaiException {

		Map<String, String[]> orderMap = new HashMap<String, String[]>();

		for (List<String> fileData: aCheckList) {

			for (String str : fileData) {

				String[] lineData = str.split(",", -1);
				// ファイルフォーマットチェック呼び出し
				if(checkFileFormat(lineData)){
					// 製品名、数量を格納
					String[] oneOrderData = { lineData[MANUFACTURE_NAME], lineData[ORDER_NUM] };
					
					orderMap.put(lineData[RECEIVE_ID], oneOrderData);
				}
			}
		}

		return orderMap;
	}

	/**
	 * ファイルのフォーマットチェックメソッド
	 * 
	 * @param aFileLineData
	 * @throws KadaiException
	 */
	private static boolean checkFileFormat(String[] aFileLineData)
			throws KadaiException {
		
		if(aFileLineData.length==1 && aFileLineData[0].isEmpty()){
			return false;
		}
		
		// 要素が5個ではない場合フォーマットエラー
		if (aFileLineData.length != 5) {
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
		}

		// 納期以外がブランクの場合はフォーマットエラー
		for (int i = 0; i < aFileLineData.length - 1; i++) {
			if (aFileLineData[i].isEmpty()) {
				throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
			}
			if (i == 3) {
				if (!PATTERN_QUANTITY.matcher(aFileLineData[i]).matches()) {
					throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
				}
			}
		}
		
		if(!aFileLineData[PAYMENT_DATE].isEmpty()){
			if (!PATTERN_DELIVERY_DATE.matcher(aFileLineData[PAYMENT_DATE]).matches()) {
				throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
			}
		}
		return true;
	}
	
}
