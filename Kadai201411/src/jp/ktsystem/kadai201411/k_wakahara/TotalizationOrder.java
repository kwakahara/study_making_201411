package jp.ktsystem.kadai201411.k_wakahara;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
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

	private final static String STRING_BRANK = "";
	
	private static int receiveId = 0;
	private static int customerName = 1;
	private static int manufactureName = 2;
	private static int orderNum = 3;
	private static int paymentDate = 4;

	/**
	 * データを集計するメソッド
	 * 
	 * @param anOrderFileList
	 *            　:　集計するファイルデータ
	 * @return : 商品ごとの集計Map
	 * @throws KadaiException
	 */
	public static Map<String, String> totalizationOrder(
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
			if (totalizationMap.containsKey(order.getValue()[receiveId])) {
				// すでに同じ製品名が存在しているならば加算
				int orderNumber = calcSum(
						totalizationMap.get(order.getValue()[receiveId]),
						order.getValue()[customerName]);
				totalizationMap.put(order.getValue()[receiveId],
						String.valueOf(orderNumber));
			} else {
				totalizationMap.put(order.getValue()[receiveId], order.getValue()[customerName]);
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

		for (int i = 0; i < aCheckList.size(); i++) {
			List<String> fileData = aCheckList.get(i);

			for (int j = 0; j < fileData.size(); j++) {

				String[] lineData = fileData.get(j).split(",", -1);
				// ファイルフォーマットチェック呼び出し
				checkFileFormat(lineData);
				// 製品名、数量を格納
				String[] oneOrderData = { lineData[manufactureName], lineData[orderNum] };
				if (orderMap.containsKey(lineData[receiveId])) {
					// すでに同じIDのものが存在しているならば削除し、新規に追加
					orderMap.remove(lineData[receiveId]);
					orderMap.put(lineData[receiveId], oneOrderData);
				} else {
					// 同じIDが存在しなければ新規追加
					orderMap.put(lineData[receiveId], oneOrderData);
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
	private static void checkFileFormat(String[] aFileLineData)
			throws KadaiException {
		// 要素が5個ではない場合フォーマットエラー
		if (aFileLineData.length != 5) {
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
		}

		// 納期以外がブランクの場合はフォーマットエラー
		for (int i = 0; i < aFileLineData.length - 1; i++) {
			if (STRING_BRANK.equals(aFileLineData[i])) {
				throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
			}
			try {
				if (i == orderNum) {
					if (!isMatch(aFileLineData[i], "^[0-9]+$")) {
						throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
					}
				}
			} catch (NumberFormatException e) {
				throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
			}
		}
		
		if(!STRING_BRANK.equals(aFileLineData[paymentDate])){
			if (!isMatch(aFileLineData[paymentDate], "^[0-9]+$")) {
				throw new KadaiException(ErrorCode.SALES_ORDER_FILE_FORMAT);
			}
		}
	}
	
	public static boolean isMatch(String data, String ptn) {
	    Pattern pattern = Pattern.compile(ptn);
	    Matcher matcher = pattern.matcher(data);
	    return matcher.matches();
	}
}
