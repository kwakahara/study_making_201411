package jp.ktsystem.kadai201411.k_wakahara;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.ktsystem.kadai201411.common.ErrorCode;
import jp.ktsystem.kadai201411.common.KadaiException;

/**
 * ファイルに出力するクラス
 * 
 * @author wakahara
 *
 */
public class OutputOrderData {
	/**
	 * 出力ファイル名
	 */
	private final static String OUTPUT_FILE_NAME = "ordercount.out";
	/**
	 * エンコード
	 */
	private final static String ENCODING = "UTF-8";
	
	/**
	 * データをファイルに出力するクラス
	 * 
	 * @param anOutputDir
	 *            フォルダパス
	 * @param anOrdersDataMap
	 *            　出力用データ
	 * @return　出力数
	 * @throws KadaiException
	 */
	public static int outputTextFile(String anOutputDir,
			Map<String, String> anOrdersDataMap) throws KadaiException {

		if(null == anOutputDir){
			throw new KadaiException(ErrorCode.QUESTION1_FILE_OUTPUT);
		}
		if(null == anOrdersDataMap){
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}
		
		List<Map.Entry<String, String>> entries = sortMap(anOrdersDataMap);
		
		
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File(anOutputDir, OUTPUT_FILE_NAME)), ENCODING));) {

			for (Map.Entry<String, String> data : entries) {
				bw.write(data.getKey() + "," + data.getValue());
				bw.newLine();
			}

			bw.flush();
		} catch (IOException e) {
			throw new KadaiException(ErrorCode.QUESTION1_FILE_OUTPUT);
		}

		return anOrdersDataMap.size();
	}

	/**
	 * MAPのソートメソッド
	 * 
	 * @param aSortMap
	 *            ソートするマップ
	 * @return ソート済みリスト
	 */
	public static List<Map.Entry<String, String>> sortMap(Map<String, String> aSortMap) {
		List<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(
				aSortMap.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> entry1,
					Entry<String, String> entry2) {
				int num = entry1.getValue().compareTo(entry2.getValue());
				return num;
			}
		});
		
		return entries;

	}
}
